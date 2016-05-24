package com.pswiech.dao;

import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterDao {

    static {
        initDb();
    }

    public static boolean isValidUserName(String username) {
        boolean valid = false;
        if ((username != null) && isAlphaNumeric(username) && isFiveOrMoreCharsLong(username)) {
            valid = true;
        }
        return valid;
    }

    public static boolean isAlreadyRegistered(String username) {
        boolean status = false;

        Connection conn = Connect.getConnection();

        if (conn != null) {
            PreparedStatement pst = null;
            ResultSet rs = null;
            try {
                pst = conn.prepareStatement("select * from user where username=?");
                pst.setString(1, username);
                rs = pst.executeQuery();
                if (rs.next()) {
                    System.out.println("User already exists");
                    status = true;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return status;
    }

    protected static boolean isAlphaNumeric(String text) {
        boolean status = false;

        if ((text != null) && (text.length() > 0)) {
            status = text.equals(text.replaceAll("[^A-Za-z0-9]", ""));
        }
        return status;
    }

    public static boolean isValidPassword(String password) {
        boolean status = false;

        if ((password != null) && isEightOrMoreCharsLong(password) && containsNumber(password)
                && containsUpperCase(password) && containsLowerCase(password)) {
            status = true;
        }
        return status;
    }

    protected static boolean containsUpperCase(String text) {
        return !text.equals(text.toLowerCase());
    }

    protected static boolean containsLowerCase(String text) {
        return !text.equals(text.toUpperCase());
    }

    protected static boolean isFiveOrMoreCharsLong(String text) {
        return hasMinimalLength(text, 5);
    }

    protected static boolean isEightOrMoreCharsLong(String text) {
        return hasMinimalLength(text, 8);
    }

    private static boolean hasMinimalLength(String str, int len) {
        boolean status = false;

        if (len <= 0) {
            throw new InvalidParameterException();
        }

        if ((null != str) && (str.length() >= len)) {
            status = true;
        }
        return status;
    }

    protected static boolean containsNumber(String str) {
        boolean status = false;

        if (null != str) {
            Pattern p = Pattern.compile("([0-9])");
            Matcher m = p.matcher(str);

            if (m.find()) {
                status = true;
            }
        }
        return status;
    }

    public static boolean perstoreUser(String username, String password) {
        boolean status = false;

        Connection conn = Connect.getConnection();
        Statement stmt = null;

        String sql = "INSERT INTO user (username, password)" + " VALUES ('" + username + "','"
                + password + "')";

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (Exception e) {
        }
        return status;
    }

    public static boolean initDb() {
        boolean status = false;

        Connection conn = Connect.getConnection();
        Statement stmt = null;

        String sql = "CREATE TABLE IF NOT EXISTS user" + "("
                + "Id INTEGER PRIMARY KEY AUTOINCREMENT," + "Username VARCHAR(30) NOT NULL,"
                + "Password VARCHAR(30) NOT NULL" + ");";

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            System.err.println("Could not initialize database!");
        }
        return status;
    }
}
