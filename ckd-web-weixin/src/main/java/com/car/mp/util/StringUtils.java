package com.car.mp.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.cglib.beans.BeanMap;

public class StringUtils {
	
	private static Pattern webParamPattern = Pattern.compile("[&]?([^=]+)=([^&$]*)");

	public static <T> T autowireObject(String str, T bean) {
		Matcher m = webParamPattern.matcher(str);
		BeanMap map = BeanMap.create(bean);
		while (m.find()) {
			if (!m.group(2).trim().equals("")) {
				map.put(bean, m.group(1), m.group(2));
			}
		}
		return bean;
	}
	
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().equals("");
	}
	
	/**
	 * 判断两个字符串在去掉空格后，忽略大小写后是否相等
	 * 若两个字符串都为Null，则返回False
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean isTextMatch(String str1, String str2) {
		if (str1 == null && str2 == null) {
			return false;
		} else if (str1 != null && str2 != null) {
			return str1.trim().equalsIgnoreCase(str2.trim());
		}
		return false;
	}
	
	/**
	 * MD5摘要算法
	 * @param text
	 * @return
	 */
	public static String md5(String text) {
		char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			md.update(text.getBytes());
			byte tmp[] = md.digest();
			char str[] = new char[16 * 2];
			int k = 0;
			for (int i = 0; i < 16; i++) {
				byte byte0 = tmp[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 把没有冒号的mac地址重新还原
	 * @param mac
	 * @return
	 */
	public static String addColonToMac(String mac) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (char c : mac.toCharArray()) {
			sb.append(c);
			if (++i % 2 == 0 && i < mac.length())
				sb.append(":");
		}
		return sb.toString().toUpperCase();
	}
	
	//compute imei by cuid
	public static String getIMEIByCuid(String cuid)
	{
		try {
			if(null == cuid)
			{
				return null;
			}
			cuid = URLDecoder.decode(cuid, "utf-8");
			int idx = cuid.indexOf('|');
			if(idx != -1)
			{
				String imei = cuid.substring(idx + 1);
				return new StringBuffer(imei).reverse().toString();
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
