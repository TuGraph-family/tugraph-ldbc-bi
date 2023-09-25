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
package com.antfin.arc.cluster.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * The service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 0.15.0)",
    comments = "Source: master.proto")
public class MasterServiceGrpc {

  private MasterServiceGrpc() {}

  public static final String SERVICE_NAME = "MasterService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<WorkerMetrics,
      com.google.protobuf.Empty> METHOD_REPORT_METRICS =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "MasterService", "reportMetrics"),
          io.grpc.protobuf.ProtoUtils.marshaller(WorkerMetrics.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.google.protobuf.Empty.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      JobSystemMetrics> METHOD_GET_METRICS =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "MasterService", "getMetrics"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.google.protobuf.Empty.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(JobSystemMetrics.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      RuntimeConfig> METHOD_GET_RUNTIME_CONFIG =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "MasterService", "getRuntimeConfig"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.google.protobuf.Empty.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(RuntimeConfig.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<RuntimeConfig,
      com.google.protobuf.Empty> METHOD_UPDATE_RUNTIME_CONFIG =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "MasterService", "updateRuntimeConfig"),
          io.grpc.protobuf.ProtoUtils.marshaller(RuntimeConfig.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.google.protobuf.Empty.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      ConfigEntries> METHOD_GET_CONFIG_ENTRIES =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "MasterService", "getConfigEntries"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.google.protobuf.Empty.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(ConfigEntries.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MasterServiceStub newStub(io.grpc.Channel channel) {
    return new MasterServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MasterServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new MasterServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static MasterServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new MasterServiceFutureStub(channel);
  }

  /**
   * <pre>
   * The service definition.
   * </pre>
   */
  @Deprecated public static interface MasterService {

    /**
     */
    public void reportMetrics(WorkerMetrics request,
                              io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver);

    /**
     */
    public void getMetrics(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<JobSystemMetrics> responseObserver);

    /**
     */
    public void getRuntimeConfig(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<RuntimeConfig> responseObserver);

    /**
     */
    public void updateRuntimeConfig(RuntimeConfig request,
                                    io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver);

    /**
     */
    public void getConfigEntries(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<ConfigEntries> responseObserver);
  }

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1469")
  public static abstract class MasterServiceImplBase implements MasterService, io.grpc.BindableService {

    @Override
    public void reportMetrics(WorkerMetrics request,
                              io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_REPORT_METRICS, responseObserver);
    }

    @Override
    public void getMetrics(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<JobSystemMetrics> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_METRICS, responseObserver);
    }

    @Override
    public void getRuntimeConfig(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<RuntimeConfig> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_RUNTIME_CONFIG, responseObserver);
    }

    @Override
    public void updateRuntimeConfig(RuntimeConfig request,
                                    io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UPDATE_RUNTIME_CONFIG, responseObserver);
    }

    @Override
    public void getConfigEntries(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<ConfigEntries> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_CONFIG_ENTRIES, responseObserver);
    }

    @Override public io.grpc.ServerServiceDefinition bindService() {
      return MasterServiceGrpc.bindService(this);
    }
  }

  /**
   * <pre>
   * The service definition.
   * </pre>
   */
  @Deprecated public static interface MasterServiceBlockingClient {

    /**
     */
    public com.google.protobuf.Empty reportMetrics(WorkerMetrics request);

    /**
     */
    public JobSystemMetrics getMetrics(com.google.protobuf.Empty request);

    /**
     */
    public RuntimeConfig getRuntimeConfig(com.google.protobuf.Empty request);

    /**
     */
    public com.google.protobuf.Empty updateRuntimeConfig(RuntimeConfig request);

    /**
     */
    public ConfigEntries getConfigEntries(com.google.protobuf.Empty request);
  }

  /**
   * <pre>
   * The service definition.
   * </pre>
   */
  @Deprecated public static interface MasterServiceFutureClient {

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> reportMetrics(
        WorkerMetrics request);

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<JobSystemMetrics> getMetrics(
        com.google.protobuf.Empty request);

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<RuntimeConfig> getRuntimeConfig(
        com.google.protobuf.Empty request);

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> updateRuntimeConfig(
        RuntimeConfig request);

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ConfigEntries> getConfigEntries(
        com.google.protobuf.Empty request);
  }

  public static class MasterServiceStub extends io.grpc.stub.AbstractStub<MasterServiceStub>
      implements MasterService {
    private MasterServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MasterServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected MasterServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MasterServiceStub(channel, callOptions);
    }

    @Override
    public void reportMetrics(WorkerMetrics request,
                              io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_REPORT_METRICS, getCallOptions()), request, responseObserver);
    }

    @Override
    public void getMetrics(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<JobSystemMetrics> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_METRICS, getCallOptions()), request, responseObserver);
    }

    @Override
    public void getRuntimeConfig(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<RuntimeConfig> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_RUNTIME_CONFIG, getCallOptions()), request, responseObserver);
    }

    @Override
    public void updateRuntimeConfig(RuntimeConfig request,
                                    io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPDATE_RUNTIME_CONFIG, getCallOptions()), request, responseObserver);
    }

    @Override
    public void getConfigEntries(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<ConfigEntries> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_CONFIG_ENTRIES, getCallOptions()), request, responseObserver);
    }
  }

  public static class MasterServiceBlockingStub extends io.grpc.stub.AbstractStub<MasterServiceBlockingStub>
      implements MasterServiceBlockingClient {
    private MasterServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MasterServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected MasterServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MasterServiceBlockingStub(channel, callOptions);
    }

    @Override
    public com.google.protobuf.Empty reportMetrics(WorkerMetrics request) {
      return blockingUnaryCall(
          getChannel(), METHOD_REPORT_METRICS, getCallOptions(), request);
    }

    @Override
    public JobSystemMetrics getMetrics(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_METRICS, getCallOptions(), request);
    }

    @Override
    public RuntimeConfig getRuntimeConfig(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_RUNTIME_CONFIG, getCallOptions(), request);
    }

    @Override
    public com.google.protobuf.Empty updateRuntimeConfig(RuntimeConfig request) {
      return blockingUnaryCall(
          getChannel(), METHOD_UPDATE_RUNTIME_CONFIG, getCallOptions(), request);
    }

    @Override
    public ConfigEntries getConfigEntries(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_CONFIG_ENTRIES, getCallOptions(), request);
    }
  }

  public static class MasterServiceFutureStub extends io.grpc.stub.AbstractStub<MasterServiceFutureStub>
      implements MasterServiceFutureClient {
    private MasterServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MasterServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected MasterServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MasterServiceFutureStub(channel, callOptions);
    }

    @Override
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> reportMetrics(
        WorkerMetrics request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_REPORT_METRICS, getCallOptions()), request);
    }

    @Override
    public com.google.common.util.concurrent.ListenableFuture<JobSystemMetrics> getMetrics(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_METRICS, getCallOptions()), request);
    }

    @Override
    public com.google.common.util.concurrent.ListenableFuture<RuntimeConfig> getRuntimeConfig(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_RUNTIME_CONFIG, getCallOptions()), request);
    }

    @Override
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> updateRuntimeConfig(
        RuntimeConfig request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPDATE_RUNTIME_CONFIG, getCallOptions()), request);
    }

    @Override
    public com.google.common.util.concurrent.ListenableFuture<ConfigEntries> getConfigEntries(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_CONFIG_ENTRIES, getCallOptions()), request);
    }
  }

  @Deprecated public static abstract class AbstractMasterService extends MasterServiceImplBase {}

  private static final int METHODID_REPORT_METRICS = 0;
  private static final int METHODID_GET_METRICS = 1;
  private static final int METHODID_GET_RUNTIME_CONFIG = 2;
  private static final int METHODID_UPDATE_RUNTIME_CONFIG = 3;
  private static final int METHODID_GET_CONFIG_ENTRIES = 4;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MasterService serviceImpl;
    private final int methodId;

    public MethodHandlers(MasterService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REPORT_METRICS:
          serviceImpl.reportMetrics((WorkerMetrics) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_GET_METRICS:
          serviceImpl.getMetrics((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<JobSystemMetrics>) responseObserver);
          break;
        case METHODID_GET_RUNTIME_CONFIG:
          serviceImpl.getRuntimeConfig((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<RuntimeConfig>) responseObserver);
          break;
        case METHODID_UPDATE_RUNTIME_CONFIG:
          serviceImpl.updateRuntimeConfig((RuntimeConfig) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_GET_CONFIG_ENTRIES:
          serviceImpl.getConfigEntries((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<ConfigEntries>) responseObserver);
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
        METHOD_REPORT_METRICS,
        METHOD_GET_METRICS,
        METHOD_GET_RUNTIME_CONFIG,
        METHOD_UPDATE_RUNTIME_CONFIG,
        METHOD_GET_CONFIG_ENTRIES);
  }

  @Deprecated public static io.grpc.ServerServiceDefinition bindService(
      final MasterService serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          METHOD_REPORT_METRICS,
          asyncUnaryCall(
            new MethodHandlers<
              WorkerMetrics,
              com.google.protobuf.Empty>(
                serviceImpl, METHODID_REPORT_METRICS)))
        .addMethod(
          METHOD_GET_METRICS,
          asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              JobSystemMetrics>(
                serviceImpl, METHODID_GET_METRICS)))
        .addMethod(
          METHOD_GET_RUNTIME_CONFIG,
          asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              RuntimeConfig>(
                serviceImpl, METHODID_GET_RUNTIME_CONFIG)))
        .addMethod(
          METHOD_UPDATE_RUNTIME_CONFIG,
          asyncUnaryCall(
            new MethodHandlers<
              RuntimeConfig,
              com.google.protobuf.Empty>(
                serviceImpl, METHODID_UPDATE_RUNTIME_CONFIG)))
        .addMethod(
          METHOD_GET_CONFIG_ENTRIES,
          asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              ConfigEntries>(
                serviceImpl, METHODID_GET_CONFIG_ENTRIES)))
        .build();
  }
}
