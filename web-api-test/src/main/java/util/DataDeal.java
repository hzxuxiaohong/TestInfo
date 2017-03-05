package util;

import com.netease.qa.webApiTest.constant.IdentityConstant;


public class DataDeal {
	 public String preDeal(String data) {
		 
		 String[] str = data.split("&");
		 String cardID = "6666";
		 String dataDeal = null;
		for (int i=0;i < str.length;i++) {
			String s = str[i];
			if (s.contains("#")) {
				dataDeal = s.split("#")[1];
				switch (dataDeal) {
				case "card":
					if (IdentityConstant.cardId == null) {
						IdentityConstant.cardId = GeneratebankCard.createCardId();
					}
					data=data.replace("#card",IdentityConstant.cardId );
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
