package ru.javabegin.training.spring.aop.logging;

import java.util.Map;
import java.util.Set;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyLogger {

	@Pointcut("execution(* ru.javabegin.training.spring.aop.objects.Manager.*(..))")
	private void allMethods() {
	}

	@Pointcut("@annotation(ru.javabegin.training.spring.aop.annotations.ShowSetResult)")
	private void setMethods() {
	}

	@Pointcut("@annotation(ru.javabegin.training.spring.aop.annotations.ShowMapResult)")
	private void mapMethods() {
	}

	@Around("allMethods() && setMethods()")
	public Object watchSetTime(ProceedingJoinPoint joinpoint) {
		long start = System.currentTimeMillis();
		System.out.println("method begin (Set): " + joinpoint.getSignature().toShortString() + " >>");
		Object output = null;

		for (Object object : joinpoint.getArgs()) {
			System.out.println("Param : " + object);
		}

		try {
			output = joinpoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		long time = System.currentTimeMillis() - start;
		System.out.println("method end (Set): " + joinpoint.getSignature().toShortString() + ", time=" + time + " ms <<");
		System.out.println();

		return output;
	}

	@Around("allMethods() && mapMethods()")
	public Object watchMapTime(ProceedingJoinPoint joinpoint) {
		long start = System.currentTimeMillis();
		System.out.println("method begin (Map): " + joinpoint.getSignature().toShortString() + " >>");
		Object output = null;

		for (Object object : joinpoint.getArgs()) {
			System.out.println("Param : " + object);
		}

		try {
			output = joinpoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		long time = System.currentTimeMillis() - start;
		System.out.println("method end (Map): " + joinpoint.getSignature().toShortString() + ", time=" + time + " ms <<");
		System.out.println();

		return output;
	}


}
