package org.example.controller;

import org.example.plugin.email.EmailService;
import org.example.plugin.people.PeoplePlugin;
import org.example.plugin.people.PeoplePluginType;
import org.example.plugin.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auther yangxiaolong
 * @create 2024/8/28
 */
@RestController
@RequestMapping("api/plugin/")
public class PluginController {

    @Autowired
    private PluginRegistry<PeoplePlugin, PeoplePluginType> peoplePlugins;

    @Autowired
    private PluginRegistry<SmsService, String> smsServices;

    @Autowired
    private PluginRegistry<EmailService, String> emailService;

    @GetMapping("eat")
    public void eat() {
        List<PeoplePlugin> list = peoplePlugins.getPlugins();
        for (PeoplePlugin people : list) {
            String s = people.eat(PeoplePluginType.WHITE);
            System.out.println(s);
        }
    }

    @GetMapping("sms")
    public void sms() {
        List<SmsService> list = smsServices.getPlugins();
        for (SmsService sms : list) {
            sms.sendSms("139111111");
            sms.sendSms("138111111");
        }
    }

    @GetMapping("email")
    public void email() {
        List<EmailService> list = emailService.getPlugins();
        for (EmailService eamil : list) {
            eamil.sendEmail("qq.com");
            eamil.sendEmail("gmail.com");
        }
    }
}

