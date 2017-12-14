package com.example.demo.utils;

import com.example.demo.start.InterfaceUrlInti;

import java.util.Properties;

/**
 * @author huangteng
 * @version 1.0.0
 * @description for world peace
 * @time 16:31 2017/11/22
 * @modified by:
 * @modified time:
 */
public class GlobalConstants {
    public static Properties interfaceUrlProperties;
    static {
        if(GlobalConstants.interfaceUrlProperties == null){
            InterfaceUrlInti.init();
        }
    }

    /**
     * 根据不同类型取值
     */
    public static String getString(String key){
        String properties = (String) interfaceUrlProperties.get(key);
        return properties == null ? null : properties;
    }

    public static Integer getInt(String key){
        String properties = (String) interfaceUrlProperties.get(key);
        return properties == null ? null : Integer.parseInt(properties);
    }

    public static boolean getBoolean(String key){
        String properties = (String) interfaceUrlProperties.get(key);
        return properties == null ? null : Boolean.valueOf(properties);
    }

    public static long getLong(String key){
        String properties = (String) interfaceUrlProperties.get(key);
        return properties == null ? null : Long.valueOf(properties);
    }
}
