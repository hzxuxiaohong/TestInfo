package com.netease.qa.webApiTest.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class JustTest {
	public static Logger log = LoggerFactory.getLogger(JustTest.class);
	@BeforeClass
	public void beforeClass() {
		log.info("beforeClass!");
	}
	@AfterClass
	public void afterClass() {
		log.info("afterClass!");
	}
	@BeforeTest
	public void preTest() {
		log.info("beforeTest!");
	}
	@BeforeMethod
	public void preTest2() {
		log.info("beforeMethod!");
	}
	@Test
	public void dependTest() {
		log.info("dependTest!");
	}
	@Test(dependsOnMethods={"dependTest"})
	public void testBegin() {
		log.error("testBegin!!!");
	}
	@AfterMethod
	public void aftMethod() {
		log.info("afterMethod!");
	}
	@AfterTest
	public void aftTest() {
		log.info("afterTest");
	}
}
