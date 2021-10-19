package com.apress.prospring5.ch3.annotated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("standardLookupBean")
public class StandardLookupDemoBean implements DemoBean {

	private Singer mySinger;

//	@Autowired
//	@Qualifier("singer")
	@Resource(name = "singer")
	public void setMySinger(Singer mySinger) {
		this.mySinger = mySinger;
	}

	@Override
	public Singer getMySinger() {
		return this.mySinger;
	}

	@Override
	public void doSomething() {
		mySinger.sing();
	}
}
