package com.example.demo.passwordless.jdbc;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import org.junit.jupiter.api.Test;

import javax.sql.PooledConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HelloWorld {
    @Test
    void helloworld() {

    }

    @Test
    public void getServerTimeUmi() throws SQLException {

        MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
        ds.setUrl(
                "jdbc:mysql://single-mysql-gzh.mysql.database.azure.com:3306/?"
                        + "useSSL=true&requireSSL=true"
                        + "&defaultAuthenticationPlugin=com.azure.identity.providers.mysql.AzureIdentityMysqlAuthenticationPlugin"
                        + "&authenticationPlugins=com.azure.identity.providers.mysql.AzureIdentityMysqlAuthenticationPlugin"
//                + "&paranoid=true"
        );
        ds.setUser("zhihaoguo@microsoft.com@single-mysql-gzh");

        PooledConnection connectionPooled = ds.getPooledConnection();
        if (connectionPooled != null) {
            Connection connection = connectionPooled.getConnection();
            if (connection != null) {
                System.out.println("Successfully connected.");
                System.out.println(connection.isValid(10));
                ResultSet result = connection.prepareStatement("SELECT now() as now").executeQuery();
                if (result.next()) {
                    assertNotNull(result.getString("now"));
                }
            }
        }
    }
    @Test
    void singleMySQLNativePassword() throws SQLException {
        String url = "jdbc:mysql://single-mysql-gzh.mysql.database.azure.com:3306/?useSSL=true";
        String user = "rootsingle@single-mysql-gzh";
        String password = "password@";
        DriverManager.getConnection(url, user, password);
    }

    @Test
    void singleMySQLAAD() throws SQLException {
        String url = "jdbc:mysql://single-mysql-gzh.mysql.database.azure.com:3306/?useSSL=true";
        String user = "zhihaoguo@microsoft.com@single-mysql-gzh";
        String password = "token";
        DriverManager.getConnection(url, user, password);
    }

    @Test
    void flexMySQLAAD() throws SQLException {
        String url = "jdbc:mysql://flex-mysql-gzh.mysql.database.azure.com:3306/?useSSL=true";
        String user = "zhihaoguo@microsoft.com";
        String password = "token";
        DriverManager.getConnection(url, user, password);
    }

}
