package com.abhinav.mavenexample.app.test;

import org.junit.Test;

import com.abhinav.mavenexample.app.beans.PrototypeBeanWithClassProxy;
import com.abhinav.mavenexample.app.beans.PrototypeBeanWithoutProxy;
import com.abhinav.mavenexample.app.util.BeanLocator;

public class BeanLocaterTest {

	@Test
	public void test() {
		PrototypeBeanWithClassProxy proxyBean1 = (PrototypeBeanWithClassProxy) BeanLocator
				.getBean("prototypeBeanWithClassProxy");
		PrototypeBeanWithoutProxy noProxybean1 = (PrototypeBeanWithoutProxy) BeanLocator
				.getBean("prototypeBeanWithoutProxy");

		System.out.println("Proxy - [" + proxyBean1.getRandomNum() + "]  ["
				+ proxyBean1.getRandomNum() + "]");
		System.out.println("No Proxy - [" + noProxybean1.getRandomNum() + "] ["
				+ noProxybean1.getRandomNum() + "]");
	}

}
