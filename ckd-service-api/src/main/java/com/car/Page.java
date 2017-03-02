package com.car;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 
 * 开发公司：SOJSON在线工具 <p>
 * 版权所有：© www.sojson.com<p>
 * 博客地址：http://www.sojson.com/blog/  <p>
 * <p>
 * 
 * 分页的对象，以及分页页码输出
 * 
 * <p>
 * 
 * 区分　责任人　日期　　　　说明<br/>
 * 创建　周柏成　2016年6月2日 　<br/>
 *
 * @author zhou-baicheng
 * @email  so@sojson.com
 * @version 1.0,2016年6月2日 <br/>
 * 
 */
@SuppressWarnings("serial")
public class Page<T> extends PageInfo<T> implements java.io.Serializable {

	public Page() {
	}
	public Page(List<T> list) {
		super(list);
	}

	public Page(int pageNo, int pageSize) {
		super.setPageNum(pageNo);
		super.setPageSize(pageSize);
	}
	public Page(int pageNo, int pageSize,int totalCount) {
		super.setPageNum(pageNo);
		super.setPageSize(pageSize);
		super.setTotal(totalCount);
	}


	@SuppressWarnings("unchecked")
	public Page(int pageNo, int pageSize, int totalCount, List<T> list) {
		super(list);
		super.setPageNum(pageNo);
		super.setPageSize(pageSize);
		super.setTotal(totalCount);
	}

	public int getFirstResult() {
		return (super.getPageNum()- 1) * super.getPageSize();
	}

	/**SOJSON SEO 翻页版本*/
	public String getWebPage(String page){
		StringBuffer pageHtml = new StringBuffer("<ul class='pagination'>");
		if(this.getPageNum()>1){
			if(this.getPageNum()>5){
				pageHtml.append("<li><a href='javascript:;' onclick='"+ page +"'>首页</a></li>");
			}
			pageHtml.append("<li><a href='"+ page +""+(this.getPageNum() -1) +"'>上一页</a></li>");
		}
		for (int i = (this.getPageNum()-2<=0?1:this.getPageNum()-2),no = 1; i <= this.getPages()&& no <6 ; i++,no++) {
			if (this.getPageNum() == i) {
				pageHtml.append("<li class='active'><a href='javascript:void(0);' >"+i+"</a></li>");
			}else{
				pageHtml.append("<li><a href='"+ page +""+ i +"'>"+i+"</a></li>");
			}
		}
		if(this.getPageNum() < this.getPages()){
			pageHtml.append("<li><a href='"+ page +""+(this.getPageNum()+1) +"'>下一页</a></li>");
		}
		pageHtml.append("</ul>");
		return pageHtml.toString();
	}
	
	
	
	/**Ajxa翻页*/
	public String getSiAjaxPageHtml(){
		StringBuffer pageHtml = new StringBuffer("<ul class='pagination'>");
		if(this.getPageNum()>1){
			if(this.getPageNum()>5){
				pageHtml.append("<li><a href='javascript:;' onclick='goPageByAjax(1)'>首页</a></li>");
			}
			pageHtml.append("<li><a href='javascript:;'  onclick='goPageByAjax("+(this.getPageNum() - 1)+")'>上一页</a></li>");
		}
		for (int i = (this.getPageNum()-2<=0?1:this.getPageNum()-2),no = 1; i <= this.getPages()&& no <6 ; i++,no++) {
			if (this.getPageNum() == i) {
				pageHtml.append("<li class='active'><a href='javascript:void(0);' >"+i+"</a></li>");
			}else{
				pageHtml.append("<li><a href='javascript:;' onclick='goPageByAjax("+i+")'>"+i+"</a></li>");
			}
		}
		if(this.getPageNum() < this.getPages()){
			pageHtml.append("<li><a href='javascript:;'  onclick='goPageByAjax("+(this.getPageNum() + 1)+")'>下一页</a></li>");
		}
		pageHtml.append("</ul>");
		return pageHtml.toString();
	}
	
	/**普通翻页*/
	public String getPageHtml(){
		StringBuffer pageHtml = new StringBuffer("<ul class='pagination'>");
		if(this.getPageNum()>1){
			if(this.getPageNum()>5){
				pageHtml.append("<li><a href='javascript:;' onclick='_submitform(1)'>首页</a></li>");
			}
			pageHtml.append("<li><a href='javascript:;'  onclick='_submitform("+(this.getPageNum() - 1)+")'>上一页</a></li>");
		}
		for (int i = (this.getPageNum()-2<=0?1:this.getPageNum()-2),no = 1; i <= this.getPages()&& no <6 ; i++,no++) {
			if (this.getPageNum() == i) {
				pageHtml.append("<li class='active'><a href='javascript:void(0);' >"+i+"</a></li>");
			}else{
				pageHtml.append("<li><a href='javascript:;' onclick='_submitform("+i+")'>"+i+"</a></li>");
			}
		}
		if(this.getPageNum() < this.getPages()){
			pageHtml.append("<li><a href='javascript:;'  onclick='_submitform("+(this.getPageNum() + 1)+")'>下一页</a></li>");
			pageHtml.append("<li><a href='javascript:;'  onclick='_submitform("+(this.getPages())+")'>尾页</a></li>");
		}
		pageHtml.append("</ul>");
		pageHtml.append("<script>");
		pageHtml.append("	function _submitform(pageNo){");
		pageHtml.append("		$(\"#formId\").append($(\"<input type='hidden' value='\" + pageNo +\"' name='pageNo'>\")).submit();");
		pageHtml.append("	}");
		pageHtml.append("</script>");
		
		return pageHtml.toString();
	}
	
	
}
