package com.github.muzhaleks.connectionpool;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public enum ConnectionPool {

    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static final int POOL_CAPACITY = 20;
    private static final String DATABASE_DRIVER = "db.driver";
    private static final String DATABASE_PROPERTIES_FILE_NAME = "database.properties";
    private static final String DATABASE_USER_PROPERTY = "db.user";
    private static final String DATABASE_PASSWORD_PROPERTY = "db.password";
    private static final String DATABASE_URL_PROPERTY = "db.url";
    private AtomicBoolean isPoolAlreadyInitiated = new AtomicBoolean(false);

    private BlockingQueue<Connection> availableConnections;
    private List<Connection> usedConnection;

    ConnectionPool() {
        availableConnections = new LinkedBlockingQueue<>();
        usedConnection = new ArrayList<>();
    }

    public void initPool() {
        if (!isPoolAlreadyInitiated.get()) {
            Properties databaseProperties = new Properties();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(DATABASE_PROPERTIES_FILE_NAME);
            try {
                databaseProperties.load(inputStream);
                String user = databaseProperties.getProperty(DATABASE_USER_PROPERTY);
                char[] password = databaseProperties.getProperty(DATABASE_PASSWORD_PROPERTY).toCharArray();
                String url = databaseProperties.getProperty(DATABASE_URL_PROPERTY);
                String driver = databaseProperties.getProperty(DATABASE_DRIVER);

                Class.forName(driver);

                for (int i = 0; i < POOL_CAPACITY; i++) {
                    availableConnections.add(new ConnectionProxy(DriverManager.getConnection(url, user, new String(password))));
                }
                isPoolAlreadyInitiated.set(true);

            } catch (ClassNotFoundException | IOException e) {
                LOGGER.fatal(e);
            } catch (SQLException e) {
                LOGGER.warn(e);
            }
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = availableConnections.take();
            usedConnection.add(connection);
        } catch (InterruptedException e) {
            LOGGER.warn(e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        try {
            if (connection != null) {
                if (!usedConnection.remove(connection)) {
                    LOGGER.debug("Connection do not remove from usedConnection");
                }
                availableConnections.put(connection);
            }
        } catch (InterruptedException e) {
            LOGGER.warn(e);
        }
    }

    public void closePool() {
        if (isPoolAlreadyInitiated.get()) {
            if (!usedConnection.isEmpty()) {
                LOGGER.warn("Connection used");
            }
            closeAvailableConnection(availableConnections);
            closeUsedConnection(usedConnection);
        }
    }

    private void closeAvailableConnection(BlockingQueue<Connection> connections) {
        while (!connections.isEmpty()) {
            try {
                connections.take().close();
            } catch (SQLException e) {
                LOGGER.warn("Cant close connection", e);
            } catch (InterruptedException e) {
                LOGGER.warn(e);
            }
        }
    }

    private void closeUsedConnection(List<Connection> connections) {
        for (Connection connection : connections) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.warn("Cant close connection", e);
            }

        }
    }
}
