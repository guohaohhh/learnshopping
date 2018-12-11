package com.neuedu.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {
    private static Properties properties=new Properties();
    static{
        try {
            InputStream inputStream=new FileInputStream("D:\\git的创建\\Ilernshopping\\src\\main\\resources\\db.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 读取数据里面的内容
     * */

    public static String readByKey(String key){
        return properties.getProperty(key);
    }

//    public static void main(String[] args) {
//        System.out.println(readByKey("imageHost"));
//    }
}
