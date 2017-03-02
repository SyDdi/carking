	//验证小数的js,使用时,str为传进来的参数,intNum为整数位,deciNum为小数位.return true or false.
	function isDecimal(str,intNum,deciNum){
		var pattern = '^\\d{1,'+intNum+'}(\\.\\d{1,'+deciNum+'})?$';
		var re = new RegExp(pattern);
		return re.test(str);
	} 
	function isInt(str,intNum){
		var pattern = '^\\d{1,'+intNum+'}?$';
		var re = new RegExp(pattern);
		return re.test(str);
	} 
	function isPhone(str){
		var testPhonePattern = /(^(\d{3,4}(-)?)?\d{7,8})$|(^1[3,5,7,8][0-9]{9}$)/;//匹配电话号码和手机号码正则表达式
		return testPhonePattern.test(str);
	} 
	
	function isMobilePhone(str){
		var testPhonePattern = /^1[3,5,7,8][0-9]{9}$/;//匹配手机号码正则表达式
		return testPhonePattern.test(str);
	} 
	function isTelePhone(str){
		var testPhonePattern = /^(\d{3,4}(-)?)?\d{7,8}$/;//匹配座机号码正则表达式
		return testPhonePattern.test(str);
	}
	/**
	 * 只能输入数字和两位小数的
	 * @param obj
     */
	function clearNoNum(obj){
		obj.value = obj.value.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符
		obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字而不是
		obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的
		obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
		obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数
	}
	/**
	 * 车牌号码验证
	 * @param str
	 * @returns {boolean}
     */
	function isCarNumber(str){
		var re=/^[\u4e00-\u9fa5]{1}[A-Z_a-z]{1}[a-z_A-Z_0-9]{5}$/;
		return re.test(str);
	}

