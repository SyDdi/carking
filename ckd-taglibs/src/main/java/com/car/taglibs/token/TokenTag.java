package com.car.taglibs.token;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;



public class TokenTag extends TagSupport{

	private static final long serialVersionUID = -6590961720995681403L;
	
	private static final String REQUEST_TYPE = "default";
	
	private static final String REQUEST_AJAX = "ajax";
	
	private String type = REQUEST_TYPE;
	
	public int doStartTag() throws JspException {
		StringBuilder sb = new StringBuilder();
		String _springmvc_server_token = UUID.randomUUID().toString();
		if(REQUEST_TYPE.equals(type)){
			sb.append("<input type=\"hidden\" name=\"token\" value=\""+_springmvc_server_token+"\">");
		}else if(REQUEST_AJAX.equals(type)){
			sb.append(_springmvc_server_token);
		}
		try {
			pageContext.getSession().setAttribute("_springmvc_server_token", _springmvc_server_token);
			pageContext.getOut().write(sb.toString());
		} catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;  //本标签主体为空,所以直接跳过主体
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
