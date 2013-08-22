package com.abhinav.mavenexample.app.beans;

import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component("prototypeBeanWithClassProxy")
@Scope(value="prototype", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class PrototypeBeanWithClassProxy {
	
	private int randNum;
	
	public PrototypeBeanWithClassProxy() {
		randNum = new Random().nextInt();
	}
	
	public int getRandomNum(){
		return randNum;
	}

}
