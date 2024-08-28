package org.example;

import org.example.plugin.email.EmailService;
import org.example.plugin.people.PeoplePlugin;
import org.example.plugin.sms.SmsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.plugin.core.config.EnablePluginRegistries;

/**
 * @author yangxiaolong
 */
@SpringBootApplication
@EnablePluginRegistries({SmsService.class, EmailService.class, PeoplePlugin.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

