package com.netease.qa.webApiTest.dto;

/**
 * Created by hzyangbaosong on 2016-05-30.
 */
public class ResponseDto {
    private int statusCode;
    private String response = null;
    private String cookies = null;
    private String sessions = null;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

    public String getSessions() {
        return sessions;
    }

    public void setSessions(String sessions) {
        this.sessions = sessions;
    }
}

