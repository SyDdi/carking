
/**留言删除**/
 function messageMove(e) {
    // 设定每一行的宽度=屏幕宽度+按钮宽度
    $(".mycar-scroll-wrapper").width($(".mycar-wrapper").width() + $(".mycar-btn-delete").width());
    // 设定常规信息区域宽度=屏幕宽度
    $(".mycar-normal-wrapper").width($(".mycar-wrapper").width());
	
    //$(".mycar-btn-delete").height( $(this).prev($(".mycar-normal-wrapper").height()) );
	
	//$(".mycar-btn-delete").css("height", $(".mycar-btn-delete").prev("div").css("height"));
	
	$(".mycar-btn-delete").each(function (i,o){
       var $this = $(o), 
        height = $this.prev("div").height();
		//height = $($this.prev("div")).actual('height');
        $(this).css("height",height);
     });

        //$('#someElement').actual('width');
	
		// 获取所有行，对每一行设置监听
	    var lines = $(".mycar-normal-wrapper");
	    var len = lines.length; 
	    var lastX, lastXForMobile;
	
	    // 用于记录被按下的对象
	    var pressedObj;  // 当前左滑的对象
	    var lastLeftObj; // 上一个左滑的对象
	
	    // 用于记录按下的点
	    var start;
	    
	    // 网页在移动端运行时的监听
	    for (var i = 0; i < len; ++i) {
	        lines[i].addEventListener('touchstart', function(e){
	        	
	            lastXForMobile = e.changedTouches[0].pageX;
	            pressedObj = this; // 记录被按下的对象 
	
	            // 记录开始按下时的点
	            var touches = event.touches[0];
	            start = { 
	                x: touches.pageX, // 横坐标
	                y: touches.pageY  // 纵坐标
	            };
	            
	        });
	        
	        
	        lines[i].addEventListener('touchmove',function(e){
	    		// 计算划动过程中x和y的变化量
	    		var touches = event.touches[0];
	    		delta = {
	    				x: touches.pageX - start.x,
	    				y: touches.pageY - start.y
	    		};
	    		if (Math.abs(delta.x) > Math.abs(delta.y)) {
	    			event.preventDefault();
	    		}
	    	});
	        
	
	        lines[i].addEventListener('touchend', function(e){
	            if (lastLeftObj && pressedObj != lastLeftObj) { // 点击除当前左滑对象之外的任意其他位置
	                $(lastLeftObj).animate({marginLeft:"0"}, 100); // 右滑
	                lastLeftObj = null; // 清空上一个左滑的对象
	            }
	            var diffX = e.changedTouches[0].pageX - lastXForMobile;
	            if (diffX < -60) {
	            	//判断是不是车主
	            	var id = $(this).parent().data("id");
	            	var userId = $("#userId").val();
	            	var userJson = JSON.parse(sessionStorage.getItem('CKD_WX_USER'));
	            	var vehicleStatus = $("#vehicleStatus").val();
	            	var isOwner = $("#isOwner").val();
	            	var ok = "OK";
	            	if(userJson != null && userJson.id !=""){
	            		userId = userJson.id;
	            		$("#userId").val(userId);
	            	}
	            	if(vehicleStatus != 1) {
	            		return;
	            	}
	            	if ( isOwner == null || isOwner == ""){//车主可以删除留言
	            		if (id != userId) {//非车主只能删除自己的
	            			return;
	            		}
	            	}
	            	//判断是不是车主
	                $(pressedObj).animate({marginLeft:"-80px"}, 100); // 左滑
	                lastLeftObj && lastLeftObj != pressedObj && 
	                    $(lastLeftObj).animate({marginLeft:"0"}, 100); // 已经左滑状态的按钮右滑
	                lastLeftObj = pressedObj; // 记录上一个左滑的对象
	            } else if (diffX > 60) {
	              if (pressedObj == lastLeftObj) {
	                $(pressedObj).animate({marginLeft:"0"}, 100); // 右滑
	                lastLeftObj = null; // 清空上一个左滑的对象
	              }
	            }
	        });
	    }
	
	    // 网页在PC浏览器中运行时的监听
	    for (var i = 0; i < len; ++i) {
	        $(lines[i]).bind('mousedown', function(e){
	            lastX = e.clientX;
	            pressedObj = this; // 记录被按下的对象
	        });
	
	        $(lines[i]).bind('mouseup', function(e){
	            if (lastLeftObj && pressedObj != lastLeftObj) { // 点击除当前左滑对象之外的任意其他位置
	                $(lastLeftObj).animate({marginLeft:"0"}, 100); // 右滑
	                lastLeftObj = null; // 清空上一个左滑的对象
	            }
	            var diffX = e.clientX - lastX;
	            if (diffX < -60) {
	                $(pressedObj).animate({marginLeft:"-80px"}, 100); // 左滑
	                lastLeftObj && lastLeftObj != pressedObj && 
	                    $(lastLeftObj).animate({marginLeft:"0"}, 100); // 已经左滑状态的按钮右滑
	                lastLeftObj = pressedObj; // 记录上一个左滑的对象
	                  
	            } else if (diffX > 60) {
	              if (pressedObj == lastLeftObj) {
	                $(pressedObj).animate({marginLeft:"0"}, 100); // 右滑
	                lastLeftObj = null; // 清空上一个左滑的对象
	              }
	            }
	        });
	    }
	
    
    delMessage();
};


function delMessage(){
		
		$(".close").click(function(){
		 //num = $(this).parent().parent().parent(".phone").index();
		 //alert($(this).parent().parent().parent(".phone").index());
			//qikoo.dialog.confirm('确认关闭？',function(){
			//	},function(){
			//});
			$("#whoxPhone").hide();
		});
		
		$(".mycar-btn-delete").click(function(){
		 num1 = $(this).parent().parent("div").index();
		 var msgId = $(this).find('.remove').attr("value");
		 var _this = $(this);
		 //alert($(this).parent().parent("div").index());
			qikoo.dialog.confirm('确认删除？',function(){
				/*1209*/
					$.ajax({
						type:"GET",
						url:"/transfer/delMsg",
						data:"msgId="+msgId,
						dataType:'json',
						success:function(data){
							alertMsg(data.data);
							if(data.message == 1){
								$(this).remove();
							}
						}
					});
				/*1209*/
				
				},function(){/*取消*/
				/*取消后左滑还原*/
					//console.info(123)
					//console.info(_this.parent().siblings())
				_this.siblings().css("margin-left",0)
					
			});
			
		});
		
};
	
	
	function del(){	
			$(".mycar-wrapper").each(function(index, element) {
				
					$(".console-btn-confirm").click(function(){
					//alert("HTML: " + $(".dialog-content p").html());
					//alert(num1);
					 if($(".dialog-content p").html() == "确认删除？"){
					  if(index == num1){
                	   var del = $(this).parents(".mod-dialog").siblings("#car_written").children(".car_written").find('.mycar-wrapper').eq(index);
					   del.remove();

					  }
					 }
					})					
            });
			
			
		  $(".phone").each(function(index, element) {
			
			  $(".console-btn-confirm").click(function(){
					if($(".dialog-content p").html() == "确认关闭？"){
				     if(index == 0){
					  var del = $(this).parents("body").find(".phone").eq(index);
					  del.remove();
					 }
			        }					
			   })
				
            });
			

		}
		

/**留言删除**/
function alertMsg(msg){
	var _height = $(window).height();
	$("#collect_zs").css('top',(_height *0.4)+$(window).scrollTop());
	$("#collect_zs").text(msg)
	$("#collect_zs").delay(500).show().delay(1000).fadeOut(1000);
}