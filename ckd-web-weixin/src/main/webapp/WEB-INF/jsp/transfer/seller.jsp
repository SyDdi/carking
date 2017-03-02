<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<meta name="format-detection" content="telephone=no">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>${model.carYear}款 ${model.brand} ${model.familyGroupName} ${model.family} ${model.shortName}</title>
	<%@ include file="/WEB-INF/jsp/common/config.jsp"%>
	<link href="${ctx}/static/css/css.css?time=20161228" rel="stylesheet" type="text/css">
	<link href="${ctx}/static/css/swiper-3.4.0.min.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="${ctx}/static/css/weui.css?ts=1420774989">
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/mycar/swiper-3.4.0.jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/mycar/carSource.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/mycar/delete.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/mycar/jquery.mycardetails.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/weixin/weixinToken.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/common/check_number.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/common/commonUtil.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/jquery.touchSlider.js"></script>
	
	<script type="text/javascript">

	/*计算轮播图片宽高*/
	var shareImgUrl = "";
	function computeImg(){
		var main_pic_height = ($(document.body).width())*3/4;
		var main_pic_width = ($(document.body).width());
		$(".main_visual").css("height",main_pic_height);
		$(".swiper-wrapper").css("height",main_pic_height);
		$(".swiper-slide").css({"text-align":"center"});

		$('.swiper-wrapper .swiper-slide img').each(function(){
			//var width = $(this).width();
			var height = $(this).height();
			var img = new Image();
			img.src =$(this).attr("src") ;
			var w = img.width;
			var h = img.height;

			if(w < h){
				$(this).css("height",main_pic_height);
				$(this).css({"width":"auto"});
			} else{
				$(this).css({"height":"auto"});
				$(this).css({"width":main_pic_width});
			}
		});
	}

	$(function(){

		var userJson = JSON.parse(sessionStorage.getItem('CKD_WX_USER'));
		if(userJson != null ) {
			$("#userId").val(userJson.id);
		}
		favorite();

		if("${vehicle.telephone}" != null && "${vehicle.telephone}" != "") {
			$("#whoxPhone").hide();
		}

		var phoneExist="${isSave!=null&&isSave!=''}";
		
		/*加载用户上传图片*/
		$.ajax({
			type:"get",
			url:"${ctx}/transfer/pictures?vhclId=${vehicle.id}",
			dataType:"json",
			success:function(data){
				var endData=data.data;
				var strHtml = "";
				for(var i in endData){
					if(endData[i].img != "" && endData[i].img!= null){
						if(shareImgUrl == ""){
							shareImgUrl = '${imgPath}'+endData[i].img;
						}
						strHtml += '<div class="swiper-slide"><img id="lunBoimages'+endData[i].type+'" src="${imgPath}'+endData[i].img+'" align="absmiddle"/></div>';
					}
				}
				if(strHtml == "" || strHtml == null) {
					if("${isSave}"!=null&&"${isSave}"!=''){
						strHtml = '<div class="swiper-slide"><img src="${ctx}/static/images/no_pic01.jpg" align="absmiddle"/></div>'
					}else if("${isSave}"==null||"${isSave}"=='' && ${vehicle.status} !=null &&  ${vehicle.status} == 1 ){
						strHtml = '<div class="swiper-slide"><img src="${ctx}/static/images/no_pic.jpg" align="absmiddle"/></div>'
					}
				}
				$("#lunBoimages").html(strHtml);
				computeImg();
				setTimeout(function(){//由于网络问题可能造成图片轮播 出现问题 所以 延时 1000ms  计算图片宽高 轮播等
					computeImg();
				}, 200);

				/*图片轮播*/
				var mySwiper = new Swiper ('.swiper-container', {
					direction: 'horizontal',/*横向轮播*/
					loop: false,
					autoplay : 3000,
					speed:1000,
					effect : 'fade',/*切换效果*/
					pagination: '.swiper-pagination',/*分页器*/
					freeMode:false,
					touchRatio:1,
					longSwipesRatio:0.1,
					threshold:50,
					followFinger:true,
					observer: true,//修改swiper自己或子元素时，自动初始化swiper
					observeParents: true,//修改swiper的父元素时，自动初始化swiper
					autoplayDisableOnInteraction : false //滑动之后自动在滑动
				});
			}
		});
		
		/*车况*/
		$.ajax({
			type:"get",
			url:"${ctx}/transfer/report?vhclId=${vehicle.id}",
			dataType:"json",
			success:function(data){
				$("#carReport").hide();
				$(".car_details_inf01").css("border-bottom","0px solid #EDEDED");
				if(data.result == 1){
					$(".car_details_inf01").css("border-bottom","1px solid #EDEDED");
					var endData=data.data.issue;
					var luData="";
					for(var i in endData){
						var liData="";
						if(endData[i].id == 6 && endData[i].answerIds.indexOf('存在故障')>=0){
							liData="<li><dl><dt>"+endData[i].issueDesc+":</dt><dd> "+data.data.electron+"</dd></dl></li>";
						}else if(endData[i].id==8){
							liData="<li><dl><dt>"+endData[i].issueDesc+":</dt><dd>"+data.data.transfer+"</dd></dl></li>";
						}else if(endData[i].id!=1&&endData[i].id!=7){
							liData="<li><dl><dt>"+endData[i].issueDesc+":</dt><dd>"+endData[i].answerIds+"</dd></dl></li>";
						}
						luData+=liData;
					}
					luData = luData.replace(/未填写/g,"--");
					$("#reportId").html(luData);
					$("#reportDescId").html(data.data.reportDesc);
					var starEle="车况综合评级：";

					if(data.data.star!=""&&data.data.star!=null){
						var starNum=parseInt(data.data.star);
						for(var j=0;j<5;j++){
							if(j<starNum){
								starEle+='<img src="${ctx}/static/images/star01.png" align="absmiddle">';
							}else{
								starEle+='<img src="${ctx}/static/images/star02.png" align="absmiddle">';
							}
						}
					}else{
						starEle+="- -";
					}
					$("#starId").html(starEle);
					$("#carReport").show();
				}
			}
		});

		/* 报价 */
		$.ajax({
			type:"get",
			url:"${ctx}/transfer/quotes?vhclId=${vehicle.id}",
			dataType:"json",
			success:function(data){
				var endData=data.data;
				var strHtml = '';
				for(var i in endData){
					if(i == 0){
						strHtml = '<div class="dealer_T"><dl><dd>车商</dd><dd>报价</dd><dt></dt></dl></div>';
					}
					
					var date=new Date(endData[i].createDate);
					var update= date.format("yyyy/MM/dd");//getDate(data,"yyyy/MM/dd");  //date.getFullYear()+"/"+date.getMonth()+"/"+date.getDate();
					var satisResult=endData[i].status==1?'满 意':'不满意';
					var endQuote=(parseFloat(endData[i].quote)/10000).toFixed(2);
					var satisfied;
					
					if(endData[i].status==-1){
						satisfied="<dt><ul id='"+endData[i].id+"'><li name='satisfied'>满意</li><li name='satisfied' style='padding:0px 5px;'>不满意</li></ul></dt>";
					}else{
						satisfied="<dt><ul><li style='background:gray'>"+satisResult+"</li></ul></dt>";
					}
					strHtml +=
					"<div class='dealer_B'>"+
						"<dl><dd>"+endData[i].nickName+"</dd><dd>"+endQuote+"万<br><span>"+update+"</span></dd>"+satisfied+"</dl>"+
					"</div>"+
					"<div class='dealer_B_det'>"+endData[i].quoteDesc+"</div>";
					
				}
				
				
				if(strHtml=="" || strHtml == null){
					$(".car_details_inf03").hide();
				}
				
				$(".dealer_T").html(strHtml);
 				$("li[name='satisfied']").click(function(){
 					var id=$(this).parent().attr("id");
 					var isSatisfied=$(this).index()==0?1:0;
 					var _this=$(this);
 					$.ajax({
 						type:"get",
 						url:"${ctx}/transfer/satisfied?quoteId="+id+"&isSatisfied="+isSatisfied,
 						dataType:"json",
 						success:function(data){

							//alertMsg("稍后会有客服与您联系")
							var result=(data.message==1?'满 意':'不满意');
  							_this.parent().html("<li style='background:gray'>"+result+"</li>");
 						}
 					});
 				});
			}
		});
		/* 修改手机号 */
		$("#phoneClick").click(function(){
			var telephone = $("#telephone").val()
			if(!isMobilePhone(telephone)){
				alertMsg("请输入正确的手机号码！");
				return;
			}
			var vhclId = $("#vhclId").val();
			if(telephone!=null&&telephone!=""){
				$.ajax({
					type:"get",
					url:"${ctx}/transfer/phone?vhclId="+vhclId+"&telephone="+telephone,
					dataType:"json",
					success:function(data){
						var result=(data.message==1?'手机号已提交':'提交失败');
						alertMsg(result);
						$("#whoxPhone").hide();
					}
				});
			}else {
				alertMsg("请输入手机号码！");
			}
		});


		$(".show_under ul").bind('click',function(){
		   $(this).children().eq(1).slideToggle();
		});

	// 展示和下架 触发时间
		$("#showId").click(function(){
			var _this=$(this);
			var _status = 1;//默认展示
			if(_this.text() == "下架"){
				_status=0;
				$("#showId").html("展示");
				$("#showTu").html("下架<img src=\"${ctx}/static/images/intro_car_details_01.png\">");
				$("#vehicleStatus").val("0");
				$("#copyright").attr("class","copyright01");
				$("#xxxxx").hide();
				alertMsg("已下架");
			}else {
				$("#showId").html("下架");
				$("#showTu").html("展示<img src=\"${ctx}/static/images/intro_car_details_01.png\">");
				$("#vehicleStatus").val("1");
				$("#copyright").attr("class","copyright");
				$("#xxxxx").show();
				alertMsg("已展示");
			}
			$.ajax({
				type:"get",
				url:"${ctx}/transfer/show?vhclId=${vehicle.id}"+"&status="+_status,
				dataType:"json",
				success:function(data){
					var sta=data.status;
					if(sta==0){
						//$("#collect_zs").text("已下架")
					}else{
						//$("#collect_zs").text("已展示")
					}
				}
			});
		});
		//判断是否显示留言输入框
		if("${vehicle.status}" !=null &&  "${vehicle.status}" == "1"){
			$("#copyright").attr("class","copyright");
			$("#xxxxx").show();
		}else{
			$("#copyright").attr("class","copyright01");
			$("#xxxxx").hide();
		}
		/*显示蒙版*/
		if("${isSave}"!=null && "${isSave}"!=""){//车主
			$('.masking_logo dd').attr('src','${ctx}/static/images/intro_car_details_02.png');
			$('.masking_nr .pic img').attr('src','${ctx}/static/images/masking_01.png');

			if(localStorage.getItem('pointer1')!=null || localStorage.getItem('pointer1')==1){
				$('.masking01').hide();
			}else{
				localStorage.setItem('pointer1',1);
				$('.masking01').show();
			}
		}else {//非车主
			$('.masking_logo dd').remove();
			$('.masking_nr .pic img').attr('src','${ctx}/static/images/masking_02.png');

			if(localStorage.getItem('pointer2')!=null || localStorage.getItem('pointer2')==1){
				$('.masking01').hide();
			}else{
				localStorage.setItem('pointer2',1);
				$('.masking01').show();
			}
		}
	});
	
	$(function(){
        $("#imageContainer").click(function(){ //收藏
			var userId = $("#userId").val();
			if($("#yes").attr("class")!= ""){// 已收藏 进入
				qikoo.dialog.confirm("取消收藏后，您将无法收到留言提醒！",function(){
					$("#yes").attr("class","")
					$("#no").attr("class","active")
					udpateShrine(userId);
				},function(){
					$("#yes").attr("class","active")
					$("#no").attr("class","")
				});
			}else{ //未收藏 进入
				$("#yes").attr("class","")
				$("#no").attr("class","active")
				udpateShrine(userId);
			}
        });
    });

	/**
	 * 修改收藏状态
	 */
	function udpateShrine(userId){
		$.ajax({
			type:"get",
			url:"${ctx}/transfer/collect?vhclId=${vehicle.id}&userId="+userId,
			dataType:"json",
			success:function(data){
				var sta=data.status;
				if(sta == 20){
					$(".concern").show();
				}else if(sta==1){
//					$("#collect_fc").show().delay(1000).fadeOut(1000);
					alertMsg("已收藏")
					$("#yes").attr("class","active");
					$("#no").attr("class","");
				}else if(sta==0){
					$("#yes").attr("class","");
					$("#no").attr("class","active");
				}else{
					alertMsg("操作失败");
				}
			}
		});
	}


	/**
	 * 当前登录用户是否收藏该车
	 */
	function favorite(){
		var userId = $("#userId").val();
		var vhclId = $("#vhclId").val();
		$.ajax({
			type:"get",
			url:"${ctx}/transfer/favorite?vhclId="+vhclId+"&userId="+userId,
			dataType:"text",
			success:function(data){
				$("#no").attr("class","active")
				$("#yes").attr("class","")
				if(data > 0){
					$("#yes").attr("class","active")
					$("#no").attr("class","")
				}
			}
		});
	}

	$(function() {//当用户没有订阅的时候点击收藏 是弹出公众号二维码让他关注 才能收藏
		$(".concern .closeco").click(function(){
			$(".concern").hide();
			if($("#yes").attr("class")!= ""){
				$("#yes").attr("class","active")
				$("#no").attr("class","")
			}else{
				$("#yes").attr("class","");
				$("#no").attr("class","active");
			}
		});
		$('.concern').bind("touchmove",function(e){
			e.preventDefault();
		});


	});

	function alertMsg(msg){
		var _height = $(window).height();
		$("#collect_zs").css('top',(_height *0.4)+$(window).scrollTop());
		$("#collect_zs").text(msg)
		$("#collect_zs").delay(500).show().delay(1000).fadeOut(1000);
	}


	
</script>
<%--  微信分享js   --%>
	<script>
		wx.config({
			debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			appId: '${signature.appid }', // 必填，公众号的唯一标识
			timestamp:'${signature.timestamp }' , // 必填，生成签名的时间戳
			nonceStr:'${signature.noncestr }', // 必填，生成签名的随机串
			signature: '${signature.signature}',// 必填，签名，见附录1
			jsApiList:[
				// 所有要调用的 API 都要加到这个列表中
//				'checkJsApi',
				'onMenuShareTimeline',
				'onMenuShareAppMessage',
				'onMenuShareQQ',
//				'onMenuShareWeibo'
			]
		});
		var titleDesc = "${model.brand} ${model.familyGroupName}";
		if("${model.familyGroupName}" == ""){
			titleDesc += "${model.family}";
		}
		setTimeout(function(){//延时1.5s 加载分享信息 保证 图片可以获取到
			wx.ready(function () {
				wx.onMenuShareTimeline({
					title: '【熟人转让】'+  titleDesc,
					link: '${url}',
					desc:"可信的车，可靠的人。这是您的朋友转让的一部车，点击了解详情。",
					imgUrl: shareImgUrl,
					trigger: function (res) {
//					alert('用户点击分享到朋友圈');

					},
					success: function (res) {
						share(0);//朋友圈

					},
					cancel: function (res) {
//					alert('已取消');

					},
					fail: function (res) {
//					alert(JSON.stringify(res));

					}
				});
				wx.onMenuShareAppMessage({
					title:  '【熟人转让】'+ titleDesc,
					link: '${url}',
					desc:"可信的车，可靠的人。这是您的朋友转让的一部车，点击了解详情。",
					imgUrl: shareImgUrl,
					trigger: function (res) {
//					alert('用户点击分享到朋友');

					},
					success: function (res) {
						//alert('已分享');
						share(1)//朋友
					},
					cancel: function (res) {
//					alert('已取消');

					},
					fail: function (res) {
//					alert(JSON.stringify(res));

					}
				});
				wx.onMenuShareQQ({
					title:  '【熟人转让】'+ titleDesc,
					link: '${url}',
					desc:"可信的车，可靠的人。这是您的朋友转让的一部车，点击了解详情。",
					imgUrl: shareImgUrl,
					trigger: function (res) {
//					alert('用户点击分享到朋友');

					},
					success: function (res) {
						share(2)//QQ
					},
					cancel: function (res) {

					},
					fail: function (res) {

					}
				});
				wx.error(function (res) {
	//				alert(res.errMsg +"  ");

				});
			});
		},1500);
		/**
		 * 分享数据添加到数据库
		 * @param shareSource 分享来源
         */
		function share(shareSource){
			var userId = $("#userId").val();
			var vhclId = $("#vhclId").val();
			$.ajax({
				type:"get",
				url:"${ctx}/transfer/share?vhclId="+vhclId+"&userId="+userId+"&shareSource="+shareSource,
				dataType:"text",
				success:function(data){

				}
			});
		}

	</script>
<%--  微信分享js  end --%>
</head>


<body>
<div id="collect_zs" style="display:none"></div>
<input type="hidden" id="vhclId" value="${vehicle.id}"/>
<input type="hidden" id="vehicleStatus" value="${vehicle.status}"/>
<input type="hidden" id="userId" value="${userId}">
<input type="hidden" id="isOwner" value="${isSave}">
<div class="car_details_header">
	<div class="main_visual">
		<div class="swiper-container">
			<div class="swiper-wrapper" id="lunBoimages">

			</div>
			<!-- 如果需要分页器 -->
			<div class="swiper-pagination"></div>
		</div>
	</div>

	<c:if test="${isSave!=null&&isSave!=''}">
		 <div class="show_under">
		  <ul>
			  <li id="showTu">${vehicle.status!=0&&vehicle.status!=null?"展示":"下架"}<img src="${ctx}/static/images/intro_car_details_01.png"></li>
			  <li style="display: none"  id="showId">${vehicle.status ==null || vehicle.status ==0 ?"展示":"下架"}</li>
		  </ul>
		 </div>
	</c:if>
	 <c:if test="${(isSave==null||isSave=='') &&( vehicle.status !=null && vehicle.status == 1 )}">
		 <div class="collect" >
			  <ul>
			   <li>
				<div id="imageContainer">
					<img src="${ctx}/static/images/intro_car_details1_05.png" align="absmiddle" id="yes">
					<img src="${ctx}/static/images/intro_car_details_05.png" align="absmiddle" id="no" >
				</div>
			   </li>
			  </ul>
		 </div>
	 </c:if>
 
 <div class="share" onclick="$(this).hide();"><img src="${ctx}/static/images/intro_car_details_02.png"></div>
</div>

<section class="wbox">
 <div class="car_details_id_editor">
  <dl><dt>车辆ID:${vehicle.id }</dt>
	  <c:if test="${isSave!=null&&isSave!=''}">
	  	  <dd onclick="window.location.href='${ctx}/transfer/edit?id=${vehicle.hashId}'">编辑<img src="${ctx}/static/images/intro_car_details_03.png"></dd>
	  </c:if>
  </dl>
 </div>
</section>

<section class="wbox">
 <div class="car_details_price">
  <dl><dt>车主报价:
	  <c:if test="${isSave != null&&isSave!=''}">
		  ${purchasePrice}万元
	  </c:if>
	  <c:if test="${isSave == null||isSave==''}">
		  <c:if test="${vehicle.status !=null && vehicle.status == 1}">
			  ${purchasePrice}万元
		  </c:if>
		  <c:if test="${vehicle.status !=null && vehicle.status != 1}">
			  已下架
		  </c:if>
	  </c:if>
  	  </dt>
	  <dd>${publicDate } 更新</dd>
  </dl>
 </div>
</section>

<section>
 <div class="car_details_inf01">
 
	<c:if test="${isSave==null||isSave==''}">
	 <div class="tx">
	 	<dl><dt><img src="${showUser.headImgUrl}"></dt><dd>${showUser.nickName}</dd></dl>
	</div>
	</c:if>
 
  <div class="main box h1">${model.carYear}款 ${model.brand} ${model.familyGroupName} ${model.family} ${model.shortName}</div>
  <div class="main box t1">
   <ul>
    <li><dl><dt>所在城市:</dt><dd>${zoneName==""?"--":zoneName}</dd></dl></li>
    <li><dl><dt>牌照属地:</dt><dd>${regZone==""?"--":regZone}</dd></dl></li>
    <li><dl><dt>上牌日期:</dt><dd>${vehicle.regDate==""?"--":vehicle.regDate}</dd></dl></li>
    <li><dl><dt>行驶里程:</dt><dd>${vehicle.miles==""?"--":vehicle.miles}万公里</dd></dl></li>
    <li><dl><dt>车身颜色:</dt><dd>${vehicle.outerColor==""?"--":vehicle.outerColor}</dd></dl></li>
    
    <c:if test="${isSave != null&&isSave!=''}">
    	<li><dl><dt>车牌号码:</dt><dd>${(vehicle.numberPlate==""?"--":vehicle.numberPlate)}</dd></dl></li>
    </c:if>
   </ul>
  </div>
  <div class="main box more">
   <dl>
    <dt>更多车辆信息说明:</dt>
	<dd><ol id="morecarinfo" >${vehicle.info}</ol></dd>
   </dl>
  </div>
 </div>
</section>

<section id="carReport">
 <div class="car_details_inf02">
  <div class="main box grade" id="starId">车况综合评级：<img src="${ctx}/static/images/star01.png" align="absmiddle"><img src="${ctx}/static/images/star01.png" align="absmiddle"><img src="${ctx}/static/images/star01.png" align="absmiddle"><img src="${ctx}/static/images/star02.png" align="absmiddle"><img src="${ctx}/static/images/star02.png" align="absmiddle"></div>
  <div class="main box t1">
   <ul id="reportId">
   </ul>
  </div>
  <div class="main box more">
   <dl>
    <dt>更多车况描述：</dt>
	<dd><ol id="reportDescId"></ol></dd>
	<dd><span><em>*</em>以上信息仅供参考，实际车况请以现场实车检测为准。</span></dd>
   </dl>
  </div>
 </div>
</section>

<c:if test="${isSave!=null&&isSave!=''}">
	<section>
	<a href="#dealer_Q"></a>
	 <div class="car_details_inf03">
	  <div class="dealer_T">
	   <%--<dl><dd>车商</dd><dd>报价</dd><dt></dt></dl>--%>
	  <%--</div>--%>
	<%--<!--   <div class="dealer_B">--%>
	   <%--<dl><dd>车王认证二手车超市</dd><dd>20.58万<br><span>2016/11/04</span></dd><dt><ul><li>满　意</li><li style="padding:0px 5px;">不满意</li></ul></dt></dl>--%>
	  <%--</div>--%>
	  <%--<div class="dealer_B">--%>
	   <%--<dl><dd>天天拍车</dd><dd>19.00万<br><span>2016/11/07</span></dd><dt><ul><li>满　意</li><li style="padding:0px 5px;">不满意</li></ul></dt></dl>--%>
	  </div>
	 </div>
	</section>
	<section class="wbox phone" id="whoxPhone">
	 <div class="car_details_inf04">
		 <div class="entry">
			 <dl><dt><input id="telephone" name="telephone" type="text" value="请输入联系手机号码" style="color:#BABABA;" onfocus="if(this.value=='请输入联系手机号码'){this.style.color='#019BFF';this.value=''}" onblur="if(this.value==''){this.style.color='#BABABA;'; this.value='请输入联系手机号码'}"></dt><dd><ul><li id="phoneClick">确　定</li></ul></dd></dl><div class="close"><img src="${ctx}/static/images/intro_car_details_04.png"></div>
		 </div>
	  <div class="prompt">留下手机号，会有专业人员与您核实车况，以提供更标准的车况描述。我们承诺，未经您的同意，我们不会将您的手机号透露给第三方。</div>
	 </div>
	</section>
</c:if>



<section class="wbox" id="car_written">
<%-- <div class="car_details_inf05">留言</div>--%>
	<div class="car_details_inf05">
		<dl>
			<dt>留言</dt>
			<c:if test="${subscribe == null || subscribe==''}">
				<dd onclick="$('.concern').show();">
					<span class="hint">关注“行情价” <img src="${ctx}/static/images/intro_ydgz.png" align="absmiddle"></span>
					<ol><img src="${ctx}/static/images/intro_ydgz_jt02.png"></ol>
					<ul class="hintNext">关注行情价公众号<br>获得最及时的车源相关信息</ul>
				</dd>
			</c:if>
		</dl>
	</div>


	<div class="main box car_written"> </div>
</section>

<%--<section class="wbox">
 <div class="car_details_inf06">向下加载留言信息</div>
</section>--%>


	<div class="main box car_details_write" id="xxxxx">
		 <dl>
		  <dt><input id="message" name="username" type="text" value="" placeholder="请输入文字留言" style="color:#000;" onfocus="if(value=='请输入文字留言'){this.style.color='#000';value=''}" onblur="if(this.value==''){this.style.color='#BABABA'; this.value='请输入文字留言'}" parentId=""/></dt>
		  <dd><button id="sendId">发送</button></dd>
		 </dl>
	</div>
<c:if test="${vehicle.status !=null && vehicle.status== 1 }">
</c:if>

<!--提示关注微信公众号-->
<div class="concern" style="display: none;">
	<ul>
		<li>
			<dl>				
				<dd><img src="/static/images/intro_car_details_05.jpg"></dd>
				<dt>扫码及时获取更多</dt>
			</dl>
			<div class="closeco"><img src="/static/images/intro_car_details_04.png"></div>
		</li>
	</ul>
</div>
<!--提示关注微信公众号-->

<!--蒙版-->
<div class="masking01">
	<div class="masking_logo">
		<dl>
			<dt><img src="${ctx}/static/images/masking_logo.png"></dt>
			<dd><img src="${ctx}/static/images/intro_car_details_02.png"></dd>
		</dl>
	</div>
	<div class="masking_nr">
		<div class="pic"><img src=""></div>
		<div class="closeco"><img src="${ctx}/static/images/intro_car_details_04.png"></div>
	</div>
</div>
<!--蒙版-->


<script language="javascript">
	$(document).ready(function(){
		$(".masking01 .closeco").click(function(){
			$(".masking01").hide();
		})
		var ifHint = true;
		$(window).scroll(function(){
			var scrollH = $(this).scrollTop();
			//console.info(scrollH);//690
			//var hintH = $('.hint').offset().top-$(document).scrollTop();
			if(ifHint){
				if(scrollH > 100){
					ifHint = false;
					$(".hintNext").fadeIn(800).delay(5000).fadeOut(1000);
					$('.car_details_inf05 dl dd ol img').fadeIn(800).delay(5000).fadeOut(1000);
				}
			}

		})
	});

	$('.masking01').bind("touchmove",function(e){
		e.preventDefault();
	});
</script>

<section>
	<div class="copyright" id="copyright">copyright © 2017 hangqingjia.com  沪ICP备11044813号</div>
</section>
</body>
</html>
<script>
	var _hmt = _hmt || [];
	(function() {
		var hm = document.createElement("script");
		hm.src = "//hm.baidu.com/hm.js?fed9ca6874b32c074b1602d4e1a78656";
		var s = document.getElementsByTagName("script")[0];
		s.parentNode.insertBefore(hm, s);
	})();
</script>
