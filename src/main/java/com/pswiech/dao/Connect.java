package com.pswiech.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {

    static Connection conn = Connect.ConnectDB();

    public static Connection getConnection() {
        return conn;
    }

    public static Connection ConnectDB() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            try {
                c = DriverManager.getConnection("jdbc:sqlite:Registration.db");
            } catch (Exception e) {
                System.out.println("Could not connect to database");
            }
        } catch (Exception e) {
            System.out.println("Could not load org.sqlite.JDBC");
        }

        return c;
    }
}
