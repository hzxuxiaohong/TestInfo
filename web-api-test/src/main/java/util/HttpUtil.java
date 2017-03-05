package util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import oracle.jrockit.jfr.events.RequestableEventEnvironment;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HeaderElement;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.cookie.CookieSpec;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.netease.qa.webApiTest.constant.IdentityConstant;
import com.netease.qa.webApiTest.constant.responseEnum;
import com.sun.org.apache.bcel.internal.generic.InstructionConstants.Clinit;
/**
 * 
 * @author hzxuxiaohong
 * @date 2016年11月22日
 * @desc 
 */
public class HttpUtil {
	public Logger log = Logger.getLogger(HttpUtil.class);
	HttpClient client = new HttpClient();
	String resp = null;
	public void HttpUtil() {
		client.getHttpConnectionManager().getParams().setConnectionTimeout(3000);//		client.setConnectionTimeout(3000);
		client.getParams().setSoTimeout(3000);// 		client.setTimeout(1000);
		client.getParams().setCookiePolicy(CookiePolicy.RFC_2965);
	}
	/**
	 * 
	* @Title: urlPostJson 
	* @Description: post请求json数据 
	* @param @param url
	* @param @param desc
	* @param @param json     
	* @return void   
	* @throws
	 */
	public String urlPostJson(String url,String json) {
		PostMethod method = new PostMethod(url);
		try {
			RequestEntity se = new StringRequestEntity(json, "application/json", "UTF-8");
	        method.setRequestEntity(se);
	        log.info(url);
			int status = client.executeMethod(method);
			if (status == HttpStatus.SC_OK) {
//				resp = method.getResponseBodyAsString();
				resp = DataTransfer.inputStream2String(method.getResponseBodyAsStream());
			}
		}catch (RuntimeException e) {
			log.error("HTTPMethod(url) Exception!",e);
		}catch (IOException e) {
			log.error("HttpClient.executeMethod  IOException!",e);
		}finally{
			if (method != null) {
				method.releaseConnection();
			}
		}	
		return resp;
	}
	/**
	 * 
	* @Title: urlPostParams 
	* @Description: post请求传参数值
	* @param @param url
	* @param @param params    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void urlPostParams(String url,String params) {
//		client.getHostConfiguration().setProxy("127.0.0.1", 5689);
		PostMethod method = new PostMethod(url);
		method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");//防止中文到后端变乱码
		if (StringUtils.isNotEmpty(params)) {
			method.setRequestBody(params);
		}
		try {
			int status = client.executeMethod(method);
			if(status == HttpStatus.SC_OK){
				log.info(method.getResponseBodyAsString());
			}
			else if (status == HttpStatus.SC_MOVED_TEMPORARILY) {
				Header header = method.getResponseHeader("Location");
				urlPostParams(header.getValue(), null);
			}
		}catch (RuntimeException e) {
			log.error("HTTPMethod(url) Exception!",e);
		}catch (IOException e) {
			log.error("HttpClient.executeMethod  IOException!",e);
		}finally{
			if (method != null) {
				method.releaseConnection();
			}
		}	
	}
	/**
	 * 
	* @Title: urlGet 
	* @Description: get请求 参数值对 
	* @param @param url
	* @param @param params
	* @param @param cookie    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void urlGet(String url,String params,String cookie) {
//		client.getHostConfiguration().setProxy("127.0.0.1", 5689);
		GetMethod method = null;
		if (StringUtils.isNotEmpty(params)) {
			url = url+"?"+params;
		}
		try {
			method  = new GetMethod(url);
			if (StringUtils.isEmpty(cookie)) {
				method.setRequestHeader("Cookie", cookie);
			}
			method.setFollowRedirects(false); //设置不自动跳转
			int statusCode = client.executeMethod(method);
			if (statusCode == 200) {
				String res=method.getResponseBodyAsString();//默认编码方式ISO-8859-1.这里指定从header里拿到的content-type里拿到charset
				log.info(res);
				getCookie(method);
                }  
            else {  
				log.error("Response Code: " + statusCode);  
            }  
		}catch (RuntimeException e) {
			log.error("HTTPMethod(url) Exception!",e);
		}catch (IOException e) {
			log.error("HttpClient.executeMethod  IOException!",e);
		}finally{
			if (method != null) {
				method.releaseConnection();
			}
		}	
	}
	
	/**
	 * 
	* @Title: getInfoFromHead 
	* @Description: 获取头部信息 
	* @param @param method
	* @param @param name
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String getInfoFromHead(HttpMethodBase method,String name) {
		Header[] headers = method.getResponseHeaders();
		for (int i = 0; i < headers.length; i++) {
			if (headers[i].getName().equals(name)) {
				return headers[i].getValue();
			}
		}
		 return null;
	}
	/**
	 * 
	* @Title: getCookie 
	* @Description: 获取响应里的cookie
	* @param @param method    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getCookie(HttpMethodBase method) {
		String cookie = getInfoFromHead(method,"Set-Cookie");
		if ( cookie != null) {
			IdentityConstant.Cookie = cookie;
		}
		else{
			Cookie[] cookies = client.getState().getCookies();
			StringBuffer cookieList = new StringBuffer();
			if (cookieList != null) {
				for (int i = 0; i < cookies.length; i++) {
					cookieList.append(cookies[i].toString());
				}
				 IdentityConstant.Cookie = cookieList.toString();
			}
		}
	}
	
	
	
}
