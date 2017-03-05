package com.netease.qa.webApiTest.constant;

public enum responseEnum {
	SC_ACCEPTD(200,"请求成功"),
	SC_MOVED_PERMANENTLY(301,"永久重定向"),
	SC_MOVED_TEMPORARILY(302,"临时重定向"),
	SC_NOT_FOUND(404,"路径错误"),
	SC_INTERNAL_ERROR(500,"服务内部出错");
	public int status;
	public String desc;
	private responseEnum(int status,String desc) {
		this.status = status;
		this.desc = desc;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
