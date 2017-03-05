package util;

public class DataDealUtil {
	 public static String preDealValue(String data) {
		 String[] str = data.split("&");
		
		 String dataDeal = null;
		for (int i=0;i < str.length;i++) {
			String s = str[i];
			if (s.contains("#")) {
				dataDeal = s.split("#")[1];
				switch (dataDeal) {
				case "card":
					data=data.replace("#card","");
					break;
				case "appid":
					data=data.replace("#appid", "3333");
				default:
					break;
				}
			}
		}
		return data;
	}
}
