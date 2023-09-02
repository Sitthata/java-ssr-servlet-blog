package com.example.blog.utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
    private static final String DB_HOST = "jdbc:mysql://localhost:3306/blog";
    private static final String DB_NAME = "root";
    private static final String DB_PASS = "";
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_HOST, DB_NAME, DB_PASS);
            return connection;
        } catch (Exception e) {
            System.out.println("Couldn't connect to database");
            return null;
        }
    }
}
