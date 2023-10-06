package com.oneaston.tap.automation.ui.utils.dbutil;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.oneaston.tap.automation.ui.utils.dataprovider.TestProperties;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionManager {

    final SQLServerDataSource ds;
    protected TestProperties prop;
    private Connection con;

    public DBConnectionManager() {
        prop = TestProperties.getInstance();
        ds = new SQLServerDataSource();
    }

    private Connection setConnection() throws SQLServerException {
        ds.setUser(prop.getProperty("db_user"));
        ds.setPassword(prop.getProperty("db_pass"));
        ds.setServerName(prop.getProperty("db_host"));
        ds.setPortNumber(Integer.parseInt(prop.getProperty("db_port")));
        ds.setDatabaseName(prop.getProperty("db_name"));
        return ds.getConnection();
    }

    public Statement createStatement() throws SQLException {
        con = setConnection();
        return con.createStatement();
    }

    public PreparedStatement createPreparedStatement(String query) throws SQLException {
        con = setConnection();
        return con.prepareStatement(query);
    }

    public void closeConnection() throws SQLException {
        if (con != null) {
            con.close();
        }
    }

}
