package ru.javabegin.training.spring.aop.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.javabegin.training.spring.aop.objects.FileManager;
import ru.javabegin.training.spring.aop.objects.ShowResult;
import ru.javabegin.training.spring.aop.objects.ShowTime;

import java.util.Map;
import java.util.Set;

@Component
@Aspect
public class MyLogger {

	@Around("@annotation(showTime)")
	public Object watchTime(ProceedingJoinPoint joinpoint, ShowTime showTime) throws Throwable {
		long start = System.currentTimeMillis();
		System.out.println("method begin: " + joinpoint.getSignature().toShortString() + " >>");
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
		System.out.println("method end: " + joinpoint.getSignature().toShortString() + ", time=" + time + " ms <<");
		System.out.println();

		return output;
	}

	@AfterReturning(pointcut = "@annotation(showResult)", returning = "obj")
	public void print(Object obj, ShowResult showResult) {
		if (obj instanceof FileManager) {
			System.out.println("Print info begin >>");

			if (obj instanceof Set) {
				Set set = (Set) obj;
				for (Object object : set) {
					System.out.println(object);
				}
			} else if (obj instanceof Map) {
				Map map = (Map) obj;
				for (Object object : map.keySet()) {
					System.out.println("key=" + object + ", " + map.get(object));
				}
			}

			System.out.println("Print info end <<");
			System.out.println();
		}
	}
}
