package lesson3Homework.auth;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервис авторизации через БД
 */
public class DatabaseAuthService implements AuthenticationService {
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    /**
     * Запуск сервиса - создаём подключение к БД
     */
    public void start() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:chat.s3db");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Authentication service started");
    }

    /**
     * Остановка сервиса - освобождаем ресурсы
     */
    public void stop() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Authentication service stopped");
    }

    /**
     * Запрос из БД ника пользователя по логину и паролю
     * @param login
     * @param password
     * @return ник пользователя, если логин и пароль указаны верно,
     * null, если пользователя с таким логином и паролем нет в базе
     */
    public String getNickByLoginAndPwd(String login, String password) {
        try {
            statement = connection.prepareStatement(
                    "SELECT nick FROM users WHERE name = ? AND password = ?");
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            String result = null;
            if (resultSet.next()) {
                result = resultSet.getString(1);
            }
            resultSet.close();
            statement.close();
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Проверяет, нет ли в БД пользователей уже пользователя с заданным ником
     * @param nick искомый ник пользователся
     * @return true, если пользователь с ником nick найден, false - если нет
     */
    public boolean isNickExist(String nick) {
        boolean result = false;
        try {
            statement = connection.prepareStatement(
                    "SELECT nick FROM users WHERE nick = ?");
            statement.setString(1, nick);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    /**
     * Изменяет ник пользователя с ником oldNick на newNick в БД пользователей
     * @param oldNick старый ник пользователя
     * @param newNick новый ник пользователся
     * @return true - если смена ника прошла успешно, false - если неудачно
     */
    public boolean changeNick(String oldNick, String newNick) {
        boolean result = false;
        try {
            statement = connection.prepareStatement(
                    "UPDATE users SET nick = ? WHERE nick = ?");
            statement.setString(1, newNick);
            statement.setString(2, oldNick);
            if (statement.executeUpdate() > 0){
                result = true;
            }
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
