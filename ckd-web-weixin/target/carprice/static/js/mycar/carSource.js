

/*加载用户留言信息*/

var liuyan = '';

$(function () {
	load();
	
	/*提交留言信息*/
	var bind_name="change";//定义所要绑定的事件名称
    if(navigator.userAgent.indexOf("MSIE")!=-1) bind_name="propertychange";//判断是否为IE内核 IE内核的事件名称要改为propertychange
    	
    	$("#sendId").bind("click",function(){
	  		if($('#message').val() != '' && $('#message').val() != "请输入文字留言"){
				var car_written = $('.car_written');
	  			var thisLiu = $('#message').val();
				var vhclId = $("#vhclId").val();
				var userInfo = weixin.getUserInfo();
				
				var parent = $("#message").attr("parentId");
				
				$("#userId").val(userInfo.id);
				//$("#userId").val(10);
				//console.info(userInfo.id);
				//  提交留言到 数据库
				$.ajax({
					type: "POST",//提交类型
					url: "/transfer/savaMessage",//请求地址
					data: {content: thisLiu,vehicleId:vhclId,userId:userInfo.id,parentId:parent},
					dataType: "json",//返回结果格式
					success: function (data) {//请求成功后imageContainer的函数
						if(data.message == 2){//没有关注不能留言
							$(".concern").show();
						}else if(data.message==1){
							var result=(data.message == 1 ?'留言成功':'留言失败');
							load();
							favorite();
						}
					}
				});
				//  提交留言到 数据库 end
				$('#message').val("");
			}	
		});
    	
	
	

});


function load(paGe){
	var vhclId = $("#vhclId").val();
	$.ajax({
		type:"get",
		url:"/transfer/messages?vhclId="+vhclId/*+paGe*/,
		//async:true,
		dataType:'json',
		success:function(data){
			var liuyan = '';
			for(var i in data){
				for(var j in data[i]){
					liuyan += '<div class="mycar-wrapper" id="touch'+ j +'">';
					liuyan += '<div class="mycar-scroll-wrapper" data-id="'+data[i][j].userId+'">';
					liuyan += '<div class="mycar-normal-wrapper">';
					liuyan += '<div class="mycar-normal-left-wrapper">';
					liuyan += '<div class="mycar-normal-avatar-wrapper"><img src="'+( data[i][j].user != null ?data[i][j].user.headImgUrl :"" )+'"></div>';
					liuyan += '<div class="mycar-normal-info-wrapper">';
					liuyan += '<dl><dt><a href="javascript:;">'+ data[i][j].userName +(data[i][j].isOnwer == 1 ?"<em>卖家</em>":"" )+'</a><span>'+ data[i][j].createTime +'</span></dt>';
					liuyan += '<dd>'+ data[i][j].content +'</dd></dl>';
					liuyan += '</div>';
					liuyan += '</div>';
					liuyan += '</div>';
					liuyan += '<div class="mycar-btn-delete" ><button class="remove" value='+ data[i][j].id +'>删除</button></div>';
					liuyan += '</div>';
					liuyan += '</div>';
				}
			}
			$('.car_written').html('');
			$('.car_written').html(liuyan);
			messageMove();/*1209*/
			
			$(".mycar-normal-wrapper").on('click',function(){
				var _this=$(this);
				
			var userInfo = weixin.getUserInfo();
				var currentUserId=$("#userId").val();
				//if(_this.find('.mycar-scroll-wrapper').attr("data-id")!=currentUserId){
				//}
				
				var uName=_this.find("a").text().replace("卖家","");
				//console.info("对方留言事件:"+uName);
				var mId=_this.find('button').val();
				//console.info("留言id"+mId);
				$("#message").val("@"+uName+":");
				$("#message").attr("parentId",mId);
				$("#message").focus();
				//console.info("parentId:"+$("#message").attr("parentId"));
			})
		}
	});
}

