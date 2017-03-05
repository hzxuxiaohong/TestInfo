package com.netease.qa.webApiTest.dubbo;

import org.testng.annotations.Test;

import com.netease.wyxd.elephant.client.bean.request.ppbank.PPBankRequestBean;
import com.netease.wyxd.elephant.client.service.PPBankService;


public class ppBankQueryInfo {
	public PPBankService bankService;
	public PPBankRequestBean requestBean = new PPBankRequestBean();
	@Test
	public void BankQueryInfo() {
		bankService.queryPPBankReportByApplyNo(requestBean);
	}
}
