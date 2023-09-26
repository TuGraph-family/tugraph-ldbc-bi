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
// source: coordinator.proto

package com.antfin.arc.cluster.coordinator.proto;

public interface QueryCancelResultOrBuilder extends
    // @@protoc_insertion_point(interface_extends:QueryCancelResult)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * The query which will be cancelled.
   * </pre>
   *
   * <code>int64 queryId = 1;</code>
   */
  long getQueryId();

  /**
   * <pre>
   * Cancel status of the current query.
   * </pre>
   *
   * <code>bool cancelStatus = 2;</code>
   */
  boolean getCancelStatus();

  /**
   * <pre>
   * Get the query cancel error if status is false.
   * </pre>
   *
   * <code>string cancelError = 3;</code>
   */
  String getCancelError();
  /**
   * <pre>
   * Get the query cancel error if status is false.
   * </pre>
   *
   * <code>string cancelError = 3;</code>
   */
  com.google.protobuf.ByteString
      getCancelErrorBytes();
}