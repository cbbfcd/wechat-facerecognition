package com.example.demo.start;


import com.example.demo.utils.GlobalConstants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author huangteng
 * @version 1.0.0
 * @description 项目启动初始化方法
 * @time 16:22 2017/11/22
 * @modified by:
 * @modified time:
 */
public class InterfaceUrlInti {
    public synchronized static void init(){
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        if(GlobalConstants.interfaceUrlProperties == null){
            GlobalConstants.interfaceUrlProperties = new Properties();
        }
        InputStream in = null;
        try{
            in = cl.getResourceAsStream("application.properties");
            props.load(in);
            for(Object key: props.keySet()){
                GlobalConstants.interfaceUrlProperties.put(key, props.get(key));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
