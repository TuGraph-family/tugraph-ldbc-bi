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

public interface QueryRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:QueryRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string gremlinQuery = 1;</code>
   */
  String getGremlinQuery();
  /**
   * <code>string gremlinQuery = 1;</code>
   */
  com.google.protobuf.ByteString
      getGremlinQueryBytes();

  /**
   * <code>repeated .QueryConfig queryConfig = 2;</code>
   */
  java.util.List<QueryConfig>
      getQueryConfigList();
  /**
   * <code>repeated .QueryConfig queryConfig = 2;</code>
   */
  QueryConfig getQueryConfig(int index);
  /**
   * <code>repeated .QueryConfig queryConfig = 2;</code>
   */
  int getQueryConfigCount();
  /**
   * <code>repeated .QueryConfig queryConfig = 2;</code>
   */
  java.util.List<? extends QueryConfigOrBuilder>
      getQueryConfigOrBuilderList();
  /**
   * <code>repeated .QueryConfig queryConfig = 2;</code>
   */
  QueryConfigOrBuilder getQueryConfigOrBuilder(
      int index);
}