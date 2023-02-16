package com.envmonitor.Utils;
/**
 * JDBC工具类
 */

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;


    // 静态方法不必生成对象就能调用
    static {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("src/db.properties"));

            driver = props.getProperty("driver");
            url = props.getProperty("url");
            user = props.getProperty("user");
            password = props.getProperty("password");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建数据库链接
     * @return 返回当前数据库链接
     */
    public static Connection getConnection() {
        try {
            Class.forName(driver);

            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 释放资源
     * @param stmt sql statement
     * @param conn 数据库链接
     * @param params 不定的其他需要释放资源的参数
     */
    public static void close(PreparedStatement stmt, Connection conn, Object ... params) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (params.length > 0) {
            if (params[0] instanceof ResultSet) {
                try {
                    ResultSet ret = (ResultSet) params[0];
                    ret.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
