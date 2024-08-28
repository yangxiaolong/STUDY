package org.example.plugin.email;

import org.springframework.stereotype.Service;

/**
 * @auther yangxiaolong
 * @create 2024/8/28
 */
@Service
public class EmailServiceImpl2 implements EmailService {
    @Override
    public boolean supports(String s) {
        return s.endsWith("gmail.com");
    }

    @Override
    public void sendEmail(String s) {
        if (supports(s)) {
            System.out.println("EmailServiceImpl2 发送邮件成功：" + s);
        }
    }
}
