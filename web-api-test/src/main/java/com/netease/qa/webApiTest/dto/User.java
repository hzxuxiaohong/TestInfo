package com.netease.qa.webApiTest.dto;



public class User {
	public Details details;
	public Long  sequence;
	public int number;
	
	public User(String data) {
		 String[] str = data.split("&");
		 String[] param = null;
		 for (int i = 0; i < str.length; i++) {
             param = str[i].split("=");
             if (param[0].equals("sequence")) {
                 this.sequence = Long.valueOf(param[1]);
            }
            else if (param[0].equals("number")) {
                 this.number = Integer.parseInt(param[1]);
            }
            else {
               handleData(param);
            }
            
       }
	}
	
	public User() {
		
	}
	
	public void handleData(String[] str) {
		 
	}
	public Details getDetails() {
		return details;
	}
	public void setDetails(Details details) {
		this.details = details;
	}
	public Long getSequence() {
		return sequence;
	}
	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	 
	
}
