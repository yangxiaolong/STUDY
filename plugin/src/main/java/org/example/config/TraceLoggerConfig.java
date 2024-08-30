package org.example.config;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @auther yangxiaolong
 * @create 2024/8/29
 */
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Aspect
@Configuration
public class TraceLoggerConfig {

    @Pointcut("execution(* org.example.controller..*.*(..))")
    public void monitor() {
    }

    /*@Around("monitor()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        Object proceed = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        log.info("Entering Info" + Arrays.toString(args));
        log.info("Leaving Info " + proceed);
        return proceed;
    }*/

    @Bean
    public Advisor traceAdvisor() {
        CustomizableTraceInterceptor customizableTraceInterceptor = new CustomizableTraceInterceptor();
        customizableTraceInterceptor.setEnterMessage("Entering ClassName=$[targetClassShortName]:$[methodName]($[arguments])");
        customizableTraceInterceptor.setExitMessage("Leaving $[methodName](): $[returnValue]");
        customizableTraceInterceptor.setUseDynamicLogger(true);
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("org.example.config.TraceLoggerConfig.monitor()");
        return new DefaultPointcutAdvisor(pointcut, customizableTraceInterceptor);
    }

}
