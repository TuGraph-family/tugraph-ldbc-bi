/**
 * Copyright 2023 AntGroup CO., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */
package com.antfin.arc.cluster.driver.coordinator;

import com.antfin.arc.cluster.coordinator.proto.CoordinatorServiceGrpc;
import com.antfin.arc.cluster.coordinator.proto.QueryConfig;
import com.antfin.arc.cluster.coordinator.proto.QueryRequest;
import com.antfin.arc.cluster.coordinator.proto.QueryResult;
import com.google.common.base.Preconditions;
import io.grpc.ManagedChannel;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.netty.NettyChannelBuilder;
import io.netty.channel.ChannelOption;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TuGraphClient implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(TuGraphClient.class);

    private static final String BUSY_MSG = "all coordinators are busy, cancel query execution";
    private static final String SPLIT_KEY = ":";
    private static final int DEFAULT_SHUTDOWN_AWAIT_MS = 5;
    private static final long DEFAULT_SLEEP_MS = 1000L;
    private static final Random RANDOM = new Random(System.currentTimeMillis());

    private final long sleepMs;
    private final String uniqueClientId;

    private String[] coordinatorAddresses;
    private ManagedChannel[] coordinatorChannels;
    private CoordinatorServiceGrpc.CoordinatorServiceBlockingStub[] coordinators;

    public TuGraphClient(String host, int port) {
        this(new HashMap<>(), host, port);
    }

    public TuGraphClient(Map<String, String> config, String host, int port) {
        this(config, DEFAULT_SLEEP_MS, host, port);
    }

    private TuGraphClient(Map<String, String> config,
                          long sleepMs,
                          String host,
                          int port) {
        this.sleepMs = sleepMs <= 0 ? DEFAULT_SLEEP_MS : sleepMs;
        this.uniqueClientId = UUID.randomUUID().toString();
        this.coordinatorAddresses = new String[] {host + ":" + port};
        this.coordinatorChannels = new ManagedChannel[1];
        this.coordinators = new CoordinatorServiceGrpc.CoordinatorServiceBlockingStub[1];
        init();
    }

    private void init() {
        initChannel();
        LOGGER.info("client [{}] init over", this.uniqueClientId);
    }

    private void initChannel() {
        IntStream.range(0, this.coordinatorAddresses.length).forEach(this::initChannel);
    }

    private void initChannel(int idx) {
        ManagedChannel oldChannel = this.coordinatorChannels[idx];
        if (oldChannel != null && (!oldChannel.isShutdown() || !oldChannel.isTerminated())) {
            oldChannel.shutdownNow();
            this.coordinatorChannels[idx] = null;
        }
        Throwable lastException = null;
        for (int i = 0; i < 3; i++) {
            try {
                String[] address = this.coordinatorAddresses[idx].split(SPLIT_KEY);
                String host = address[0];
                int port = Integer.parseInt(address[1]);
                ManagedChannel channel = buildChannel(host, port, 30000);
                this.coordinatorChannels[idx] = channel;
                this.coordinators[idx] = CoordinatorServiceGrpc.newBlockingStub(channel);
                LOGGER.info("init coordinator with address {}:{}", host, port);
                return;
            } catch (Throwable e) {
                lastException = e;
                LOGGER.warn("re init connection to {} fail {} times, retry", this.coordinatorAddresses[idx], i + 1, e);
            }
        }
        String msg = String.format("try connect to %s fail after %d times", this.coordinatorAddresses[idx], 3);
        LOGGER.error(msg, lastException);
        throw new RuntimeException(lastException);
    }

    public void shutdown() throws InterruptedException {
        for (ManagedChannel channel : this.coordinatorChannels) {
            if (channel != null) {
                channel.shutdown().awaitTermination(DEFAULT_SHUTDOWN_AWAIT_MS, TimeUnit.SECONDS);
            }
        }
        LOGGER.info("shutdown all channel");
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            LOGGER.error("caught interrupted exception", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Build the managed channel.
     *
     * @param host host
     * @param port port
     * @param timeoutMs timeout ms
     * @return channel
     */
    private ManagedChannel buildChannel(String host, int port, int timeoutMs) {
        return NettyChannelBuilder.forAddress(host, port)
            .withOption(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeoutMs)
            .maxInboundMessageSize(4194304)
            .maxRetryAttempts(5)
            .retryBufferSize(16777216L)
            .perRpcBufferLimit(1048576L)
            // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
            // needing certificates.
            .usePlaintext()
            .build();
    }

    /**
     * The main entry for submitting and executing user query with config.
     *
     * @param gremlinQuery query
     * @param queryConfig query configuration
     * @return query result
     */
    public QueryResult executeQuery(String gremlinQuery, Map<String, String> queryConfig) {
        return this.doExecuteQuery(gremlinQuery, queryConfig);
    }

    private QueryResult doExecuteQuery(String gremlinQuery, Map<String, String> queryConfig) {
        Preconditions.checkArgument(queryConfig != null, "The queryConfig must not be null");
        Map<String, String> config = new HashMap<>(queryConfig);
        config.put("tugraph.unique.client.id", this.uniqueClientId);
        List<QueryConfig> configs = config.entrySet().stream()
            .map(entry -> QueryConfig.newBuilder().setKey(entry.getKey()).setValue(entry.getValue()).build())
            .collect(Collectors.toList());
        QueryRequest request = QueryRequest.newBuilder()
            .setGremlinQuery(gremlinQuery)
            .addAllQueryConfig(configs)
            .build();
        return executeQuery(request);
    }

    /**
     * Execute user query.
     *
     * @param request query request
     * @return query result
     */
    private QueryResult executeQuery(QueryRequest request) {
        QueryResult result = null;
        for (int i = 0; i < 3; i++) {
            result = this.doExecuteQuery(request);
            boolean busy = result.getQueryError().equals("all coordinators are busy, cancel query execution");
            boolean unavailable = result.getQueryError().equals(Status.Code.UNAVAILABLE.name());
            if (result.getQueryStatus() || (!busy && !unavailable)) {
                return result;
            }
            LOGGER.info("all coordinator busy or unavailable, sleep {}ms and retry", this.sleepMs);
            sleep(this.sleepMs);
        }
        LOGGER.error("all coordinator busy or unavailable after retry {} times", 3);
        return result;
    }

    private QueryResult doExecuteQuery(QueryRequest request) {
        // select the first connection in random
        int coordinatorNum = this.coordinatorAddresses.length;
        int idx = RANDOM.nextInt(coordinatorNum);
        try {
            QueryResult result = null;
            for (int i = 0; i < coordinatorNum; i++) {
                ensureChannelAlive(idx);
                String address = this.coordinatorAddresses[idx];
                result = this.coordinators[idx].executeQuery(request);
                if (!result.getQueryStatus() && result.getQueryError().equals(BUSY_MSG)) {
                    LOGGER.warn("coordinator[{}] [{}] is busy, try next", idx, address);
                    idx = (idx + 1) % coordinatorNum;
                    continue;
                }
                return result;
            }
            if (!result.getQueryStatus() && result.getQueryError().equals(BUSY_MSG)) {
                LOGGER.error(BUSY_MSG);
                return QueryResult.newBuilder()
                    .setQueryStatus(false)
                    .setQueryId(result.getQueryId())
                    .setQueryError(BUSY_MSG)
                    .build();
            } else {
                throw new RuntimeException("Should not happen");
            }
        } catch (StatusRuntimeException e) {
            LOGGER.error("query {} execute failed with status {} and cause {}",
                e.getStatus(), request.getGremlinQuery(), getStackMsg(e));
            if (e.getStatus().getCode() == Status.Code.UNAVAILABLE) {
                LOGGER.info("re-init for exception: {}", Status.UNAVAILABLE);
                init();
                return QueryResult.newBuilder()
                    .setQueryError(Status.Code.UNAVAILABLE.name())
                    .setQueryStatus(false)
                    .build();
            } else {
                LOGGER.info("re-init channel {} for exception: {}", this.coordinatorAddresses[idx], e.getStatus());
                initChannel(idx);
                return QueryResult.newBuilder()
                    .setQueryError(e.getMessage())
                    .setQueryStatus(false)
                    .build();
            }
        }
    }

    private static String getStackMsg(Exception e) {
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackArray = e.getStackTrace();
        for (StackTraceElement element : stackArray) {
            sb.append(element.toString()).append("\n");
        }
        return sb.toString();
    }

    private void ensureChannelAlive(int idx) {
        ManagedChannel channel = this.coordinatorChannels[idx];
        if (channel == null || channel.isShutdown() || channel.isTerminated()) {
            LOGGER.warn("connection of {} lost, reconnect...", this.coordinatorAddresses[idx]);
            initChannel(idx);
        }
    }

}
