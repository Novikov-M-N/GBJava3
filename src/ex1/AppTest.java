package ex1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class AppTest {

    private static Connection connection;

    private Connection getConnection() throws SQLException {
        String URL = "jdbc:h2:mem:test";
        String user = "user";
        String password = "password";

        return DriverManager.getConnection("jdbc:h2:mem:test");
    }

    private int executeUpdate(String query) throws SQLException{
        Statement statement = connection.createStatement();
        int result = statement.executeUpdate(query);
        return result;
    }

    private void createResultSet() throws SQLException {
        String query = "SELECT * FROM ... WHERE name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, "IVAN");
        boolean result = preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        resultSet.next();
    }

    @Before
    public void init() throws SQLException {
        connection = getConnection();
    }

    @After
    public void close() throws SQLException {
        connection.close();
    }

}
