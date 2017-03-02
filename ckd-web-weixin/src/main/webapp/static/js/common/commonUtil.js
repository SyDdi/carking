
function isGuoQi(d){
	if(d==null||d==''){
		return false;
	}
	var date1=new Date();  
	var date2=new Date(d); 
	var date3=date2.getTime()-date1.getTime();
	if(date3>0){
		var days=Math.floor(date3/(24*3600*1000));
		if(days<30){
			 return true;
		}
	}
	return false;
}

function fixNullString(value){
	var str = "";
	if(value != null && value != "null"){
		str = value;
	}
	return str;
}

// 第一个参数 日期字符串 可以是明显的日期时间格式,也可以是date类型的数字格式,
//第二个参数 格式化日期格式    类似“yyyy-MM-dd”,“yyyy-MM-dd HH:mm:ss”,分为带时分秒和不带的
function getDate(datastr, pattern) {
	 	var date;
		if((""+datastr).indexOf("-")==-1||!isNaN(datastr)){
			  date = new Date(datastr);
		}else{
			if(datastr.indexOf(":")>=0){
				var s = datastr.split(" "); var s1 = s[0].split("-"); var s2 = s[1].split(":");
				date = new Date(s1[0],s1[1]-1,s1[2],s2[0],s2[1],s2[2]);
			}else{
				var s = datastr.split("-");
				date = new Date(s[0],s[1],s[2]);
			}
		}
	    return date.format(pattern);
}
function showStr(str,len){
	if(str==null){
		return "";
	}
	if(str.length>=len){
		return str.substring(0,len)+"...";
	}
	return str;
}

Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	}
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
}
//
//Array.prototype.unique = function(){
//	var res = [this[0]];
//	for(var i = 1; i < this.length; i++){
//		var repeat = false;
//		for(var j = 0; j < res.length; j++){
//			if(this[i] == res[j]){
//				repeat = true;
//				break;
//			}
//		}
//		if(!repeat){
//			res.push(this[i]);
//		}
//	}
//	return res;
//}

function msgAlert(msg) {
	$("#errorMessage-model-modal").remove();
	var str = '<div class="modal fade" id="errorMessage-model-modal" tabindex="-1" role="basic" aria-hidden="true">'
			+ '<div class="modal-dialog modal-message">'
			+ '<div class="modal-content">'
			+ '<div class="modal-body">'
			+ '<div class="portlet box yellow">'
			+ '<div class="portlet-title" >'
			+ '<div class="caption">提示信息</div>'
			+ '<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>'
			+ '</div>'
			+ '<div class="portlet-body">'
			+ '<div class="alert alert-warning" >' + '<p>';
	str += msg;
	str += '</p>'
			+ '</div>'
			+ '<p align="right"><input type="submit" name="button" id="button" data-dismiss="modal" class="btn red" value="确认" /></p>'
			+ '</div>' + '</div>' + '</div>' + '</div>' + '</div>' + '</div>';
	$(".table-toolbar:last").append(str);
	$("#errorMessage-model-modal").modal("show");
}

function AllValidate(formId){
	$("#"+formId+" .help-block").each(function() {
		$(this).text("");
	});
	
	$("#"+formId+" .has-error").each(function() {
		$(this).removeClass("has-error");
	});
}


//字符串格式化
//参数说明：num 要格式化的数字 n 保留小数位
function formatNum(num,n) {
	if(typeof(num) == "undefined"||num==""||num==0||num==null){
		num = 0;
	}
  num = String(num.toFixed(n));
  var re = /(-?\d+)(\d{3})/;
  while(re.test(num)) num = num.replace(re,"$1,$2")
  //return "¥"+num+"元";
  return ""+num+"";
}
//将数字转换成大写人民币
function uppercaseMoney(num) { //转成人民币大写金额形式 
	var str1 = '零壹贰叁肆伍陆柒捌玖'; //0-9所对应的汉字 
	var str2 = '万仟佰拾亿仟佰拾万仟佰拾元角分'; //数字位所对应的汉字 
	var str3; //从原num值中取出的值 
	var str4; //数字的字符串形式 
	var str5 = ''; //人民币大写金额形式 
	var i; //循环变量 
	var j; //num的值乘以100的字符串长度 
	var ch1; //数字的汉语读法 
	var ch2; //数字位的汉字读法 
	var nzero = 0; //用来计算连续的零值是几个 

	num = Math.abs(num).toFixed(2); //将num取绝对值并四舍五入取2位小数 
	str4 = (num * 100).toFixed(0).toString(); //将num乘100并转换成字符串形式 
	j = str4.length; //找出最高位 
	if (j > 15) {
		return '溢出';
	}
	str2 = str2.substr(15 - j); //取出对应位数的str2的值。如：200.55,j为5所以str2=佰拾元角分 

	//循环取出每一位需要转换的值 
	for (i = 0; i < j; i++) {
		str3 = str4.substr(i, 1); //取出需转换的某一位的值 
		if (i != (j - 3) && i != (j - 7) && i != (j - 11)
				&& i != (j - 15)) { //当所取位数不为元、万、亿、万亿上的数字时 
			if (str3 == '0') {
				ch1 = '';
				ch2 = '';
				nzero = nzero + 1;
			} else {
				if (str3 != '0' && nzero != 0) {
					ch1 = '零' + str1.substr(str3 * 1, 1);
					ch2 = str2.substr(i, 1);
					nzero = 0;
				} else {
					ch1 = str1.substr(str3 * 1, 1);
					ch2 = str2.substr(i, 1);
					nzero = 0;
				}
			}
		} else { //该位是万亿，亿，万，元位等关键位 
			if (str3 != '0' && nzero != 0) {
				ch1 = "零" + str1.substr(str3 * 1, 1);
				ch2 = str2.substr(i, 1);
				nzero = 0;
			} else {
				if (str3 != '0' && nzero == 0) {
					ch1 = str1.substr(str3 * 1, 1);
					ch2 = str2.substr(i, 1);
					nzero = 0;
				} else {
					if (str3 == '0' && nzero >= 3) {
						ch1 = '';
						ch2 = '';
						nzero = nzero + 1;
					} else {
						if (j >= 11) {
							ch1 = '';
							nzero = nzero + 1;
						} else {
							ch1 = '';
							ch2 = str2.substr(i, 1);
							nzero = nzero + 1;
						}
					}
				}
			}
		}
		if (i == (j - 11) || i == (j - 3)) { //如果该位是亿位或元位，则必须写上 
			ch2 = str2.substr(i, 1);
		}
		str5 = str5 + ch1 + ch2;

		if (i == j - 1 && str3 == '0') { //最后一位（分）为0时，加上“整” 
			str5 = str5 + '整';
		}
	}
	if (num == 0||typeof(num) == "undefined"||num=="") {
		str5 = '零元整';
	}
	return str5;
}

function DX(n) {
    if (!/^(0|[1-9]\d*)(\.\d+)?$/.test(n))
        return "数据非法";
    var unit = "千百拾亿千百拾万千百拾元角分", str = "";
        n += "00";
    var p = n.indexOf('.');
    if (p >= 0)
        n = n.substring(0, p) + n.substr(p+1, 2);
        unit = unit.substr(unit.length - n.length);
    for (var i=0; i < n.length; i++)
        str += '零壹贰叁肆伍陆柒捌玖'.charAt(n.charAt(i)) + unit.charAt(i);
    return str.replace(/零(千|百|拾|角)/g, "零").replace(/(零)+/g, "零").replace(/零(万|亿|元)/g, "$1").replace(/(亿)万|壹(拾)/g, "$1$2").replace(/^元零?|零分/g, "").replace(/元$/g, "元整");
}

function toUppercase(firstId,secondId){
	var value = "";
	if($("#"+firstId).is('div') || $("#"+firstId).is('span')){
		value = $("#"+firstId).text();
	}else{
		value = $("#"+firstId).val();
	}
	var up = "";
	if(value=="0" || value == ""){
		up = "零";
	} else {
		up = DX(Number(value));
	}
	if($("#"+secondId).is('div') || $("#"+secondId).is('span')){
		$("#"+secondId).text(up);
	}else{
		$("#"+secondId).val(up);
	}
}


//页面加载完成绑定重置按钮
$(document).ready(function (){
	$(".btn.btn-reset").click(function (){
		$(".table-toolbar.search-conditions").find("input").each(function (){
			if($(this).is("input[type='text']")){
				$(this).val("");
			} else if(($(this).is("input[type='checkbox']")||$(this).is("input[type='radio']"))&&$(this).attr("checked")){
				$(this).attr("checked",false);
			}
		});
		$(".table-toolbar.search-conditions").find("select").each(function (){
			$(this).val("");
		});
	});
});

/**
 * 判断是不是方法
 * @param fn
 * @returns {boolean}
 */
function isFunction(fn) {
	return Object.prototype.toString.call(fn)=== '[object Function]';
}