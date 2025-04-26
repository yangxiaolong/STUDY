package org.example;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

/**
 * @auther yangxiaolong
 * @create 2025/4/26
 */
public class ConfigurationExample {
    public static void main(String[] args) {
        Parameters params = new Parameters();
        FileBasedConfigurationBuilder<PropertiesConfiguration> builder =
                new FileBasedConfigurationBuilder<>(PropertiesConfiguration.class).configure(params.properties()
                        .setFileName("config.properties"));
        try {
            PropertiesConfiguration config = builder.getConfiguration();
            String value = config.getString("example.key");
            System.out.println("Value of example.key: " + value);
        } catch (ConfigurationException e) {
            System.err.println("Error loading configuration: " + e.getMessage());
        }
    }
}