package com.github.muzhaleks.dao.impl;

import com.github.muzhaleks.connectionpool.ConnectionPool;
import com.github.muzhaleks.connectionpool.ConnectionProxy;
import com.github.muzhaleks.dao.UserDAO;
import com.github.muzhaleks.exceptions.DAOException;
import com.github.muzhaleks.model.Role;
import com.github.muzhaleks.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class UserDAOImpl implements UserDAO {

    private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class);
    //fields
    private static final String USER_ID = "id";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String USER_ROLE = "role";
    private static final String ACTIVE_USER_STATUS = "active_status";
    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String PASSPORT_SERIAL_NUMBER = "passport_serial_number";
    private static final String DRIVER_LICENCE_NUMBER = "driver_licence_number";
    private static final String DATE_OF_REGISTRATION = "date_of_registration";

    //query
    private static final String IS_USER_BY_LOGIN_QUERY = "SELECT login FROM user WHERE login = ";

    private static final String IS_USER_BY_EMAIL_QUERY = "SELECT email FROM user WHERE email = ";

    private static final String GET_USER_BY_LOGIN_QUERY = "SELECT user.id, login, password, role, active_status," +
            " phone_number,email, first_name, last_name, passport_serial_number,\n" +
            " driver_licence_number, date_of_registration FROM user INNER JOIN role r ON user.role_id = r.id WHERE user.login = ";

    private static final String GET_USER_BY_EMAIL_QUERY = "SELECT user.id, login, password, role, active_status, " +
            "phone_number,email, first_name, last_name, passport_serial_number,\n" +
            " driver_licence_number, date_of_registration FROM user INNER JOIN role r ON user.role_id = r.id WHERE user.email = ";

    private static final String GET_USER_BY_ID_QUERY = "SELECT user.id, login, password, role, active_status, " +
            "phone_number,email, first_name, last_name, passport_serial_number,\n" +
            " driver_licence_number, date_of_registration FROM user INNER JOIN role r ON user.role_id = r.id WHERE user.id = ";

    private static final String GET_ALL_USERS_QUERY = "SELECT user.id, login, password, role, active_status," +
            " phone_number,email, first_name, last_name, passport_serial_number,\n" +
            " driver_licence_number, date_of_registration FROM user INNER JOIN role r ON user.role_id = r.id";

    private static final String REGISTRATION_USER_QUERY = "INSERT INTO user (login, password, first_name, last_name, " +
            "passport_serial_number, driver_licence_number, date_of_registration, email, phone_number)\n" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String CHANGE_USER_PASSWORD_QUERY = "UPDATE user SET password = ? WHERE id = ";

    private static final String BLOCK_USER_QUERY = "UPDATE user SET active_status = false WHERE id = ";

    private static final String UNBLOCK_USER_QUERY = "UPDATE user SET active_status = true WHERE id = ";

    private static final String DELETE_USER_FROM_DB_QUERY = "DELETE from users where id = ";


    @Override
    public void registrationUser(User newUser) throws DAOException {
        User user = new User();
        try (
                ConnectionProxy connection = new ConnectionProxy(ConnectionPool.INSTANCE.getConnection());
                PreparedStatement preparedStatement = connection.prepareStatement(REGISTRATION_USER_QUERY);
        ) {
            preparedStatement.setString(1, newUser.getLogin());
            preparedStatement.setString(2, newUser.getPassword());
            preparedStatement.setString(3, newUser.getFirstName());
            preparedStatement.setString(4, newUser.getLastName());
            preparedStatement.setString(5, newUser.getPassportSerialNumber());
            preparedStatement.setString(6, newUser.getDriverLicenceNumber());
            preparedStatement.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setString(8, newUser.getEmail());
            preparedStatement.setString(9, newUser.getPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException("Can`t create user");
        }
    }

    @Override
    public boolean isUserByLogin(String login) throws DAOException {
        try (
                ConnectionProxy connection = new ConnectionProxy(ConnectionPool.INSTANCE.getConnection());
                PreparedStatement preparedStatement = connection.prepareStatement(IS_USER_BY_LOGIN_QUERY + "\'" + login + "\'");
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException("Can`t check user by login");
        }
    }


    @Override
    public boolean isUserByEmail(String email) throws DAOException {
        try (
                ConnectionProxy connection = new ConnectionProxy(ConnectionPool.INSTANCE.getConnection());
                PreparedStatement preparedStatement = connection.prepareStatement(IS_USER_BY_EMAIL_QUERY + "\'" + email + "\'");
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException("Can`t check user by email");
        }
    }

    @Override
    public User getUserByID(long id) throws DAOException {
        User user = new User();
        try (
                ConnectionProxy connection = new ConnectionProxy(ConnectionPool.INSTANCE.getConnection());
                PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID_QUERY + id);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            buildUserByResultSet(user, resultSet);
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException("Can`t get user by ID");
        }
        return user;
    }


    @Override
    public User getUserByLogin(String login) throws DAOException {
        User user = new User();
        try (
                ConnectionProxy connection = new ConnectionProxy(ConnectionPool.INSTANCE.getConnection());
                PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN_QUERY + "\'" + login + "\'");
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            buildUserByResultSet(user, resultSet);
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException("Can`t get user by login");
        }
        return user;
    }

    @Override
    public User getUserBYEmail(String email) throws DAOException {
        User user = new User();
        try (
                ConnectionProxy connection = new ConnectionProxy(ConnectionPool.INSTANCE.getConnection());
                PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL_QUERY + "\'" + email + "\'");
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            buildUserByResultSet(user, resultSet);
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException("Can`t get user by email");
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() throws DAOException {
        List <User> users = new ArrayList<>();

        try (
                ConnectionProxy connection = new ConnectionProxy(ConnectionPool.INSTANCE.getConnection());
                PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS_QUERY);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                User user = new User();
                Role role = new Role();
                role.setRole(resultSet.getString(USER_ROLE));
                user.setId(resultSet.getLong(USER_ID));
                user.setLogin(resultSet.getString(LOGIN));
                user.setPassword(resultSet.getString(PASSWORD));
                user.setRole(role);
                user.setActiveStatus(resultSet.getBoolean(ACTIVE_USER_STATUS));
                user.setPhoneNumber(resultSet.getString(PHONE_NUMBER));
                user.setEmail(resultSet.getString(EMAIL));
                user.setFirstName(resultSet.getString(FIRST_NAME));
                user.setLastName(resultSet.getString(LAST_NAME));
                user.setPassportSerialNumber(resultSet.getNString(PASSPORT_SERIAL_NUMBER));
                user.setDriverLicenceNumber(resultSet.getString(DRIVER_LICENCE_NUMBER));
                user.setDateOfRegistration(resultSet.getTimestamp(DATE_OF_REGISTRATION));
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException("Can`t get users ");
        }
        return users;
    }

    @Override
    public void changeUserPassword(long userID, String newPassword) throws DAOException {
        try (
                ConnectionProxy connection = new ConnectionProxy(ConnectionPool.INSTANCE.getConnection());
                PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_USER_PASSWORD_QUERY + userID);
        ) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException("Can`t change password");
        }
    }

    @Override
    public void blockUser(long userID) throws DAOException {
        try (
                ConnectionProxy connection = new ConnectionProxy(ConnectionPool.INSTANCE.getConnection());
                PreparedStatement preparedStatement = connection.prepareStatement(BLOCK_USER_QUERY + userID);
        ) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException("Can`t block user");
        }
    }

    @Override
    public void unblockUser(long userID) throws DAOException {
        try (
                ConnectionProxy connection = new ConnectionProxy(ConnectionPool.INSTANCE.getConnection());
                PreparedStatement preparedStatement = connection.prepareStatement(UNBLOCK_USER_QUERY + userID);
        ) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException("Can`t unblock user");
        }
    }

    @Override
    public void deleteUserFromDB(long userID) throws DAOException {
        try (
                ConnectionProxy connection = new ConnectionProxy(ConnectionPool.INSTANCE.getConnection());
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_FROM_DB_QUERY + userID);
        ) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException("Can`t delete user from DB");
        }
    }

    private void buildUserByResultSet(User user, ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            Role role = new Role();
            role.setRole(resultSet.getString(USER_ROLE));
            user.setId(resultSet.getLong(USER_ID));
            user.setLogin(resultSet.getString(LOGIN));
            user.setPassword(resultSet.getString(PASSWORD));
            user.setRole(role);
            user.setActiveStatus(resultSet.getBoolean(ACTIVE_USER_STATUS));
            user.setPhoneNumber(resultSet.getString(PHONE_NUMBER));
            user.setEmail(resultSet.getString(EMAIL));
            user.setFirstName(resultSet.getString(FIRST_NAME));
            user.setLastName(resultSet.getString(LAST_NAME));
            user.setPassportSerialNumber(resultSet.getNString(PASSPORT_SERIAL_NUMBER));
            user.setDriverLicenceNumber(resultSet.getString(DRIVER_LICENCE_NUMBER));
            user.setDateOfRegistration(resultSet.getTimestamp(DATE_OF_REGISTRATION));
        }
    }
}
