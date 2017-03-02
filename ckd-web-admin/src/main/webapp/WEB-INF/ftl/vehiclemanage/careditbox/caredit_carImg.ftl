
<div class="container" >
	<div class="row">
		<div class="col-md-10">
			<table class="table table-bordered">
				<tr style="background: #2f87c1;color: #edfbfb;">
					<th colspan="6">车主图片</th>
				</tr>
                <tr>
                    <td>
                        <form id="imgform1" enctype="multipart/form-data" style="width: 140px;">
                            <div id="preview1" class="preview">
                                <img id="imghead1"  class="imghead" width=120 height=100 border=0 src='${basePath}/image/pic-tenant_upload.jpg'>
                            </div>
                            <input type="hidden" name="vhclId" id="imgform1_vhclId" value="">
                            <input type="hidden" name="bizType" value="1">
                            <input type="hidden" name="impPath" id="imgform1_impPath" value="">
                            <input type="file" name="photo" onchange="previewImage(this,1)" />
                        </form>
                    </td>
                    <td>
                        <form id="imgform2" enctype="multipart/form-data" style="width: 140px;">
                            <div id="preview2" class="preview">
                                <img id="imghead2"  class="imghead" width=120 height=100 border=0 src='${basePath}/image/pic-tenant_upload.jpg'>
                            </div>
                            <input type="hidden" name="vhclId" id="imgform2_vhclId" value="">
                            <input type="hidden" name="bizType" value="2">
                            <input type="hidden" name="impPath" id="imgform2_impPath" value="">
                            <input type="file" name="photo" onchange="previewImage(this,2)" />
                        </form>
                    </td>
                    <td>
                        <form id="imgform3" enctype="multipart/form-data" style="width: 140px;">
                            <div id="preview3" class="preview">
                                <img id="imghead3" class="imghead"  width=120 height=100 border=0 src='${basePath}/image/pic-tenant_upload.jpg'>
                            </div>
                            <input type="hidden" name="vhclId" id="imgform3_vhclId" value="">
                            <input type="hidden" name="bizType" value="3">
                            <input type="hidden" name="impPath" id="imgform3_impPath" value="">
                            <input type="file" name="photo" onchange="previewImage(this,3)" />
                        </form>
                    </td>
                    <td>
                        <form id="imgform4" enctype="multipart/form-data" style="width: 140px;">
                            <div id="preview4" class="preview">
                                <img id="imghead4" class="imghead"  width=120 height=100 border=0 src='${basePath}/image/pic-tenant_upload.jpg'>
                            </div>
                            <input type="hidden" name="vhclId" id="imgform4_vhclId" value="">
                            <input type="hidden" name="bizType" value="4">
                            <input type="hidden" name="impPath" id="imgform4_impPath" value="">
                            <input type="file" name="photo" onchange="previewImage(this,4)" />
                        </form>
                    </td>
                    <td>
                        <form id="imgform5" enctype="multipart/form-data" style="width: 140px;">
                            <div id="preview5" class="preview">
                                <img id="imghead5" class="imghead"  width=120 height=100 border=0 src='${basePath}/image/pic-tenant_upload.jpg'>
                            </div>
                            <input type="hidden" name="vhclId" id="imgform5_vhclId" value="">
                            <input type="hidden" name="bizType" value="5">
                            <input type="hidden" name="impPath" id="imgform5_impPath" value="">
                            <input type="file" name="photo" onchange="previewImage(this,5)" />
                        </form>
                    </td>
                    <td>
                        <form id="imgform6" enctype="multipart/form-data" style="width: 140px;">
                            <div id="preview6" class="preview">
                                <img id="imghead6" class="imghead" width=120 height=100 border=0 src='${basePath}/image/pic-tenant_upload.jpg'>
                            </div>
                            <input type="hidden" name="vhclId" id="imgform6_vhclId" value="">
                            <input type="hidden" name="bizType" value="6">
                            <input type="hidden" name="impPath" id="imgform6_impPath" value="">
                            <input type="file" name="photo" onchange="previewImage(this,6)"  />
                        </form>
                    </td>
                </tr>
			</table>
        </div>
	</div>
</div>
<style type="text/css">
    .preview{width:120px;height:100px;border:1px solid #000;overflow:hidden;}
    .imghead {filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);}
</style>

<script >
    $(function(){
        findCarImage();
    });

    //        //图片上传预览    IE是用了滤镜。
    function previewImage(file,index){
        var MAXWIDTH  = 120;
        var MAXHEIGHT = 100;
        var div = document.getElementById('preview'+index);
        if (file.files && file.files[0])
        {
            div.innerHTML ='<img id=imghead'+index+'>';
            var img = document.getElementById('imghead'+index);
            img.onload = function(){
                var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
                img.width  =  rect.width;
                img.height =  rect.height;
//                 img.style.marginLeft = rect.left+'px';
                img.style.marginTop = rect.top+'px';
            }
            var reader = new FileReader();
            reader.onload = function(evt){img.src = evt.target.result;}
            reader.readAsDataURL(file.files[0]);
        }
        else //兼容IE
        {
            var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
            file.select();
            var src = document.selection.createRange().text;
            div.innerHTML = '<img id=imghead'+index+'>';
            var img = document.getElementById('imghead'+index);
            img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
            div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
        }
        formSubmit(index);
    }
    function clacImgZoomParam( maxWidth, maxHeight, width, height ){
        var param = {top:0, left:0, width:width, height:height};
        if( width>maxWidth || height>maxHeight )
        {
            rateWidth = width / maxWidth;
            rateHeight = height / maxHeight;

            if( rateWidth > rateHeight )
            {
                param.width =  maxWidth;
                param.height = Math.round(height / rateWidth);
            }else
            {
                param.width = Math.round(width / rateHeight);
                param.height = maxHeight;
            }
        }

        param.left = Math.round((maxWidth - param.width) / 2);
        param.top = Math.round((maxHeight - param.height) / 2);
        return param;
    }
    /**
     * 异步提交图片
     */
    function formSubmit(index){
        $("#imgform"+index+"_vhclId").val($("#vhclId").val())
        $("#imgform"+index).ajaxSubmit({
            type: "POST",//提交类型
            url: "${basePath}/vehiclemanage/imgUpload.shtml",//请求地址
            dataType: "text",//返回结果格式
            async: true,
            success: function (data) {//请求成功后的函数
                $("#imgform"+index+"_impPath").val(data);
            }
        });
    }



    function findCarImage() {//查询车主信息
        $.ajax({
            type: "GET",
            url: "${basePath}/vehiclemanage/findCarImage.shtml",
            data: "vhclId="+$("#vhclId").val(),
            dataType: "json",
            success: function (code) {
               if(code.result == 1){
                   var imgs = code.data;
                   $.each(imgs,function(i,item){//http://localhost:8080
                       if(item.img != "" && item.img != null) {
                           $("#preview" + item.type).html('<img id="imghead' + item.type + '" class="imghead" src="${domainImg}' + item.img + '" width="120" height="100" border="0">');
                       }
                   })
                }else{
                    alert("车辆图片信息查询失败-_-!");
                }
            }
        });
    }

</script>