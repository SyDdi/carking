package com.car.mp.util;

public class PageHelper {

	static int maxPage = 10000;
	static int showNum = 7;

//	<div class="main box fy">
//	   <a href="#"><em>上一页</em></a>
//	   <span><em>1</em></span>
//	   <a href="#"><em>2</em></a>
//	   <a href="#"><em>3</em></a>
//	   <a href="#"><em>4</em></a>
//	   <a href="#"><em>5</em></a>
//	   <span>...</span>
//	   <a href="#"><em>下一页</em></a>
//	   <a href="#"><em>最后一页</em></a>
//	   <span>&nbsp;跳转到&nbsp;<input name="" type="text" class="input_01">&nbsp;页</span>&nbsp;<input type="button" value="GO" class="input_02">
//	  </div>
	  
	/**
	 * max 50 page
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @param record
	 * @return
	 */
	public static String getHtml(String paras, int pageIndex, int pageSize,
			int record) {
		String html = "";
		int diff = showNum / 2 + 1;

		pageIndex = pageIndex > 1 ? pageIndex : 1;
		pageIndex = pageIndex > maxPage ? maxPage : pageIndex;
		if (pageSize > 0 && record > 0) {
			int page = record / pageSize;
			if (record % pageSize > 0) {
				page = page + 1;
			}
			page = page > maxPage ? maxPage : page;

			if (page > 1) {
				html = html + "<div class=\"main box fy\">";
				// prePAGE
				if (pageIndex > 1) {
					html = html + link(paras, pageIndex - 1, "上一页");
				}
				if (pageIndex == 1) {
					html = html + current(pageIndex);
				} else {
					html = html + link(paras, 1, "1");
				}
				if (pageIndex > diff) {
					html = html + more();
				}
				int min = pageIndex - (diff - 2);
				while (min < 2) {
					min++;
				}
				while ((page - min) < diff + 1) {
					min--;
				}
				int max = min + (showNum - 2);
				for (int i = min; i < max; i++) {
					if (i > 1) {
						if (i == pageIndex) {
							html = html + current(i);
						} else {
							html = html + link(paras, i, "" + i);
						}
					}
				}

				if ((page + 1 - pageIndex) > diff) {
					html = html + more();
				}
				//if (page > showNum) {
					if (page == pageIndex) {
						html = html + current(page);
					} else {
						html = html + link(paras, page, "" + page);
					}
				//}
				if (pageIndex < page) {
					html = html + link(paras, pageIndex + 1, "下一页");
				}
				html = html + "</div>";
			}
		}
		return html;
	}

	/**
	 * 
	 * @param paras
	 * @param page
	 * @param linkName
	 * @return
	 */
	private static String link(String paras, int page, String linkName) {
		return "<a href=\"" + getUrl(paras, page) + "\"><em>" + linkName
				+ "</em></a>";
	}

	/**
	 * 
	 * @param page
	 * @return
	 */
	private static String current(int page) {
		return "<span><em>" + page
				+ "</em></span>";
	}

	/**
	 * 
	 * @return
	 */
	private static String more() {
		return "<span> ... </span>";
	}

	/**
	 * 
	 * @param paras
	 * @param pageIndex
	 * @return
	 */
	private static String getUrl(String paras, int pageIndex) {
		String url = paras.replace("@pageIndex", "" + pageIndex);
		 url = url.replace("---", "&");
		 url = url.replace("--", "?");
		return url;
	}
}
