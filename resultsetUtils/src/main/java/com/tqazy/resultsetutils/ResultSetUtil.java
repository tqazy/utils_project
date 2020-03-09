package com.tqazy.resultsetutils;

import com.tqazy.properties.PropertiesUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 散场前的温柔
 */
public class ResultSetUtil {

    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;


    public static ResultSet resultSetUtils(String sql, String path) {
        ResultSetUtil.getConnection(path);
        // 如果创建连接失败，返回0行
        if (connection == null) {
            System.out.println("创建数据库连接失败");
        }

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /**
     * 关闭resultSet、statement、connection连接
     */
    public static void closeCon() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static void getConnection(String path) {
        // 获取数据库连接信息
        List<String> list = new ArrayList<String>();
        list.add("databaseDriver");
        list.add("databaseUrl");
        list.add("user");
        list.add("password");
        // 读取jdbc.properties文件中的信息
        Map<String, String> map = PropertiesUtils.readProperties(path, list);
        String databaseDriver = map.get("databaseDriver");
        String databaseUrl = map.get("databaseUrl");
        String user = map.get("user");
        String password = map.get("password");
        // 获取数据库的连接
        try {
            Class.forName(databaseDriver);
            connection = DriverManager.getConnection(databaseUrl, user, password);
        } catch (ClassNotFoundException e) {
            // 数据库驱动没找到异常
            e.printStackTrace();
        } catch (SQLException e) {
            // 连接失败异常
            e.printStackTrace();
        }
    }

}
