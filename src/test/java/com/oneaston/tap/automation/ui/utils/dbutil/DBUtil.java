package com.oneaston.tap.automation.ui.utils.dbutil;

import org.testng.annotations.AfterTest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

    private final DBConnectionManager dbConnectionManager;
    private PreparedStatement preparedStatement;
    private Statement statement;

    public DBUtil() throws SQLException {
        dbConnectionManager = new DBConnectionManager();
    }

    public ResultSet selectQuery(String query) throws SQLException {
        statement = dbConnectionManager.createStatement();
        return statement.executeQuery(query);
    }

    public int deleteQuery(String query) throws SQLException {
        preparedStatement = dbConnectionManager.createPreparedStatement(query);
        return preparedStatement.executeUpdate();
    }

    @AfterTest(groups = "testDataRefresh")
    void closeConnection() {
        try {
            closeStatements();
            dbConnectionManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeStatements() throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
}
