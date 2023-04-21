package com.willow.config;

import com.willow.aop.aspect.LogAspects;
import org.springframework.context.annotation.Bean;
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

	@Bean
	public LogAspects logAspects() { // 注册到IOC容器中
		return new LogAspects();
	}

}
