package com.abhinav.mavenexample.app.interceptor;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

public class HelloInterceptor {

	private static final String CLASS_NAME = HelloInterceptor.class.getName();
	private static final Logger logger = Logger.getLogger(CLASS_NAME);

	@PostConstruct
	protected void helloPostConstruct(InvocationContext ctx) throws Exception {
		logger.info("**** HelloInterceptor: helloPostConstruct  ");

		ctx.proceed();
	}

	@AroundInvoke
	protected Object onMethodCall(InvocationContext ctx) throws Exception {
		String methodName = ctx.getMethod().getName();
		String className = ctx.getMethod().getDeclaringClass().getName();
		
		Date timestamp = java.util.Calendar.getInstance().getTime();
		
		logger.info(className +" : "+methodName + "called at :"+timestamp);
		
		return ctx.proceed();
	}

}
