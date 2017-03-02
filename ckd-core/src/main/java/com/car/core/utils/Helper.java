package com.car.core.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 公用类
 * 
 * @author desmond 2013-11-15
 */
public class Helper {

	/**
	 * 是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (null == str) {
			return true;
		} else {
			str = str.trim();
			if ("".equals(str)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 去空
	 * @param str
	 * @return
	 */
	public static String trim(String str){
		if(isEmpty(str)){
			return "";
		}
		else{
			return str.trim();
		}
	}
	
	/**
	 * 强制转换 - int
	 * 
	 * @param str
	 * @return
	 */
	public static int parseInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return -1;
		}
	}
	
	/**
	 * 系统时间戳（秒）
	 * @return
	 */
	public static int getTs(){
		return (int)(System.currentTimeMillis() / 1000);
	}

	/**
	 * 强制转换 - double
	 * 
	 * @param str
	 * @return
	 */
	public static double parseDouble(String str) {
		try {
			return Double.parseDouble(str);
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * 强制转换 - double
	 * 
	 * @param str
	 * @return
	 */
	public static Float parseFloat(String str) {
		try {
			return Float.parseFloat(str);
		} catch (Exception e) {
			return -1f;
		}
	}

	/**
	 * 强制转换 - double
	 * 
	 * @param str
	 * @return
	 */
	public static int parseFloat2(String str) {
		try {
			float f = Float.parseFloat(str);
			int f1 = (int) f;
			return f1;
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * 强制转换 - Long
	 * 
	 * @param str
	 * @return
	 */
	public static double parseLong(String str) {
		try {
			return Long.parseLong(str);
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * 获取IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (!bIP(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (!bIP(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (!bIP(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 读取文件转换成byte
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static byte[] getByteByFile(String path) throws Exception {
		File file = new File(path);
		byte[] bytes = null;
		if (file.exists()) {
			FileInputStream fi = new FileInputStream(file);
			try {
				if (fi != null) {
					int len = fi.available();
					bytes = new byte[len];
					fi.read(bytes);
					// fi.close();
				}
			} finally {
				if (fi != null) {
					fi.close();
				}
			}

		}
		return bytes;
	}

	/**
	 * 判断是否正确的IP格式
	 * 
	 * @param ip
	 * @return
	 */
	private static boolean bIP(String ip) {
		if (ip == null || ip.length() == 0 || "unkown".equalsIgnoreCase(ip)
				|| ip.split(".").length != 4) {
			return false;
		}
		return true;
	}

	/**
	 * 显示 年 月日
	 * 
	 * @param dateString
	 * @return
	 */
	public static String getShortDate1(String dateString) {
		String sDate = "";
		Calendar cl = parseDate(dateString);
		if (cl != null) {
			sDate = new SimpleDateFormat("yyyy-MM-dd").format(cl.getTime());
		}
		return sDate;
	}

	/**
	 * 显示 月日-时分
	 * 
	 * @param dateString
	 * @return
	 */
	public static String getShortDate2(String dateString) {
		String sDate = "";
		Calendar cl = parseDate(dateString);
		if (cl != null) {
			sDate = new SimpleDateFormat("MM-dd HH:mm").format(cl.getTime());
		}
		return sDate;
	}

	/**
	 * 显示最近+年月日
	 * 
	 * @param dateString
	 * @return
	 */
	public static String getShortDate3(String dateString) {
		String sDate = "";
		Calendar cl = parseDate(dateString);
		if (cl != null) {
			long cts = (System.currentTimeMillis() - cl.getTimeInMillis()) / 1000;
			if (cts < 60) {
				sDate = "刚刚";
			} else if (cts <= 600) {
				sDate = (int) (cts / 60) + "分钟前";
			} else if (cts <= 1200) {
				sDate = "20分钟前";
			} else if (cts <= 1800) {
				sDate = "30分钟前";
			} else if (cts <= 86400) {
				if (cl.get(Calendar.DAY_OF_MONTH) == Calendar.getInstance()
						.get(Calendar.DAY_OF_MONTH)) {
					sDate = "今天 ";
				} else {
					sDate = "昨天 ";
				}
				sDate = sDate
						+ new SimpleDateFormat("HH:mm").format(cl.getTime());
			} else {
				sDate = new SimpleDateFormat("yyyy-MM-dd").format(cl.getTime());
			}
		}
		return sDate;
	}

	/**
	 * 显示最近+年月日 时间
	 * 
	 * @param dateString
	 * @return
	 */
	public static String getShortDate4(String dateString) {
		String sDate = "";
		Calendar cl = parseDate(dateString);
		if (cl != null) {

			long cts = (System.currentTimeMillis() - cl.getTimeInMillis()) / 1000;
			if (cts < 60) {
				sDate = "刚刚";
			} else if (cts <= 600) {
				sDate = (int) (cts / 60) + "分钟前";
			} else if (cts <= 1200) {
				sDate = "20分钟前";
			} else if (cts <= 1800) {
				sDate = "30分钟前";
			} else if (cts <= 86400) {
				if (cl.get(Calendar.DAY_OF_MONTH) == Calendar.getInstance()
						.get(Calendar.DAY_OF_MONTH)) {
					sDate = "今天 ";
				} else {
					sDate = "昨天 ";
				}
				sDate = sDate
						+ new SimpleDateFormat("HH:mm").format(cl.getTime());
			} else {
				sDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(cl
						.getTime());
			}
		}
		return sDate;
	}

	/**
	 * 将时间字符串转换成时间格式
	 * 
	 * @param dateString
	 * @return
	 */
	public static Calendar parseDate(String dateString) {
		try {
			dateString = dateString.replace("-", "/");
			if (dateString.length() > 19) {
				dateString = dateString.substring(0, 19);
			}
			// System.out.println(dateString);
			@SuppressWarnings("deprecation")
			long date = Date.parse(dateString);
			// System.out.println(date);
			Date date1 = new Date(date);
			// System.out.println(date1.getTime());
			Calendar cl = Calendar.getInstance();
			cl.setTime(date1);
			// System.out.println(cl.getTimeInMillis());
			// System.out.println(System.currentTimeMillis());
			return cl;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 数据流转2进制
	 * 
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	public static byte[] input2byte(InputStream inStream) throws Exception {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[100];
		int rc = 0;
		while ((rc = inStream.read(buff, 0, 100)) > 0) {
			swapStream.write(buff, 0, rc);
		}
		byte[] in2b = swapStream.toByteArray();
		return in2b;
	}

	/***
	 * 
	 * @param str
	 * @return
	 */
	public static String goneString(String str) {
		if (null == str) {
			return "";
		} else {
			if (3 < str.length()) {
				String pre = str.substring(0, 3);
				int i = 0;
				while (pre.length() < str.length() && 4 > i) {
					pre = pre + "*";
					i++;
				}
				if (i >= 4) {
					pre = pre + str.substring(7);
				}
				return pre;
			}
		}
		return str;
	}
	
	public static String chgNewLine(String item){
		item = item.replace("<", "&lt;");
		item = item.replace(">", "&gt;");
		item = item.replace("\r\n", "<br/>");
		item = item.replace("\n", "<br/>");
		return item;
	}
	
	/**
	 * 转码
	 * @param str
	 * @return
	 */
	public static String encodeStr(String str){
		if(str == null){
			return null;
		}
		try {
			return URLEncoder.encode(str,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 解析转码
	 * @param str
	 * @return
	 */
	public static String decodeStr(String str){
		if(str == null){
			return null;
		}
		try {
			return URLDecoder.decode(str,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取昨天的时间
	 * @return
	 */
	public static Date getYesterday() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}
}
