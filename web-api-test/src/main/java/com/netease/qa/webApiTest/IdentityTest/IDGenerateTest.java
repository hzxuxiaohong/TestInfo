package com.netease.qa.webApiTest.IdentityTest;

import java.io.UnsupportedEncodingException;

import javax.xml.soap.Detail;

import jdk.nashorn.internal.runtime.UserAccessorProperty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import sun.util.logging.resources.logging;
import util.HttpUtil;
import util.DataTransfer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netease.qa.webApiTest.constant.IdentityConstant;
import com.netease.qa.webApiTest.dto.Details;
import com.netease.qa.webApiTest.dto.User;

import util.DataProviderFromCsv;

public class IDGenerateTest {
	public HttpUtil httpRequest = new HttpUtil();
	public JSONObject jsonReq = new JSONObject();
	public Logger log = LoggerFactory.getLogger(IDGenerateTest.class);
	String request;
	String response;
	
	@Test(dataProvider="ProviderCsvClass",dataProviderClass=DataProviderFromCsv.class)
	public void generateIdTestGroup(String caseId,String desc,String isOpen,String dataReq) throws UnsupportedEncodingException {
		jsonReq.put("data", DataTransfer.string2JsonArray(dataReq));
		request = jsonReq.toJSONString();
		log.info("-----------------------------Run: "+caseId+" "+desc+request);
		response= httpRequest.urlPostJson(IdentityConstant.identity_url,request);
		log.info("-----------------------------Response: "+response);
	}
	
	@Test
	public void testIndex() {
		httpRequest.urlGet("http://10.165.125.55:8181/api/index/", "",IdentityConstant.Cookie);
	}
	
	/*@Test
	public void test2() {
		User user = new User();
		user.setNumber(1);
		user.setSequence(Long.valueOf(1));
		Details detail = new Details();
		detail.setCity("杭州");
		user.setDetails(detail);
		String s = JSON.toJSONString(user);
		System.out.println(s);
	}*/
	/*@Test
	public void test3() {
		JSONObject json = new JSONObject();
		json.put("details", 1);
		json.put("detail", 2);
//		json.putIfAbsent(key, value)
		System.out.println(json.toJSONString());
	}
	*/
	
	
}
