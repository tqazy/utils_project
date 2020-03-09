package com.tqazy.properties;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestPropertiesUtils {
    @Test
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("english_string");
        list.add("chinese_string");
        list.add("special_string");

        Map<String, String> map = PropertiesUtils.readProperties("test.properties", list);
        System.out.println("english_string:" + map.get("english_string"));
        System.out.println("chinese_string:" + map.get("chinese_string"));
        System.out.println("special_string:" + map.get("special_string"));
    }
}
