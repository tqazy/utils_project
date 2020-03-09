package com.tqazy.statementutils;

import org.junit.Test;

public class TestStateMentUtils {

    @Test
    public void TestStateMent(){
        String sql = "INSERT  INTO user(name, age, remark) values ('樟道',21,'这是一个活泼的男孩')";
        String path = "jdbc.properties";
        int num = StateMentUtil.stateMentUtils(sql, path);
        System.out.println("改变行数：" + num);
    }
}
