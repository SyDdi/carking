package com.car.utils;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpHandler {

	private static Logger logger = Logger.getLogger("api");

	public static String Get(String url, Map<String, Object> params, int retryCount) {
		try {
			StringBuffer line = new StringBuffer();
			HttpClient client = new HttpClient();
			HttpMethod getmethod = new GetMethod(url);
			getmethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(retryCount, false));
			if (params.size() > 0) {
				List<NameValuePair> paramList = new ArrayList<NameValuePair>();
				for (String key : params.keySet()) {
					paramList.add(new NameValuePair(key, params.get(key).toString()));
				}
				NameValuePair[] param = paramList.toArray(new NameValuePair[paramList.size()]);
				getmethod.setQueryString(param);
			}
			int statusCode = 0;
			try {
				statusCode = client.executeMethod(getmethod);
				BufferedReader br = new BufferedReader(
						new InputStreamReader(getmethod.getResponseBodyAsStream(), "UTF-8"));
				String temp = "";
				while ((temp = br.readLine()) != null) {
					line.append(temp);
				}
				logger.info("statusCode : " + statusCode);
			} catch (URIException e) {
				logger.info("statusCode : " + statusCode);
				logger.error(e);
			} catch (IOException e) {
				logger.info("statusCode : " + statusCode);
				logger.error(e);
			} finally {
				getmethod.releaseConnection();
			}
			return line.toString();
		} catch (Exception e) {
			logger.info(e.getMessage());
			return "";
		}
	}

	public static String Post(String url, Map<String, Object> params, int retryCount) {
		try {
			StringBuffer line = new StringBuffer();
			HttpClient client = new HttpClient();
			PostMethod postmethod = new PostMethod(url);
			postmethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(retryCount, false));
			postmethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			if (params.size() > 0) {
				List<NameValuePair> paramList = new ArrayList<NameValuePair>();
				for (String key : params.keySet()) {
					paramList.add(new NameValuePair(key, params.get(key).toString()));
				}
				NameValuePair[] param = paramList.toArray(new NameValuePair[paramList.size()]);
				postmethod.setRequestBody(param);
			}
			postmethod.releaseConnection();
			int statusCode = 0;
			try {
				statusCode = client.executeMethod(postmethod);
				BufferedReader br = new BufferedReader(
						new InputStreamReader(postmethod.getResponseBodyAsStream(), "UTF-8"));
				String temp = "";
				while ((temp = br.readLine()) != null) {
					line.append(temp);
				}
				logger.info("statusCode : " + statusCode);
			} catch (URIException e) {
				logger.info("statusCode : " + statusCode);
				logger.error(e);
			} catch (IOException e) {
				logger.info("statusCode : " + statusCode);
				logger.error(e);
			} finally {
				postmethod.releaseConnection();
			}
			return line.toString();
		} catch (Exception e) {
			logger.info(e.getMessage());
			return "";
		}
	}

}
