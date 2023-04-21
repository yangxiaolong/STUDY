package com.willow.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * Created by yangxiaolong on 2019\10\10 0010.
 */
@Aspect
public class LogAspects {

	// 抽取公共的切入点表达式
	// 1、本类引用
	// 2、其他的切面引用
	@Pointcut("execution(* com.willow.aop.*.*(..))")
	public void pointCut() {
	}

	// 前置通知
	@Before(value = "pointCut()")
	public void logStart(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		System.out.println(
				"****" + joinPoint.getSignature().getName() + "运行。。。@Before:参数列表是：{" + Arrays.asList(args) + "}");
	}

	// 后置通知
	@After(value = "pointCut()")
	public void logEnd(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		System.out.println(
				"****" + joinPoint.getSignature().getName() + "运行。。。@After:参数列表是：{" + Arrays.asList(args) + "}");
	}

	// 返回通知
	@AfterReturning(value = "pointCut()", returning = "result")
	public void logReturn(JoinPoint joinPoint, Object result) {
		System.out.println("方法返回" + joinPoint.getSignature().getName() + "运行。。。@result:返回：{" + result + "}");
	}

	// 异常通知
	@AfterThrowing(value = "pointCut()", throwing = "exception")
	public void logThrowing(JoinPoint joinPoint, Exception exception) {
		Object[] args = joinPoint.getArgs();
		System.out.println("方法返回异常" + joinPoint.getSignature().getName() + "运行。。。@AfterThrowing:参数列表是：{"
				+ Arrays.asList(args) + "}" + exception.toString());
	}

}
