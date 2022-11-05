package com.test.task.framework.reader;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    static String propertiesFolder = "src/test/resources/configuration/";


    public static String getPropertyValue(String fileName,String propertyName) {
        Properties prop = new Properties();
        try {
            FileReader fReader=new FileReader(propertiesFolder + fileName.toUpperCase()+".properties");
            prop.load(fReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(propertyName);
    }
}
