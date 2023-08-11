
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thecoffee.utils;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ngocduc
 */
public class XJdbc {

//    public static String driver = "com.mysql.cj.jdbc.Driver";
//    public static String dburl = "jdbc:mysql://free02.123host.vn:3306/bpaikkrk_thecoffee";
//    public static String username = "bpaikkrk_thecoffee";
//    public static String password = "1234";
    public static String driver = "com.mysql.cj.jdbc.Driver";
    public static String dburl = "jdbc:mysql://localhost:3306/thecoffee";
    public static String username = "root";
    public static String password = "1234";
    public static Connection conn;
    public static Connection con1;

    static {
        try {
            Class.forName(driver);
            con1 = DriverManager.getConnection(dburl, username, password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException ex) {
            Logger.getLogger(XJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static PreparedStatement preparedStatement(String sql, Object... args) throws SQLException {
        conn = DriverManager.getConnection(dburl, username, password);
        PreparedStatement pstmt = null;
        if (sql.trim().startsWith("{")) {
            pstmt = conn.prepareCall(sql); //proc
        } else {
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); //SQL
        }
        for (int i = 0; i < args.length; i++) {
            pstmt.setObject(i + 1, args[i]);
        }
        return pstmt;
    }

    public static ResultSet executeQuery(String sql, Object... args) {
        try {
            PreparedStatement pstmt = preparedStatement(sql, args);
            try {
                return pstmt.executeQuery();
            } finally {

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void executeUpdate(String sql, Object... args) {
        try {
            PreparedStatement pstmt = preparedStatement(sql, args);
            try {
                pstmt.executeUpdate();
            } finally {
                pstmt.getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int insertAndGenerateID(String sql, Object... args) {
        int id = -1;
        try {
            PreparedStatement pstmt = preparedStatement(sql, args);
            try {
                pstmt.executeUpdate();
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
                rs.close();
            } finally {
                pstmt.getConnection().close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return id;
        }
        return id;
    }
}
