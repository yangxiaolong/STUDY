package org.example.plugin.sms;

import org.springframework.stereotype.Service;

/**
 * @auther yangxiaolong
 * @create 2024/8/28
 */
@Service
public class SmsServiceImpl2 implements SmsService {

    @Override
    public boolean supports(String s) {
        return s.startsWith("138");
    }

    @Override
    public void sendSms(String mobile) {
        if (supports(mobile)) {
            System.out.println("SmsServiceImpl2 发送短信成功：" + mobile);
        }
    }

}