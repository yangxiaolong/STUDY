package org.example.plugin.email;

import org.springframework.plugin.core.Plugin;

/**
 * @auther yangxiaolong
 * @create 2024/8/28
 */
public interface EmailService extends Plugin<String> {

    void sendEmail(String s);
}