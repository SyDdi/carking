package com.car.taglibs.picture;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;


public class ImagesBaseUrlTag extends TagSupport{
		
	private static final long serialVersionUID = 8851488650541375605L;

	private static final String DEFAULT_IMAGES_SECOND_DOMAIN =  "abc";
		
	private static final String STATIC_IMAGES_URL = "efg";
	
	private String secondDomain;
	
	public int doStartTag() throws JspException {
		StringBuilder sb = new StringBuilder();
				
		if(StringUtils.isBlank(STATIC_IMAGES_URL)){
			throw new JspException("系统没有配置公共的图片访问地址:static.images.url");
		}
        
		if(StringUtils.isBlank(secondDomain) || DEFAULT_IMAGES_SECOND_DOMAIN.equals(secondDomain) ){
			sb.append(STATIC_IMAGES_URL);
		}else{
			sb.append(STATIC_IMAGES_URL.replaceFirst(DEFAULT_IMAGES_SECOND_DOMAIN, secondDomain));
		}
		
		try {
			pageContext.getOut().write(sb.toString());
		} catch (IOException e) {
			throw new JspException(e);
		}		
		return SKIP_BODY;	
	}

	public String getSecondDomain() {
		return secondDomain;
	}

	public void setSecondDomain(String secondDomain) {
		this.secondDomain = secondDomain;
	}
}
