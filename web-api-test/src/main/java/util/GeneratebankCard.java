package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import bsh.This;

/**
 * 
 * @author hzxuxiaohong
 * @date 2016年11月14日
 * @desc
 */
public class GeneratebankCard {
	
	public static String createCardId() {
		String cardPrefix = "666666";
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
		String cardSuffix = format.format(date);
		return cardPrefix + cardSuffix;
	}
}
