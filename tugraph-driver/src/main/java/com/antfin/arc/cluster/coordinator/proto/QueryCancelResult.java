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

/**
 * <pre>
 * Query cancel status.
 * </pre>
 *
 * Protobuf type {@code QueryCancelResult}
 */
public  final class QueryCancelResult extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:QueryCancelResult)
    QueryCancelResultOrBuilder {
private static final long serialVersionUID = 0L;
  // Use QueryCancelResult.newBuilder() to construct.
  private QueryCancelResult(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private QueryCancelResult() {
    cancelError_ = "";
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private QueryCancelResult(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {

            queryId_ = input.readInt64();
            break;
          }
          case 16: {

            cancelStatus_ = input.readBool();
            break;
          }
          case 26: {
            String s = input.readStringRequireUtf8();

            cancelError_ = s;
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return Coordinator.internal_static_QueryCancelResult_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return Coordinator.internal_static_QueryCancelResult_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            QueryCancelResult.class, Builder.class);
  }

  public static final int QUERYID_FIELD_NUMBER = 1;
  private long queryId_;
  /**
   * <pre>
   * The query which will be cancelled.
   * </pre>
   *
   * <code>int64 queryId = 1;</code>
   */
  public long getQueryId() {
    return queryId_;
  }

  public static final int CANCELSTATUS_FIELD_NUMBER = 2;
  private boolean cancelStatus_;
  /**
   * <pre>
   * Cancel status of the current query.
   * </pre>
   *
   * <code>bool cancelStatus = 2;</code>
   */
  public boolean getCancelStatus() {
    return cancelStatus_;
  }

  public static final int CANCELERROR_FIELD_NUMBER = 3;
  private volatile Object cancelError_;
  /**
   * <pre>
   * Get the query cancel error if status is false.
   * </pre>
   *
   * <code>string cancelError = 3;</code>
   */
  public String getCancelError() {
    Object ref = cancelError_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      cancelError_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * Get the query cancel error if status is false.
   * </pre>
   *
   * <code>string cancelError = 3;</code>
   */
  public com.google.protobuf.ByteString
      getCancelErrorBytes() {
    Object ref = cancelError_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      cancelError_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (queryId_ != 0L) {
      output.writeInt64(1, queryId_);
    }
    if (cancelStatus_ != false) {
      output.writeBool(2, cancelStatus_);
    }
    if (!getCancelErrorBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, cancelError_);
    }
    unknownFields.writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (queryId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, queryId_);
    }
    if (cancelStatus_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(2, cancelStatus_);
    }
    if (!getCancelErrorBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, cancelError_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof QueryCancelResult)) {
      return super.equals(obj);
    }
    QueryCancelResult other = (QueryCancelResult) obj;

    if (getQueryId()
        != other.getQueryId()) return false;
    if (getCancelStatus()
        != other.getCancelStatus()) return false;
    if (!getCancelError()
        .equals(other.getCancelError())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + QUERYID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getQueryId());
    hash = (37 * hash) + CANCELSTATUS_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getCancelStatus());
    hash = (37 * hash) + CANCELERROR_FIELD_NUMBER;
    hash = (53 * hash) + getCancelError().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static QueryCancelResult parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static QueryCancelResult parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static QueryCancelResult parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static QueryCancelResult parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static QueryCancelResult parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static QueryCancelResult parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static QueryCancelResult parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static QueryCancelResult parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static QueryCancelResult parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static QueryCancelResult parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static QueryCancelResult parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static QueryCancelResult parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(QueryCancelResult prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @Override
  protected Builder newBuilderForType(
      BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * Query cancel status.
   * </pre>
   *
   * Protobuf type {@code QueryCancelResult}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:QueryCancelResult)
      QueryCancelResultOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return Coordinator.internal_static_QueryCancelResult_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return Coordinator.internal_static_QueryCancelResult_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              QueryCancelResult.class, Builder.class);
    }

    // Construct using com.antfin.arc.cluster.coordinator.proto.QueryCancelResult.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @Override
    public Builder clear() {
      super.clear();
      queryId_ = 0L;

      cancelStatus_ = false;

      cancelError_ = "";

      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return Coordinator.internal_static_QueryCancelResult_descriptor;
    }

    @Override
    public QueryCancelResult getDefaultInstanceForType() {
      return QueryCancelResult.getDefaultInstance();
    }

    @Override
    public QueryCancelResult build() {
      QueryCancelResult result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public QueryCancelResult buildPartial() {
      QueryCancelResult result = new QueryCancelResult(this);
      result.queryId_ = queryId_;
      result.cancelStatus_ = cancelStatus_;
      result.cancelError_ = cancelError_;
      onBuilt();
      return result;
    }

    @Override
    public Builder clone() {
      return super.clone();
    }
    @Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return super.setField(field, value);
    }
    @Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return super.addRepeatedField(field, value);
    }
    @Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof QueryCancelResult) {
        return mergeFrom((QueryCancelResult)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(QueryCancelResult other) {
      if (other == QueryCancelResult.getDefaultInstance()) return this;
      if (other.getQueryId() != 0L) {
        setQueryId(other.getQueryId());
      }
      if (other.getCancelStatus() != false) {
        setCancelStatus(other.getCancelStatus());
      }
      if (!other.getCancelError().isEmpty()) {
        cancelError_ = other.cancelError_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @Override
    public final boolean isInitialized() {
      return true;
    }

    @Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      QueryCancelResult parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (QueryCancelResult) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long queryId_ ;
    /**
     * <pre>
     * The query which will be cancelled.
     * </pre>
     *
     * <code>int64 queryId = 1;</code>
     */
    public long getQueryId() {
      return queryId_;
    }
    /**
     * <pre>
     * The query which will be cancelled.
     * </pre>
     *
     * <code>int64 queryId = 1;</code>
     */
    public Builder setQueryId(long value) {
      
      queryId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The query which will be cancelled.
     * </pre>
     *
     * <code>int64 queryId = 1;</code>
     */
    public Builder clearQueryId() {
      
      queryId_ = 0L;
      onChanged();
      return this;
    }

    private boolean cancelStatus_ ;
    /**
     * <pre>
     * Cancel status of the current query.
     * </pre>
     *
     * <code>bool cancelStatus = 2;</code>
     */
    public boolean getCancelStatus() {
      return cancelStatus_;
    }
    /**
     * <pre>
     * Cancel status of the current query.
     * </pre>
     *
     * <code>bool cancelStatus = 2;</code>
     */
    public Builder setCancelStatus(boolean value) {
      
      cancelStatus_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Cancel status of the current query.
     * </pre>
     *
     * <code>bool cancelStatus = 2;</code>
     */
    public Builder clearCancelStatus() {
      
      cancelStatus_ = false;
      onChanged();
      return this;
    }

    private Object cancelError_ = "";
    /**
     * <pre>
     * Get the query cancel error if status is false.
     * </pre>
     *
     * <code>string cancelError = 3;</code>
     */
    public String getCancelError() {
      Object ref = cancelError_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        cancelError_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <pre>
     * Get the query cancel error if status is false.
     * </pre>
     *
     * <code>string cancelError = 3;</code>
     */
    public com.google.protobuf.ByteString
        getCancelErrorBytes() {
      Object ref = cancelError_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        cancelError_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * Get the query cancel error if status is false.
     * </pre>
     *
     * <code>string cancelError = 3;</code>
     */
    public Builder setCancelError(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      cancelError_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Get the query cancel error if status is false.
     * </pre>
     *
     * <code>string cancelError = 3;</code>
     */
    public Builder clearCancelError() {
      
      cancelError_ = getDefaultInstance().getCancelError();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Get the query cancel error if status is false.
     * </pre>
     *
     * <code>string cancelError = 3;</code>
     */
    public Builder setCancelErrorBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      cancelError_ = value;
      onChanged();
      return this;
    }
    @Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:QueryCancelResult)
  }

  // @@protoc_insertion_point(class_scope:QueryCancelResult)
  private static final QueryCancelResult DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new QueryCancelResult();
  }

  public static QueryCancelResult getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<QueryCancelResult>
      PARSER = new com.google.protobuf.AbstractParser<QueryCancelResult>() {
    @Override
    public QueryCancelResult parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new QueryCancelResult(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<QueryCancelResult> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<QueryCancelResult> getParserForType() {
    return PARSER;
  }

  @Override
  public QueryCancelResult getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

