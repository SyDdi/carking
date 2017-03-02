
<div class="container" >
	<div class="row">
		<div class="col-md-10">
			<form id="carinfo_form">
				<table class="table table-bordered">
					<tr  >
						<th colspan="7" style="background: #2f87c1;color: #edfbfb;">车辆基本信息</th>
						<th><div id="carinfo_editDiv" style="display: none;">
                            <a onclick="carinfo_save('carinfo_form');">更新</a>&nbsp;&nbsp;&nbsp;&nbsp;
                            <a onclick="carInfoCancel('carinfo_form');">取消</a>
                        </div><a id="carinfo_edit" onclick="carInfo_edit(this,'carinfo_form');">编辑</a></th>
					</tr>
					<tr>
						<th>品牌</th>
						<th>厂商</th>
						<th>车系</th>
						<th>年款</th>
						<th colspan="2">车型</th>
						<th>车主报价（万元）</th>
						<th>状态</th>
					</tr>
                    <tbody id="carinfo_table_tr1">
						<tr>
							<td><select id="brand" name="brandId">
								<option value="">请选择</option>
							</select> </td>
							<td><select id="seriesFactory" name="factoryId">
                                <option value="">请选择</option>
                            </select> </td>
							<td><select id="series" name="series">
                                <option value="">请选择</option>
                            </select></td>
							<td><select id="modelYear" name="carYear">
                                <option value="">请选择</option>
                            </select></td>
							<td colspan="2"><select id="model" name="strModel">
                                <option value="">请选择</option>
                            </select></td>
							<td><input type="text" id="expectPrice" name="carPrice" onkeyup="clearNoNum(this);"></td>
							<td><select id="carinfo_updStatus" name="status">'+
                                '<option value="1">展示</option>'+
                                '<option value="0">下架</option>'+
                                '</select></td>
						</tr>
                    </tbody>
					<tr>
						<td>所在地-省</td>
						<td>所在地-市</td>
						<td>车牌号</td>
						<td>牌照属地-省</td>
						<td>牌照属地-市</td>
						<td>上牌日期</td>
						<td>里程（万公里）</td>
						<td>车身颜色</td>
					</tr>
                    <tbody id="carinfo_table_tr2">
						<tr>
							<td>
								<select id="siteProvince" name="provinceId" style="width: 80px">
									<option value="">请选择</option>
								</select>
							</td>
							<td>
								<select id="siteZone" name="zoneId" style="width: 80px">
                            	    <option value="">请选择</option>
                            	</select>
							</td>
							<td><input type="text" id="numberPlate" name="numberPlate" /></td>
                            <td>
                                <select id="regProvince" name="regProvinceId" style="width: 80px" >
                                    <option value="">请选择</option>
                                </select>
                            </td>
                            <td>
                                <select id="regZone" name="regZoneId" style="width: 80px" >
                                    <option value="">请选择</option>
                                </select>
                            </td>
							<td><input style="width: 100px;"  onClick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})" name="regDate" id="regDate" placeholder="上牌日期"></td><!-- class="form-control"-->
							<td><input type="text" id="miles" name="miles" onkeyup="clearNoNum(this);"/></td>
							<td>
                                <select id="outerColor" name="outerColor" style="width: 80px" >
                                    <option value="">请选择</option>
                                </select>
							</td>
						</tr>
                    <tr>
                        <td colspan="8">车辆信息说明</td>
                    </tr>
                        <tr>
                            <td colspan="8"><textarea rows="3" cols="50" id="info" name="info" ></textarea></td>
                        </tr>
                    </tbody>
				</table>
            </form>
		</div>
	</div>
</div>


<script >

    $(function(){
        carInfoCancel('carinfo_form');
        initColor();
        initCarInfo();
    });



    function initCarInfo() {//查询车主信息
        $.ajax({
            type: "GET",
            url: "${basePath}/vehiclemanage/findVehicle.shtml",
            data: "vhclId="+$("#vhclId").val(),
            dataType: "json",
            success: function (code) {
                if(code.result == 1){
                    var vhcl = code.data;
                    $("#expectPrice").val((vhcl.expectPrice/10000.00));
                    $("#numberPlate").val(vhcl.numberPlate);
                    $("#regDate").val(vhcl.regDate);
                    $("#miles").val(vhcl.miles);
                    $("#outerColor").val(vhcl.outerColor);
                    $("#info").val(vhcl.info);
                    $("#carinfo_updStatus").val(vhcl.status);
                    // 初始化品牌车系车
                    initBrand("brand","series","model",vhcl.brandId,vhcl.factoryId,vhcl.familyId,vhcl.carYear,vhcl.modelId);
                    //初始化所在地省市
                    initZoneData("siteProvince","siteZone",vhcl.provinceId,vhcl.zoneId);
                    //初始化车牌所在地省市
                    initZoneData("regProvince","regZone",vhcl.regProvinceId,vhcl.regZoneId);
                }else{
                    alert("车辆分享信息查询失败-_-!");
                }
            }
        });
    }
    /**
     * 初始化颜色
     */
    function initColor(){
        $.ajax({
            type: "GET",
            url: "${basePath}/vehiclemanage/findColor.shtml",
            dataType: 'json',
            async: false,
            success: function (result) {
                    var color = result.data;
                var colorOption = " <option value=\"\">请选择</option>";
                for (var i in color) {
                    if(!isFunction(color[i])) {
                        colorOption += "<option value=\"" + color[i] + "\">" + color[i] + "</option>";
                    }
                }
                $("#outerColor").html(colorOption);
            }
        });
    }
    function  success_jsonpCallback(result){
        console.info(result);
    }

    function carInfo_edit(opt,fromId){
        $("#carinfo_editDiv").show();
        $(opt).hide();
        $(':input','#'+fromId).not(':button, :submit, :reset, :hidden').removeAttr('disabled');
    }
    function carInfoCancel(formId){
        $("#carinfo_editDiv").hide();
        $("#carinfo_edit").show();
        $(':input','#'+formId).not(':button, :submit, :reset, :hidden').attr('disabled','disabled');
    }

    function carinfo_save(formId){
        var param = $('#'+formId).serialize();
        param +="&id="+ $('#vhclId').val();
        if(!isDecimal($("#expectPrice").val(),4,2)){
            alert("车主报价输入不正确！");
            return;
        }
        $.ajax({
            type: "GET",
            url: "${basePath}/vehiclemanage/updCarInfo.shtml",
            data: param,
            dataType: "json",
            success: function (code) {
                alert(code.data);
                if(code.result == 1){
                    carInfoCancel(formId);
                }
            }
        });
    }







</script>