package lesson3Homework;

import java.sql.*;

/**
 * Класс для манипуляций с базой данных вручную, к основному заданию отношения не имеет.
 */
public class DBInteraction {
    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;

    public static void main(String[] args) {
        connection = null;
//        try {
//            Class.forName("org.sqlite.JDBC");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:chat.s3db");
            System.out.println("Подключение к БД выполнено");
            statement = connection.createStatement();
//            statement.execute("INSERT INTO users (name,nick,password) VALUES ('login5','nick5','password5')");
            resultSet = statement.executeQuery("SELECT * FROM users");
//            System.out.println(resultSet.getMetaData());
            int columnCount = resultSet.getMetaData().getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                System.out.print(String.format("%" + 12 + "s", resultSet.getMetaData().getColumnLabel(i+1)));
            }
            System.out.println("");
            while (resultSet.next()) {
                for (int i = 0; i < columnCount; i++) {
                    System.out.print(String.format("%" + 12 + "s", resultSet.getString(i+1)));
                }
                System.out.println("");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
