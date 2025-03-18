package com.Foodics.Amazon.base.utils;

import java.io.*;
import java.util.Properties;

public class PropertiesUtils {
    public static Properties loadProperties(String filepath){
        File file  = new File(filepath);

        try {
            InputStream inputStream = new FileInputStream(file);

            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            return properties;

        } catch (FileNotFoundException e) {
            throw new RuntimeException("properties file not found  ");
        } catch (IOException e) {
            throw new RuntimeException("Not able to get properties  ");
        }

    }
}
