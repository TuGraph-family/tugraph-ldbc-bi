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

package com.antfin.arc.cluster.proto;

public final class Master {
  private Master() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Task_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Task_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_WorkerMetrics_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_WorkerMetrics_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_JobSystemMetrics_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_JobSystemMetrics_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_RuntimeConfig_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_RuntimeConfig_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_RuntimeConfig_ConfigEntry_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_RuntimeConfig_ConfigEntry_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ConfigEntry_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ConfigEntry_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ConfigEntries_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ConfigEntries_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\014master.proto\032\033google/protobuf/empty.pr" +
      "oto\"(\n\004Task\022\016\n\006taskId\030\001 \001(\t\022\020\n\010threadId\030" +
      "\002 \001(\003\"\275\002\n\rWorkerMetrics\022\014\n\004host\030\001 \001(\t\022\013\n" +
      "\003pid\030\002 \001(\005\022\027\n\017heapCommittedMB\030\003 \001(\003\022\022\n\nh" +
      "eapUsedMB\030\004 \001(\003\022\025\n\rheapUsedRatio\030\005 \001(\001\022\020" +
      "\n\010fgcCount\030\006 \001(\003\022\017\n\007fgcTime\030\007 \001(\003\022\016\n\006gcT" +
      "ime\030\010 \001(\003\022\017\n\007gcCount\030\t \001(\003\022\017\n\007avgLoad\030\n " +
      "\001(\001\022\022\n\navailCores\030\013 \001(\005\022\022\n\nprocessCpu\030\014 " +
      "\001(\001\022\022\n\nreportTime\030\r \001(\003\022\017\n\007taskNum\030\016 \001(\005" +
      "\022\024\n\005tasks\030\017 \003(\0132\005.Task\022\025\n\ractiveThreads\030" +
      "\020 \001(\005\"9\n\020JobSystemMetrics\022%\n\rworkerMetri" +
      "cs\030\001 \003(\0132\016.WorkerMetrics\"j\n\rRuntimeConfi" +
      "g\022*\n\006config\030\001 \003(\0132\032.RuntimeConfig.Config" +
      "Entry\032-\n\013ConfigEntry\022\013\n\003key\030\001 \001(\t\022\r\n\005val" +
      "ue\030\002 \001(\t:\0028\001\":\n\013ConfigEntry\022\013\n\003key\030\001 \001(\t" +
      "\022\r\n\005value\030\002 \001(\t\022\017\n\007enabled\030\003 \001(\010\".\n\rConf" +
      "igEntries\022\035\n\007entries\030\001 \003(\0132\014.ConfigEntry" +
      "2\302\002\n\rMasterService\0229\n\rreportMetrics\022\016.Wo" +
      "rkerMetrics\032\026.google.protobuf.Empty\"\000\0229\n" +
      "\ngetMetrics\022\026.google.protobuf.Empty\032\021.Jo" +
      "bSystemMetrics\"\000\022<\n\020getRuntimeConfig\022\026.g" +
      "oogle.protobuf.Empty\032\016.RuntimeConfig\"\000\022?" +
      "\n\023updateRuntimeConfig\022\016.RuntimeConfig\032\026." +
      "google.protobuf.Empty\"\000\022<\n\020getConfigEntr" +
      "ies\022\026.google.protobuf.Empty\032\016.ConfigEntr" +
      "ies\"\000B \n\034com.antfin.arc.cluster.protoP\001b" +
      "\006proto3"
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
          com.google.protobuf.EmptyProto.getDescriptor(),
        }, assigner);
    internal_static_Task_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Task_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Task_descriptor,
        new String[] { "TaskId", "ThreadId", });
    internal_static_WorkerMetrics_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_WorkerMetrics_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_WorkerMetrics_descriptor,
        new String[] { "Host", "Pid", "HeapCommittedMB", "HeapUsedMB", "HeapUsedRatio", "FgcCount", "FgcTime", "GcTime", "GcCount", "AvgLoad", "AvailCores", "ProcessCpu", "ReportTime", "TaskNum", "Tasks", "ActiveThreads", });
    internal_static_JobSystemMetrics_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_JobSystemMetrics_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_JobSystemMetrics_descriptor,
        new String[] { "WorkerMetrics", });
    internal_static_RuntimeConfig_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_RuntimeConfig_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_RuntimeConfig_descriptor,
        new String[] { "Config", });
    internal_static_RuntimeConfig_ConfigEntry_descriptor =
      internal_static_RuntimeConfig_descriptor.getNestedTypes().get(0);
    internal_static_RuntimeConfig_ConfigEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_RuntimeConfig_ConfigEntry_descriptor,
        new String[] { "Key", "Value", });
    internal_static_ConfigEntry_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_ConfigEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ConfigEntry_descriptor,
        new String[] { "Key", "Value", "Enabled", });
    internal_static_ConfigEntries_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_ConfigEntries_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ConfigEntries_descriptor,
        new String[] { "Entries", });
    com.google.protobuf.EmptyProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
