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

public interface TaskOrBuilder extends
    // @@protoc_insertion_point(interface_extends:Task)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string taskId = 1;</code>
   */
  String getTaskId();
  /**
   * <code>string taskId = 1;</code>
   */
  com.google.protobuf.ByteString
      getTaskIdBytes();

  /**
   * <code>int64 threadId = 2;</code>
   */
  long getThreadId();
}