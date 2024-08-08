package com.willow.aop.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class WebLogAspect {

    private final static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    /**
     * 以自定义 @WebLog 注解为切点
     **/
    @Pointcut("@annotation(com.willow.aop.WebLog)")
    public void webLog() {
    }

    /**
     * 在切点之前织入
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        logger.info("****" + joinPoint.getSignature().getName() + "运行。。。@Before:参数列表是：{" + Arrays.asList(args) + "}");
    }

    /**
     * 在切点之后织入
     */
    @After("webLog()")
    public void doAfter(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        logger.info("****" + joinPoint.getSignature().getName() + "运行。。。@After:参数列表是：{" + Arrays.asList(args) + "}");
    }

    @AfterReturning(value = "webLog()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        logger.info("方法返回" + joinPoint.getSignature().getName() + "运行。。。@result:返回：{" + result + "}");
    }

    @AfterThrowing(value = "webLog()", throwing = "exception")
    public void logThrowing(JoinPoint joinPoint, Exception exception) {
        Object[] args = joinPoint.getArgs();
        logger.info("方法返回异常" + joinPoint.getSignature().getName() + "运行。。。@AfterThrowing:参数列表是：{"
                + Arrays.asList(args) + "}" + exception.toString());
    }

    /**
     * 环绕
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //开始时间
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // 打印出参
        logger.info("Response Args  : {}", new ObjectMapper().writeValueAsString(result));
        // 执行耗时
        logger.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
        return result;
    }

}
