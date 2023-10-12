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
package com.antfin.arc.cluster.coordinator.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 0.15.0)",
    comments = "Source: coordinator.proto")
public class CoordinatorServiceGrpc {

  private CoordinatorServiceGrpc() {}

  public static final String SERVICE_NAME = "CoordinatorService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<QueryRequest,
      QueryResult> METHOD_EXECUTE_QUERY =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "CoordinatorService", "executeQuery"),
          io.grpc.protobuf.ProtoUtils.marshaller(QueryRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(QueryResult.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<QueryCancelRequest,
      QueryCancelResult> METHOD_CANCEL_QUERY =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "CoordinatorService", "cancelQuery"),
          io.grpc.protobuf.ProtoUtils.marshaller(QueryCancelRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(QueryCancelResult.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CoordinatorServiceStub newStub(io.grpc.Channel channel) {
    return new CoordinatorServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CoordinatorServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new CoordinatorServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static CoordinatorServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new CoordinatorServiceFutureStub(channel);
  }

  /**
   */
  @Deprecated public static interface CoordinatorService {

    /**
     * <pre>
     * Execute query and return query result.
     * </pre>
     */
    public void executeQuery(QueryRequest request,
                             io.grpc.stub.StreamObserver<QueryResult> responseObserver);

    /**
     * <pre>
     * Cancel query which is specified by queryId.
     * </pre>
     */
    public void cancelQuery(QueryCancelRequest request,
                            io.grpc.stub.StreamObserver<QueryCancelResult> responseObserver);
  }

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1469")
  public static abstract class CoordinatorServiceImplBase implements CoordinatorService, io.grpc.BindableService {

    @Override
    public void executeQuery(QueryRequest request,
                             io.grpc.stub.StreamObserver<QueryResult> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_EXECUTE_QUERY, responseObserver);
    }

    @Override
    public void cancelQuery(QueryCancelRequest request,
                            io.grpc.stub.StreamObserver<QueryCancelResult> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CANCEL_QUERY, responseObserver);
    }

    @Override public io.grpc.ServerServiceDefinition bindService() {
      return CoordinatorServiceGrpc.bindService(this);
    }
  }

  /**
   */
  @Deprecated public static interface CoordinatorServiceBlockingClient {

    /**
     * <pre>
     * Execute query and return query result.
     * </pre>
     */
    public QueryResult executeQuery(QueryRequest request);

    /**
     * <pre>
     * Cancel query which is specified by queryId.
     * </pre>
     */
    public QueryCancelResult cancelQuery(QueryCancelRequest request);
  }

  /**
   */
  @Deprecated public static interface CoordinatorServiceFutureClient {

    /**
     * <pre>
     * Execute query and return query result.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<QueryResult> executeQuery(
        QueryRequest request);

    /**
     * <pre>
     * Cancel query which is specified by queryId.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<QueryCancelResult> cancelQuery(
        QueryCancelRequest request);
  }

  public static class CoordinatorServiceStub extends io.grpc.stub.AbstractStub<CoordinatorServiceStub>
      implements CoordinatorService {
    private CoordinatorServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CoordinatorServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected CoordinatorServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CoordinatorServiceStub(channel, callOptions);
    }

    @Override
    public void executeQuery(QueryRequest request,
                             io.grpc.stub.StreamObserver<QueryResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_EXECUTE_QUERY, getCallOptions()), request, responseObserver);
    }

    @Override
    public void cancelQuery(QueryCancelRequest request,
                            io.grpc.stub.StreamObserver<QueryCancelResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CANCEL_QUERY, getCallOptions()), request, responseObserver);
    }
  }

  public static class CoordinatorServiceBlockingStub extends io.grpc.stub.AbstractStub<CoordinatorServiceBlockingStub>
      implements CoordinatorServiceBlockingClient {
    private CoordinatorServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CoordinatorServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected CoordinatorServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CoordinatorServiceBlockingStub(channel, callOptions);
    }

    @Override
    public QueryResult executeQuery(QueryRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_EXECUTE_QUERY, getCallOptions(), request);
    }

    @Override
    public QueryCancelResult cancelQuery(QueryCancelRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CANCEL_QUERY, getCallOptions(), request);
    }
  }

  public static class CoordinatorServiceFutureStub extends io.grpc.stub.AbstractStub<CoordinatorServiceFutureStub>
      implements CoordinatorServiceFutureClient {
    private CoordinatorServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CoordinatorServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected CoordinatorServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CoordinatorServiceFutureStub(channel, callOptions);
    }

    @Override
    public com.google.common.util.concurrent.ListenableFuture<QueryResult> executeQuery(
        QueryRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_EXECUTE_QUERY, getCallOptions()), request);
    }

    @Override
    public com.google.common.util.concurrent.ListenableFuture<QueryCancelResult> cancelQuery(
        QueryCancelRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CANCEL_QUERY, getCallOptions()), request);
    }
  }

  @Deprecated public static abstract class AbstractCoordinatorService extends CoordinatorServiceImplBase {}

  private static final int METHODID_EXECUTE_QUERY = 0;
  private static final int METHODID_CANCEL_QUERY = 1;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CoordinatorService serviceImpl;
    private final int methodId;

    public MethodHandlers(CoordinatorService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_EXECUTE_QUERY:
          serviceImpl.executeQuery((QueryRequest) request,
              (io.grpc.stub.StreamObserver<QueryResult>) responseObserver);
          break;
        case METHODID_CANCEL_QUERY:
          serviceImpl.cancelQuery((QueryCancelRequest) request,
              (io.grpc.stub.StreamObserver<QueryCancelResult>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    return new io.grpc.ServiceDescriptor(SERVICE_NAME,
        METHOD_EXECUTE_QUERY,
        METHOD_CANCEL_QUERY);
  }

  @Deprecated public static io.grpc.ServerServiceDefinition bindService(
      final CoordinatorService serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          METHOD_EXECUTE_QUERY,
          asyncUnaryCall(
            new MethodHandlers<
              QueryRequest,
              QueryResult>(
                serviceImpl, METHODID_EXECUTE_QUERY)))
        .addMethod(
          METHOD_CANCEL_QUERY,
          asyncUnaryCall(
            new MethodHandlers<
              QueryCancelRequest,
              QueryCancelResult>(
                serviceImpl, METHODID_CANCEL_QUERY)))
        .build();
  }
}
