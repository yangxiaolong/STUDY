package com.willow.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by yangxiaolong on 2019\10\10 0010.
 */
@Configuration
@ComponentScan("com.willow.aop")
@EnableAspectJAutoProxy(exposeProxy = true) // 开启注解的AOP模式
public class AopConfig {

}
