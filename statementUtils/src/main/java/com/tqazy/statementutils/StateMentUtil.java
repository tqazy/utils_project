package com.tqazy.statementutils;

import com.tqazy.properties.PropertiesUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 散场前的温柔
 */
public class StateMentUtil {
    private static Connection connection = null;
    private static Statement statement = null;

    /**
     * 执行SQL并返回改变行数
     * @param sql
     * @param path
     * @return
     */
    public static int stateMentUtils(String sql, String path){
        StateMentUtil.getConnection(path);
        // 如果创建连接失败，返回0行
        if(connection == null){
            System.out.println("创建数据库连接失败");
            return 0;
        }
        int num = 0;
        try {
            statement = connection.createStatement();
            num =  statement.executeUpdate(sql);
        } catch (SQLException e) {
            // 从连接中获取Statement异常
            e.printStackTrace();
        } finally {
            closeCon();
        }
        return num;
    }

    /**
     * 获取数据库连接
     * @return
     */
    public static void getConnection(String path){
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
        } catch (SQLException e){
            // 连接失败异常
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接的实例
     *
     */
    private static void closeCon(){
        try {
            if(statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
