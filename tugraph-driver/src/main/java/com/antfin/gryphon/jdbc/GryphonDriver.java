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
package com.antfin.gryphon.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

public class GryphonDriver implements Driver {

    static {
        try {
            DriverManager.registerDriver(new GryphonDriver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String GRYPHON_URL_PREFIX = "jdbc:TuGraph://";

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        if (!this.acceptsURL(url)) {
            return null;
        }
        return GryphonConnection.getInstance(url, info);
    }

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        return url.startsWith(GRYPHON_URL_PREFIX);
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return new DriverPropertyInfo[0];
    }

    @Override
    public int getMajorVersion() {
        return 0;
    }

    @Override
    public int getMinorVersion() {
        return 0;
    }

    @Override
    public boolean jdbcCompliant() {
        return false;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new UnsupportedOperationException();
    }

}
