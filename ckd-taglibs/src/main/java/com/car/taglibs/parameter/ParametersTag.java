package com.car.taglibs.parameter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang3.StringUtils;


import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

public class ParametersTag extends BodyTagSupport {
	
	private static final long serialVersionUID = -4638859223215508340L;
	

	/** 输出为input tag */
	public static final String TYPE_INPUT_TAG = "inputTag";
	/** 输出为query string */
	public static final String TYPE_QUERY_STRING = "queryString";

	/**
	 * 指定包含的parameters，如果没有指定，则包含全部。可以使用*、?作为通配符。
	 */
	private String includes;
	/**
	 * 默认为空的参数会过滤掉，这里指定后即使为空也不会过滤
	 */
	private String emptyIncludes;
	/**
	 * @see #TYPE_INPUT_TAG
	 * @see #TYPE_QUERY_STRING
	 */
	private String type;

	/**
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#release()
	 */
	@Override
	public void release() {
		includes = null;
		emptyIncludes=null;
		type = null;
		super.release();
	}

	/**
	 * @throws java.io.UnsupportedEncodingException
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doEndTag()
	 */
	@Override
	public int doEndTag() throws JspException {
		Enumeration<?> keys = pageContext.getRequest().getParameterNames();
		String str = "";
		if (StringUtils.isBlank(type) || StringUtils.equals(TYPE_INPUT_TAG, type)) {
			str = buildInputTags(keys);
		} else {
			str = buildQueryString(keys);
		}
		try {
			pageContext.getOut().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return BodyTagSupport.EVAL_PAGE;
	}
	public String getQueryString(HttpServletRequest request, String include,String emptyIncludes) throws JspException, UnsupportedEncodingException {
		this.setEmptyIncludes(emptyIncludes);
		return getQueryString(request,include);
	}
	public String getQueryString(HttpServletRequest request, String include) throws JspException, UnsupportedEncodingException {
		if (StringUtils.isBlank(include)){
			return "";
		}
		setIncludes(include);

		Enumeration<?> keys = request.getParameterNames();

		StringBuffer buf = new StringBuffer();
		while (keys.hasMoreElements()) {
			String key = (String)keys.nextElement();
			// add by march 2008-01-11
			if (!isInclude(key)) {
				continue;
			}

			String value = request.getParameter(key);
			if (StringUtils.isBlank(value)&&!isEmptyInclude(key)) {
				continue;
			}
			buf.append(key).append("=")
					.append(URLEncoder.encode(value, "utf-8"));
			if (keys.hasMoreElements()) {
				buf.append("&");
			}
		}

		String tmp = buf.toString();

		if (tmp.endsWith("&"))
			return tmp.substring(0, tmp.length() - 1);
		else
			return tmp;

	}

	/**
	 * 根据参数构造queryString
	 * 
	 * @param keys
	 *            参数名称
	 * @throws java.io.UnsupportedEncodingException
	 */
	private String buildQueryString(Enumeration<?> keys) {
		StringBuffer buf = new StringBuffer();
		while (keys.hasMoreElements()) {
			String key = (String)keys.nextElement();

			// add by march 2008-01-11
			if (!isInclude(key)) {
				continue;
			}

			String value = pageContext.getRequest().getParameter(key);
			if (StringUtils.isBlank(value)&&!isEmptyInclude(key)) {
				continue;
			}

			try {
				buf.append(key).append("=").append(URLEncoder.encode(value, "utf-8"));
			} catch (UnsupportedEncodingException e) {

			}

			if (keys.hasMoreElements()) {
				buf.append("&");
			}
		}

		String tmp = buf.toString();

		if (tmp.endsWith("&")){
			return tmp.substring(0, tmp.length() - 1);
		}else{
			return tmp;
		}
	}

	/**
	 * 根据参数构造Input
	 * 
	 * @param keys
	 *            参数名称
	 */
	private String buildInputTags(Enumeration<?> keys) {
		StringBuffer buf = new StringBuffer();
		while (keys.hasMoreElements()) {
			String key = (String)keys.nextElement();
			if (!isInclude(key)) {
				continue;
			}
			String value = pageContext.getRequest().getParameter(key);
			buf.append("<input type='hidden' name='")
			   .append(key)
			   .append("' value='")
			   .append((value == null) ? StringUtils.EMPTY : value)
			   .append("'/>");
		}

		return buf.toString();
	}

	/**
	 * 判断某个参数是否可以包含。
	 */
	private boolean isInclude(String target) {
		if (StringUtils.isBlank(includes)) {
			return true;
		}

		PathMatcher matcher = new AntPathMatcher();
		String[] incs = StringUtils.split(includes, ",");
		for (String inc : incs) {
			if (matcher.match(inc, target)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 判断某个参数是否可以包含。
	 */
	private boolean isEmptyInclude(String target) {
		if (StringUtils.isBlank(emptyIncludes)) {
			return false;
		}
		String[] incs = StringUtils.split(emptyIncludes, ",");
		for (String inc : incs) {
			if (inc.equals(target)) {
				return true;
			}
		}

		return false;
	}
	/**
	 * @return the includes
	 */
	public String getIncludes() {
		return includes;
	}

	/**
	 * @param includes
	 *            the includes to set
	 */
	public void setIncludes(String includes) {
		this.includes = includes;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	public String getEmptyIncludes() {
		return emptyIncludes;
	}

	public void setEmptyIncludes(String emptyIncludes) {
		this.emptyIncludes = emptyIncludes;
	}
	
}