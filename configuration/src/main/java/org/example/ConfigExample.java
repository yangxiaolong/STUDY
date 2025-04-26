package org.example;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * @auther yangxiaolong
 * @create 2025/4/26
 */
public class ConfigExample {
    public static void main(String[] args) {
        try {
            Configuration config = new PropertiesConfiguration("config.properties");
            String value = config.getString("example.key");
            System.out.println("Value of example.key: " + value);
        } catch (ConfigurationException e) {
            System.err.println("Error loading configuration: " + e.getMessage());
        }
    }
}