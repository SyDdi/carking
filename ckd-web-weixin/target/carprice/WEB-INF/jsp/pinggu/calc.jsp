<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="com.car.mp.domain.ResultEntity" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="format-detection" content="telephone=no">
<title>总收购价-行情价</title>
<link href="${ctx}/static/css/m_zsgj.css?2016110801" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/js/jquery.tzCheckbox.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.calculation.js"></script>
<script type="text/javascript">
	var bIsFirebugReady = (!!window.console && !!window.console.log);
	//无利润隐藏
	function noProfitRateHidden(){
		$("#profitRate").val()<=0?$("#noProfitRate").show():$("#noProfitRate").hide();
	}
	//悬浮设置值
	function setPrice(){
		$("#totalSum_").html($("#totalSum").val()+"万");
		$("#price_").html("建议零售价:"+$("#suggestPrice").val()+"万&nbsp;&nbsp;预计利润率:"+$("#profitRate").val()+"%");
	}
	//计算器calculation
    function recalc(){
        $("[id^=total_item]").calc(
                // the equation to use for the calculation
                "qty * price",
                // define the variables used in the equation, these can be a jQuery object
                {
                    qty: $("[id^=qty_item_]"),
                    price: $("[id^=price_item_]")
                },
                // define the formatting callback, the results of the calculation are passed to this function
                function (s){
                    // return the number as a dollar amount
                    $(this).val(s);
                },
                // define the finish callback, this runs after the calculation has been complete
                function ($this){
                    // sum the total of the $("[id^=total_item]") selector
                    //var sum = $this.sum();
                    //var totalPrice = sum/10000;
                    $("#totalSum").val(($this.sum()/10000).toFixed(2));
                    var totalSum=$("#totalSum").val();
                    
                    var suggestPrice = $("#suggestPrice").val();
                    var profitRate = (((suggestPrice-totalSum)/totalSum)*100).toFixed(2);
                    totalSum!=0?$("#profitRate").val(profitRate):$("#profitRate").val("");
                    if(totalSum!=0){
        				$("#profitRate").val(profitRate);
        				noProfitRateHidden();
        			}else{
        				$("#profitRate").val("");
        				$("#noProfitRate").hide()
        			}
                    setPrice();
                    
//                        $("#grandTotal").text(
//                                // round the results to 2 digits
//                                        "$" + sum.toFixed(2)
//                        );
                }
        );
    }
    //键盘按下
    function keyPress(e){
     	var char_code=e.charCode?e.charCode:e.keyCode;
     	var element = $(e.srcElement ? e.srcElement : e.target);
     	var positiveJudge=char_code>=48&&char_code<=57||char_code==8;
     	var decimalJudge=char_code>=46&&char_code<=57&&char_code!=47||char_code==8;
     	var nagetiveJudege=char_code>=45&&char_code<=57&&char_code!=47||char_code==8;
     	//var judge=element.attr("id")=="profitRate"?char_code>=45&&char_code<=57&&char_code!=47||char_code==8:char_code>=46&&char_code<=57&&char_code!=47||char_code==8;
     	var judge=element.attr("id")=="profitRate"?nagetiveJudege:(element.attr("id")=="price_item_0")||element.attr("id")=="suggestPrice"?decimalJudge:positiveJudge;
     	if(judge){
        	if((char_code==45)&&(element.val().indexOf("-")==0||$("#pointValue").val()==45)||(char_code==46)&&element.val().indexOf(".")>=0){
        		return false;
        	}
        	$("#originalValue").val(element.val());
        	$("#pointValue").val(char_code);
        	return true;
     	}
		return false;
    }
     //正则判断
    function regularNumber(element,regular){
		var value=$(element).val();
    	var char_code=$("#pointValue").val();
    	var original=$("#originalValue").val();
       	//var valueBack=isInteger?(char_code==45||char_code==46):(value==""&&original>0&&char_code==45)||(value!=""&&!regular.test(value)||(value==""&&char_code==46));
       	var valueBack=(value==""&&original>0&&char_code==45)||(value!=""&&!regular.test(value)||(value==""&&char_code==46));
       	if(valueBack){
    		$(element).val(original);
    		$("#pointValue").val("");
    		return false;
    	}
       	$("#originalValue").val(value);
       	value<0?$("#pointValue").val(""):null;
       	value==""&&char_code!=45?$(element).val(""):null;
       	return true;
    }
	//各项值改变
  	function priceChange(){
    	var regular;
    	var result;
    	var id=$(this).attr("id");
    	if(id.indexOf("price_item_")>=0){
    		if(id=="price_item_0"){
        		regular=/^(\d?)+(\.\d{0,2})?$/;
        		result=regularNumber($(this),regular);
    		}else{
        		regular=/^([0-9]+)$/;
        		result=regularNumber($(this),regular);
    		}
    		result?recalc():null;
    	}else{
    		var tp = $("#totalSum").val();
    		if(id=="suggestPrice"){
    			regular=/^(\d?)+(\.\d{0,2})?$/;
    			result=regularNumber($(this),regular);
    			var pr=((($(this).val()-tp)/tp)*100).toFixed(2);
    			$("#profitRate").val(pr);
    		}else if(id=="profitRate"){
    			regular=/^[-]?(\d?)+(\.\d{0,2})?$/;
    			result=regularNumber($(this),regular);
   				var sp=(($(this).val()/100)*tp+parseFloat(tp)).toFixed(2);
   				$("#suggestPrice").val(sp);
    		}
    		if(result){
    			noProfitRateHidden();
    			setPrice();
    		}
    	}
	}
	$(document).ready(
		function (){
	        $("input[id^=price_item_]").bind("input propertychange", priceChange);
	        $("#suggestPrice").bind("input propertychange", priceChange);
	        $("#profitRate").bind("input propertychange", priceChange);
	        recalc();
        }
    );
    
	$(document).ready(function(){
		$(document).scroll(function(){
			var top = $(document).scrollTop();
			if(top >= 100)
				$(".fc_zsgj_cc").fadeIn("slow");
			else if(top <= 100 )
				$(".fc_zsgj_cc").fadeOut("slow"); 
		});
	});

	$(document).ready(function(){
	  $("#wh_btn").click(function(){
	    $(".fc_zsgj_wh").fadeIn("slow");
	  });
	  $("#zsgj_gb").click(function(){
	    $(".fc_zsgj_wh").fadeOut("slow");
	  });
	});
</script>
</head>

<body>

<header>
 <div class="menu_mc">
  <dl>
   <dd class="fh"><a href="javascript:void(false);history.back();"><img src="${ctx}/static/images/header_fh.png"></a></dd>
   <dt>总收购价</dt>
   <dd><a href="#" id="wh_btn">?</a></dd>
  </dl>
 </div>
</header>

<section class="intro_zsgj_ck">
 <dl>
  <dt>${model.makeYear}款 ${model.make} ${model.seriesName} ${model.modelName}</dt>
  <dd>${area} | ${timeId}上牌 | ${mile}万公里 | ${color} | ${cond}</dd>
 </dl>
</section>

<section class="intro_zsgj_zjg">
 <div class="main box intro_zsgj_A">
 <dl>
  <dt>总收购价</dt>
  <dd><input type="number" name="totalSum" id="totalSum" value="" readonly="readonly"><span>万</span></dd>
 </dl>
 </div>
 <div class="main box intro_zsgh_B">
  <ul>
   <li>
    <dl><dt>建议零售价</dt><dd><input id="suggestPrice" type="number" name="sum" value="${retailPrice}" onkeypress="return keyPress(event)"><span>万</span></dd></dl>
	<dl><dt>预计利润率</dt><dd><input id="profitRate" type="number" name="sum" value="10.34" onkeypress="return keyPress(event)"><span>%</span><div id="noProfitRate"><img src="${ctx}/static/images/L_jt.png"><p>已无利润！</p></div></dd></dl>
   </li>
  </ul>
 </div>
</section>

<section class="intro_zsgj_cs">
<div class="main box bg">
 <div class="intro_zsgj_sgjxf">总收购价细分</div>
 <div class="intro_zsgj_jjzg">
  <dl><dt>基准价格</dt><dd><input id="price_item_0" type="number" name="sum" value="${result.basePrice}" onkeypress="return keyPress(event)"><span>万</span><input type="hidden" name="sum" id="qty_item_0" value="10000" class="sj"><input type="hidden" class="sj" id="total_item0" value="0"/></dd></dl>
 </div>
 
 <div class="intro_zsgj_kbcs">
  <dl><dt>里程</dt><dd>
  	<input name="ch_location" type="checkbox" id="ch_location" ${result.milePrice>=0?"checked":""}/>
    <input type="number" name="sum" id="price_item_1" onkeypress="return keyPress(event)" value="${result.milePrice<0?-result.milePrice:result.milePrice}" class="sj"><span>元</span>
  	<input type="hidden" name="sum" id="qty_item_1" value="${result.milePrice>=0?1:-1}" class="sj">
  	<input type="hidden" class="sj" id="total_item1" value="0"/></dd></dl>
  <dl><dt>车身颜色</dt><dd>
  	<input name="ch_location" type="checkbox" id="ch_location" ${result.colorPrice>=0?"checked":""}/>
  	<input type="number" name="sum" id="price_item_2" onkeypress="return keyPress(event)" value="${result.colorPrice<0?-result.colorPrice:result.colorPrice}" class="sj"><span>元</span>
  	<input type="hidden" name="sum" id="qty_item_2" value="${result.colorPrice>=0?1:-1}" class="sj">
  	<input type="hidden" class="sj" id="total_item2" value="0"/></dd></dl>
  <dl><dt>车况</dt><dd><dd>
  	<input name="ch_location" type="checkbox" id="ch_location" ${purchasePriceDifference>=0?"checked":""}/>
  	<input type="number" name="sum" id="price_item_3" onkeypress="return keyPress(event)" value="${purchasePriceDifference<0?-purchasePriceDifference:purchasePriceDifference}" class="sj"><span>元</span>
  	<input type="hidden" name="sum" id="qty_item_3" value="${purchasePriceDifference>=0?1:-1}" class="sj">
  	<input type="hidden" class="sj" id="total_item3" value="0"/></dd></dl>
  <dl><dt>地区冷热度</dt><dd><dd>
  	<input name="ch_location" type="checkbox" id="ch_location" ${result.zonePrice>=0?"checked":""}/>
  	<input type="number" name="sum" id="price_item_4" onkeypress="return keyPress(event)" value="${result.zonePrice<0?-result.zonePrice:result.zonePrice}" class="sj"><span>元</span>
  	<input type="hidden" name="sum" id="qty_item_4" value="${result.zonePrice>=0?1:-1}" class="sj">
  	<input type="hidden" class="sj" id="total_item4" value="0"/></dd></dl>
  <dl><dt>库存</dt><dd><dd>
  	<input name="ch_location" type="checkbox" id="ch_location" checked/>
  	<input type="number" name="sum" id="price_item_5" onkeypress="return keyPress(event)" value="" class="sj"><span>元</span>
  	<input type="hidden" name="sum" id="qty_item_5" value="1" class="sj">
  	<input type="hidden" class="sj" id="total_item5" value="0"/></dd></dl>
  <dl><dt>时间成本</dt><dd><em>减</em>
  	<input type="number" name="sum" value="" onkeypress="return keyPress(event)" class="sj" id="price_item_2" ><span>元</span><!-- onkeypress="return keyPress(event)" -->
  	<input type="hidden" name="sum" id="qty_item_6" value="-1" class="sj">
  	<input type="hidden" class="sj" id="total_item6" value="0"/></dd></dl>
  <dl><dt>整备费用</dt><dd><em>减</em>
  	<input type="number" name="sum" value="" onkeypress="return keyPress(event)" class="sj" id="price_item_3" ><span>元</span>
  	<input type="hidden" name="sum" id="qty_item_7" value="-1" class="sj">
  	<input type="hidden" class="sj" id="total_item7" value="0"/></dd></dl>
  <dl><dt>物流成本</dt><dd><em>减</em>
  	<input type="number" name="sum" value="" onkeypress="return keyPress(event)" class="sj" id="price_item_4" ><span>元</span>
  	<input type="hidden" name="sum" id="qty_item_8" value="-1" class="sj">
  	<input type="hidden" class="sj" id="total_item8" value="0"/></dd></dl>
  <dl><dt>办证费用</dt><dd><em>减</em>
  	<input type="number" name="sum" value="" onkeypress="return keyPress(event)" class="sj" id="price_item_5" ><span>元</span>
  	<input type="hidden" name="sum" id="qty_item_9" value="-1" class="sj">
  	<input type="hidden" class="sj" id="total_item9" value="0"/></dd></dl>
  <dl><dt>其他</dt><dd>
  	<input name="ch_location" type="checkbox" id="ch_location" checked/>
  	<input type="number" name="sum" value="" onkeypress="return keyPress(event)" class="sj" id="price_item_6" ><span>元</span>
  	<input type="hidden" name="sum" id="qty_item_10" value="1" class="sj">
  	<input type="hidden" class="sj" id="total_item10" value="0"/></dd></dl>
 </div>
 </div>
</section>

<div class="fc_zsgj_cc">
 <div class="T">
  <dl><dt>总收购价:</dt><dd id="totalSum_"></dd></dl>
 </div>
 <div class="B">
  <ul>
   <li id="price_">建议零售价：19.27万&nbsp;&nbsp;&nbsp;预计利润率：10.34%</li>
  </ul>
 </div>
</div>

<div class="fc_zsgj_wh">
 <ul>
  <li>
   <dl>
    <dt>使用说明</dt>
    <dd>
      1. 本功能可帮您计算个性化的收购价、利润率及零售价。<br>
      2. 您可根据车辆的实际情况及自身的运营成本，调整下方各项金额来获得准确收购价。<br>
      3. 在获得收购价后，可输入预计利润率，获得建议零售价；反之也可通过输入目标零售价，了解相应的利润率。
    </dd>
	<dd><span><img src="${ctx}/static/images/zsgj_gb.png" id="zsgj_gb"></span></dd>
   </dl>
  </li>
 </ul> 
</div>

<input type="hidden" id="originalValue"/>
<input type="hidden" id="pointValue"/>

<script src="${ctx}/static/js/jquery.tzCheckbox.js"></script>
<script src="${ctx}/static/js/script.js"></script>
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