package com.bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;

public class DBConnection {
    private static String url, user, pass, driver;
    static {
        try (InputStream in = DBConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties p = new Properties();
            p.load(in);
            url = p.getProperty("jdbc.url");
            user = p.getProperty("jdbc.username");
            pass = p.getProperty("jdbc.password");
            driver = p.getProperty("jdbc.driver");
            Class.forName(driver);
        } catch (Exception e) {
            throw new RuntimeException("DB config load failed", e);
        }
    }
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(url, user, pass);
    }
}
