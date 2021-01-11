package ruan.provider.config;

import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Logger;
import javax.sql.DataSource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 自定义mysql连接池
 */
@Slf4j
@Component
public class JdbcConnectionPool implements DataSource {

    static String CONNECTION_URL = "";
    static String CONNECTION_USERNAME = "";
    static String CONNECTION_PASSWORD = "";
    static String CONNECTION_DRIVER = "";
    private static Properties properties = new Properties();
    static int MAX = 10;
    static int INIT = 10;

    static LinkedList<Connection> connectionLinkedList = new LinkedList<>();

    static {
        try (InputStream resourceAsStream = Object.class.getResourceAsStream("/mysql-connect.properties")) {
            properties.load(resourceAsStream);
            CONNECTION_USERNAME = properties.getProperty("mysql.username").trim();
            CONNECTION_PASSWORD = properties.getProperty("mysql.password").trim();
            CONNECTION_URL = properties.getProperty("mysql.url").trim();
            CONNECTION_DRIVER = properties.getProperty("mysql.driver-class-name").trim();
            Class.forName(CONNECTION_DRIVER);
            while (connectionLinkedList.size() <= INIT) {
                Connection connection = DriverManager.getConnection(CONNECTION_URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
                connectionLinkedList.add(connection);
            }
        } catch (Exception e) {
            log.error("", e);
        }
    }

    @SneakyThrows
    @Override
    public Connection getConnection() throws SQLException {
        if (connectionLinkedList.size() == 0) {
            Class.forName("com.mysql.cj.jdbc.Driver".trim());
            while (connectionLinkedList.size() <= INIT) {
                connectionLinkedList.add(DriverManager.getConnection(CONNECTION_URL,
                        CONNECTION_USERNAME, CONNECTION_PASSWORD));
            }
        }
        if (connectionLinkedList.size() > 0) {
            Connection connection = connectionLinkedList.removeFirst();
            return (Connection) Proxy.newProxyInstance(JdbcConnectionPool.class.getClassLoader(),
                    new Class[]{Connection.class},
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args)
                                throws Throwable {
                            if (!method.getName().equalsIgnoreCase("close")) {
                                return method.invoke(connection, args);
                            } else {
                                connectionLinkedList.add(connection);
                            }
                            return null;
                        }
                    });
        }
        return null;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
