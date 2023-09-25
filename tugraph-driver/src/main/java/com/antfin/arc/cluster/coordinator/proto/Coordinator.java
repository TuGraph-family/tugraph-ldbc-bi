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
// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: master.proto

package com.antfin.arc.cluster.coordinator.proto;

public final class Coordinator {
  private Coordinator() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_QueryRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_QueryRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_QueryConfig_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_QueryConfig_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_QueryCancelRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_QueryCancelRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_QueryResult_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_QueryResult_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_QueryCancelResult_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_QueryCancelResult_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\021coordinator.proto\"G\n\014QueryRequest\022\024\n\014g" +
      "remlinQuery\030\001 \001(\t\022!\n\013queryConfig\030\002 \003(\0132\014" +
      ".QueryConfig\")\n\013QueryConfig\022\013\n\003key\030\001 \001(\t" +
      "\022\r\n\005value\030\002 \001(\t\"%\n\022QueryCancelRequest\022\017\n" +
      "\007queryId\030\001 \001(\003\"\\\n\013QueryResult\022\017\n\007queryId" +
      "\030\001 \001(\003\022\023\n\013queryStatus\030\002 \001(\010\022\023\n\013queryResu" +
      "lt\030\003 \001(\t\022\022\n\nqueryError\030\004 \001(\t\"O\n\021QueryCan" +
      "celResult\022\017\n\007queryId\030\001 \001(\003\022\024\n\014cancelStat" +
      "us\030\002 \001(\010\022\023\n\013cancelError\030\003 \001(\t2}\n\022Coordin" +
      "atorService\022-\n\014executeQuery\022\r.QueryReque" +
      "st\032\014.QueryResult\"\000\0228\n\013cancelQuery\022\023.Quer" +
      "yCancelRequest\032\022.QueryCancelResult\"\000B,\n(" +
      "com.antfin.arc.cluster.coordinator.proto" +
      "P\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_QueryRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_QueryRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_QueryRequest_descriptor,
        new String[] { "GremlinQuery", "QueryConfig", });
    internal_static_QueryConfig_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_QueryConfig_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_QueryConfig_descriptor,
        new String[] { "Key", "Value", });
    internal_static_QueryCancelRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_QueryCancelRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_QueryCancelRequest_descriptor,
        new String[] { "QueryId", });
    internal_static_QueryResult_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_QueryResult_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_QueryResult_descriptor,
        new String[] { "QueryId", "QueryStatus", "QueryResult", "QueryError", });
    internal_static_QueryCancelResult_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_QueryCancelResult_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_QueryCancelResult_descriptor,
        new String[] { "QueryId", "CancelStatus", "CancelError", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
