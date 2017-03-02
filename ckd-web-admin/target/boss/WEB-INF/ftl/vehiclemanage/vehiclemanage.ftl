<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta charset="utf-8" />
		<title>车辆管理 - 车辆管理</title>
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
		<link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
		<link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
        <script  src="${basePath}/js/common/jquery/jquery.min.js"></script>
		<script  src="${basePath}/js/common/layer/layer.js"></script>
		<script  src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script  src="${basePath}/js/shiro.demo.js"></script>
		<script >
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
		<#--引入头部-->
		<@_top.top 4/>
		<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
			<div class="row">
				<#--引入左侧菜单-->
				<@_left.vhcl 1/>
				<div class="col-md-10">
					<h2>车辆管理</h2>
					<hr>
					<form method="post" action="" id="formId" class="form-inline">
						<div clss="well"><!-- 查询条件位置 -->
							<div class="form-group">
								<input type="text" class="form-control" style="width: 300px;" value="${id?default('')}" name="id" id="id" placeholder="车源ID">
								<input class="form-control" style="width: 150px;"  onClick="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\',{d:0});}'})" value="${stratDate?default('')}"  name="startDate" id="startDate" placeholder="车源生成时间">
								至
								<input class="form-control" style="width: 150px;" onClick="WdatePicker({minDate:'#F{$dp.$D(\'startDate\',{d:0});}'})" value="${endDate?default('')}"  name="endDate" id="endDate" placeholder="车源生成时间">
								<select  class="form-control" name="orderByColumn" id="orderByColumn">
									<option value="create_date" <#if orderByColumn == 'create_date' >selected</#if>>车源生成时间降序</option>
                                    <option value="quote_count" <#if orderByColumn == 'quote_count' >selected</#if>>车商报价次数降序</option>
                                    <option value="last_quote_date" <#if orderByColumn == 'last_quote_date' >selected</#if>>车商报价时间降序</option>
								</select>
							</div>
                            <div class="form-group">
                                <input class="form-control" style="width: 150px;" value="${quoteCountSmall?default('')}" name="quoteCountSmall" id="quoteCountSmall" placeholder="车商报价次数">
                                至
                                <input class="form-control" style="width: 150px;" value="${quoteCountBig?default('')}" name="quoteCountBig" id="quoteCountBig" placeholder="车商报价次数">

                                <input class="form-control" style="width: 150px;"  onClick="WdatePicker({maxDate:'#F{$dp.$D(\'lastQuoteDateEnd\',{d:0});}'})" value="${lastQuoteDateStart?default('')}"  name="lastQuoteDateStart" id="lastQuoteDateStart" placeholder="车商报价时间">
                                至
                                <input class="form-control" style="width: 150px;" onClick="WdatePicker({minDate:'#F{$dp.$D(\'lastQuoteDateStart\',{d:0});}'})" value="${lastQuoteDateEnd?default('')}"  name="lastQuoteDateEnd" id="lastQuoteDateEnd" placeholder="车商报价时间">

							</div>
							 <span class=""> <#--pull-right -->
								<button type="submit" class="btn btn-primary">查询</button>
								<button type="button" id="deleteAll" class="btn  btn-danger">重置</button>
							 </span>
				        </div>
					<hr>
					<table class="table table-bordered">
						<tr>
							<#--<th><input type="checkbox" id="checkAll"/></th>-->
							<th>车辆ID</th>
							<th>品牌</th>
							<th>厂商</th>
							<th>车系</th>
							<th>年款</th>
							<th>车型</th>
							<th>车源生成时间</th>
							<th>车商报价次数</th>
							<th>车商报价时间</th>
						</tr>
						<#if page?exists && page.list?size gt 0 >
							<#list page.list as it>
								<tr>
									<#--<td><input value="${it.id}" check='box' type="checkbox" /></td>-->
									<td><a href="${basePath}/vehiclemanage/carEdit.shtml?vhclId=${it.id}" target="_blank">${it.id}</a></td>
									<td>
										<#if it.brand?exists >
										    ${it.brand.brandName?default('-')}
										<#else >
                                            --
										</#if>
									</td>
									<td>
										<#if it.model?exists >
											${it.model.factory?default('-')}
										<#else >
                                            --
										</#if>
									</td>
									<td>
										<#if it.model?exists >
											<#if it.model.familyGroupName != "">
												${it.model.familyGroupName?default('-')}
											<#else>
												${it.model.family?default('-')}
											</#if>
										<#else >
                                            --
										</#if>
									</td>
									<td>${it.carYear?default('-')}</td>
									<td>
										<#if it.model?exists >
											<#if it.model.familyGroupName == "">
												${it.model.shortName?default('-')}
											<#else>
												${it.model.family?default('-')} ${it.model.shortName?default('-')}
											</#if>

										<#else >
											--
										</#if>
									</td>
									<td>
										${it.createDate?string("yyyy-MM-dd HH:mm:ss")}
									</td>
									<td>
										${it.quoteCount?default(0)}
									</td>
									<td>
										<#if it.lastQuoteDate?exists>
										    ${it.lastQuoteDate?string("yyyy-MM-dd HH:mm:ss")}
										<#else>
										   --
										</#if>

									</td>
								</tr>
							</#list>
						<#else>
							<tr>
								<td class="text-center danger" colspan="7">没有找到数据</td>
							</tr>
						</#if>
					</table>
					<#if page?exists><!--  分页位置 -->
						<div class="pagination pull-right">
							${page.pageHtml}
						</div>
					</#if>
					</form>
				</div>
			</div><#--/row-->
			<@shiro.hasPermission name="/permission/addPermission.shtml">
			<#--弹框-->
			<#--<div class="modal fade" id="addPermission" tabindex="-1" role="dialog" aria-labelledby="addPermissionLabel">-->
			  <#--<div class="modal-dialog" role="document">-->
			    <#--<div class="modal-content">-->
			      <#--<div class="modal-header">-->
			        <#--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>-->
			        <#--<h4 class="modal-title" id="addPermissionLabel">添加权限</h4>-->
			      <#--</div>-->
			      <#--<div class="modal-body">-->
			        <#--<form id="boxRoleForm">-->
			          <#--<div class="form-group">-->
			            <#--<label for="recipient-name" class="control-label">权限名称:</label>-->
			            <#--<input type="text" class="form-control" name="name" id="name" placeholder="请输入权限名称"/>-->
			          <#--</div>-->
			          <#--<div class="form-group">-->
			            <#--<label for="recipient-name" class="control-label">权限URL地址:</label>-->
			            <#--<input type="text" class="form-control" id="url" name="url"  placeholder="请输入权限URL地址">-->
			          <#--</div>-->
			        <#--</form>-->
			      <#--</div>-->
			      <#--<div class="modal-footer">-->
			        <#--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>-->
			        <#--<button type="button" onclick="addPermission();" class="btn btn-primary">Save</button>-->
			      <#--</div>-->
			    <#--</div>-->
			  <#--</div>-->
			<#--</div>-->
			<#--/弹框-->
			</@shiro.hasPermission>
		</div>
	</body>
</html>