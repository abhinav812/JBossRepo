package com.abhinav.mavenexample.app.beans;

import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component("prototypeBeanWithoutProxy")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.DEFAULT)
// ScopedProxyMode.DEFAULT : Do not create scoped proxy
public class PrototypeBeanWithoutProxy {

	private int randNum;

	public PrototypeBeanWithoutProxy() {
		randNum = new Random().nextInt();
	}

	public int getRandomNum() {
		return randNum;
	}

}
