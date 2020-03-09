package com.tqazy.resultsetutils;

import org.junit.Test;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestResultSet {

    @Test
    public void testResultSet() {
        String sql = "SELECT * FROM user";
        String path = "jdbc.properties";

        ResultSet resultSet = ResultSetUtil.resultSetUtils(sql, path);

        try {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String age = resultSet.getString("age");
                String remark = resultSet.getString("remark");

                System.out.println("id:" + id);
                System.out.println("name:" + name);
                System.out.println("age:" + age);
                System.out.println("remark:" + remark + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResultSetUtil.closeCon();
        }

    }
}
