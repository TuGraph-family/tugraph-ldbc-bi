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

public interface RuntimeConfigOrBuilder extends
    // @@protoc_insertion_point(interface_extends:RuntimeConfig)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>map&lt;string, string&gt; config = 1;</code>
   */
  int getConfigCount();
  /**
   * <code>map&lt;string, string&gt; config = 1;</code>
   */
  boolean containsConfig(
      String key);
  /**
   * Use {@link #getConfigMap()} instead.
   */
  @Deprecated
  java.util.Map<String, String>
  getConfig();
  /**
   * <code>map&lt;string, string&gt; config = 1;</code>
   */
  java.util.Map<String, String>
  getConfigMap();
  /**
   * <code>map&lt;string, string&gt; config = 1;</code>
   */

  String getConfigOrDefault(
      String key,
      String defaultValue);
  /**
   * <code>map&lt;string, string&gt; config = 1;</code>
   */

  String getConfigOrThrow(
      String key);
}
