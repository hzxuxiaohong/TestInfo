package com.netease.qa.webApiTest.test;
import org.testng.annotations.Test;

import util.HttpUtil;

import com.netease.qa.webApiTest.constant.IdentityConstant;
import com.netease.qa.webApiTest.dto.User;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class TestEnter {
	@Test
	public void postTest() {
		 HttpUtil httpRequest = new HttpUtil();
		 httpRequest.urlPostParams("https://m.8.163.com/m/myAssetView.htm", "");
	}
	@Test
	public void getTest() {
		 HttpUtil httpRequest = new HttpUtil();
		 httpRequest.urlPostParams("http://10.165.125.54:8181/user/testlogin","account_id=vfg&mobile=13567041902&openid=yx&appid=2");
//		 httpRequest.urlGet("http://10.165.125.54:8181/home/link",null,IdentityConstant.Cookie);
	}
	@Test(description="测试get方法")
	public void getIndexTest() {
		 HttpUtil httpRequest = new HttpUtil();
		 httpRequest.urlGet("http://10.165.125.54:8181/home/index", "",IdentityConstant.Cookie);
	}
	
	@Test(description="测试get跳转")
	public void redirectOnGetTest(){
		 HttpUtil httpRequest = new HttpUtil();
		 httpRequest.urlGet("https://bao.8.163.com/m/order/orderList.htm", "",IdentityConstant.Cookie);
	}
	@Test(description="测试post跳转,比如简书登录")
	public void redirectOnPostTest(){
		 HttpUtil httpRequest = new HttpUtil();
		 httpRequest.urlGet("https://m.8.163.com/m/myAssetView.htm", "",IdentityConstant.Cookie);
	}
	
}
