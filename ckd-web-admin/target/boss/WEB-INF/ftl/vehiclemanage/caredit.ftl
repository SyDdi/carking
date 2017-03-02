<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta charset="UTF-8" />
		<title>车辆管理 - 车辆编辑</title>
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
		<link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
		<link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
        <script  src="${basePath}/js/common/jquery/jquery.min.js"></script>
        <script  src="${basePath}/js/common/jquery/jquery.form-2.82.js"></script>
		<script  src="${basePath}/js/common/layer/layer.js"></script>
		<script  src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script  src="${basePath}/js/shiro.demo.js"></script>
        <script  src="${basePath}/js/common/commonUtil.js"></script>
        <script  src="${basePath}/js/common/check_number.js"></script>
        <script src="${basePath}/js/My97DatePicker/WdatePicker.js"></script>
        <script  src="${basePath}/js/common/pinpai.js"></script>
        <script  src="${basePath}/js/common/zone.js"></script>

		<script >
			var basePath = "${basePath}";
			so.init(function(){
				//初始化全选。
				so.checkBoxInit('#checkAll','[check=box]');
				//重置选项
				so.id('deleteAll').on('click',function(){
                    $(':input','#formId').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
				});
			});
			<@shiro.hasPermission name="/permission/addPermission.shtml"><!--  验证是否有权限  name为权限名称    -->
			</@shiro.hasPermission>
		</script>
	</head>
	<body data-target="#one" data-spy="scroll">
		<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
			<div class="row">
				<input type="hidden" id="vhclId" value="${vhclId?default("-1")}">
				<div class="col-md-10">
					<h2>车辆编辑</h2>
					<#--车主信息编辑-->
                    <hr>
					<#include "/vehiclemanage/careditbox/caredit_ownerinfo.ftl">
					<#--车主信息编辑end-->
                    <!--  车主分享信息  -->
					<hr>
					<#include "/vehiclemanage/careditbox/caredit_carshare.ftl">
                    <!--  车主分享信息end  -->

					<#include "/vehiclemanage/careditbox/caredit_sendmessage.ftl">

					<#-- 图片上传 -->
                    <hr>
					<#include "/vehiclemanage/careditbox/caredit_carImg.ftl">
					<#-- 图片上传end -->
					<#--车辆基本信息-->
                    <hr>
					<#include "/vehiclemanage/careditbox/caredit_carinfo.ftl">
					<#--车辆基本信息end-->
					<#--车辆车况信息-->
					<hr>
					<#include "/vehiclemanage/careditbox/caredit_carreport.ftl">
					<#--车辆车况信息end-->
					<#-- 车商报价-->
                    <hr>
					<#include "/vehiclemanage/careditbox/caredit_carquote.ftl">
					<#-- 车商报价 end-->
					<#-- 留言-->
					<hr>
					<#include "/vehiclemanage/careditbox/caredit_carmessage.ftl">
					<#-- 留言 end-->
				</div>
			</div><#--/row-->
		</div>
	</body>
</html>