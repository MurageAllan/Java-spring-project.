package com.LoveToCode.springAOP.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AppAspects {

	// @Before("execution(public void add*()) ")

		// create a pointcut for getter methods.
		@Pointcut("execution(* com.LoveToCode.springDAO.*.get*(..))")
		public void forGetters() {
		}

		// create a pointcut for setter methods.
		@Pointcut("execution(* com.LoveToCode.springDAO.*.set*(..))")
		public void forSetters() {
		}

		// create pointcut include package and exclude getters and setters
		@Pointcut("forpackageDao() && !(forSetters() || forGetters() )")
		public void forPackageDaoNoGettersSetters() {
		}

		@Pointcut("execution(* com.LoveToCode.springDAO.*.*(..))")
		public void forpackageDao() {
		}
}
