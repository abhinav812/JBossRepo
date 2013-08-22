package com.abhinav.mavenexample.app.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.abhinav.mavenexample.app.client.HelloClient;
import com.abhinav.mavenexample.app.ejb.Hello;
import com.abhinav.mavenexample.app.ejb.HelloRemote;

public class HelloClientTest {

	@Test
	public void test() throws Exception {
		Hello hello = HelloClient.getHelloEJB();
		System.out.println(hello.sayHello());
		System.out.println(hello.sayHello());
	}

}
