package util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netease.qa.webApiTest.dto.User;
/**
 * 
 * @author hzxuxiaohong
 * @date 2016年11月18日
 * @desc
 */
public class DataTransfer {
	/**
	 * 
	* @Title: Params2Json 
	* @Description: 将名称/值的形式转化成jsonstring
	* @param @param data
	* @param @return     
	* @return String 
	* @throws
	 */
	public static String Params2Json(String data) {
		 String[] str = data.split("&");
		 String[] param = null;
		 JSONObject json = new JSONObject();
		 for (int i=0;i < str.length;i++) {
			param = str[i].split("=");
			json.put(param[0], param[1]);
		}
		return json.toJSONString();
		}
	
	/**
	 * 
	* @Title: Params2Map 
	* @Description: 将名称/值形式的字符串转化为map类型 
	* @param @param data
	* @param @return    设定文件 
	* @return HashMap<String,String>    返回类型 
	* @throws
	 */
	public static HashMap<String, String> Params2Map(String data) {
		String[] str = data.split("&");
		String[] param = null;
		HashMap<String, String> params = null;
		for (int i=0;i < str.length;i++) {
		param = str[i].split("=");
		params.put(param[0], param[1]);
	}
		return params;
	}
	 /**
	  * 
	 * @Title: string2JsonObject 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param data
	 * @param @return    设定文件 
	 * @return JSONObject    返回类型 
	 * @throws
	  */
	
	 public static JSONObject string2JsonObject(String data) {
		 if (data.equals("")) {
			return null;
		}
		 JSONObject jsonInter = new JSONObject();
		 JSONObject jsonOuter = new JSONObject();
		 String[] jsonSon;
		 String[] str = data.split("&");
		 String[] param = null;
		 for (int i=0;i < str.length;i++) {
			param = str[i].split("=");
			if (param[0].equals("sequence")||param[0].equals("number")) {
				jsonOuter.put(param[0], param[1]);
			}
			else {
				jsonInter.put(param[0], param[1]);
			}
		}
		 jsonOuter.put("details", jsonInter);
		return jsonOuter;
	}
	 
	 /**
	  * 
	 * @Title: string2JsonArray 
	 * @Description: 字符串转化成json数组 
	 * @param @param data
	 * @param @return    设定文件 
	 * @return JSONArray    返回类型 
	 * @throws
	  */
	 public static JSONArray string2JsonArray(String data) {
		 String[] dataArray = data.split("\\|");
		 JSONArray array = new JSONArray();
		 JSONObject jsonElement = null;
		for (int i = 0; i < dataArray.length; i++) {
			jsonElement=DataTransfer.string2JsonObject(dataArray[i]);
			 if (jsonElement != null) {
				 array.add(jsonElement);
			 }
		}
		 return array;
	 } 
	 
	 /**
	  * 
	 * @Title: string2InputStream 
	 * @Description: 字符串转化为输入流 
	 * @param @param paramString
	 * @param @param charset
	 * @param @return  
	 * @return InputStream  
	 * @throws
	  */
	 public static InputStream string2InputStream(String paramString,String charset) {
		 InputStream in_code = null;
		 try {
			 in_code = new ByteArrayInputStream(paramString.getBytes(charset));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return in_code;
	}
	 
	 /**
	 * @throws UnsupportedEncodingException 
	  * 
	 * @Title: inputStream2String 
	 * @Description: 输入流转化为字符串 
	 * @param @param is
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	  */
	 public static String inputStream2String(InputStream is) throws UnsupportedEncodingException {
		BufferedReader read = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		StringBuffer str = new StringBuffer();
		String line = "";
		try {
			while ((line = read.readLine()) != null) {
				str.append(line+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str.toString();
		 
	}
	 
	 
}
