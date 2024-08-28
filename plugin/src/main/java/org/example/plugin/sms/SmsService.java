package org.example.plugin.sms;

import org.springframework.plugin.core.Plugin;

/**
 * @auther yangxiaolong
 * @create 2024/8/28
 */
public interface SmsService extends Plugin<String> {

    void sendSms(String mobile);
}