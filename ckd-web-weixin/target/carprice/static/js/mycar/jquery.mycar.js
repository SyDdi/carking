function showtabs2(m, n, count) {
    for (var i = 1; i <= count; i++) {
        if (i == n) {
            getObject('menutab_' + m + '_' + i).style.display = '';
            getObject('menu_' + m + '_' + i).className = 'menub2_1';


            $("div[name=line-btn-collect]").each(function (i, o) {
                var $this = $(o),
                    height = $this.prev("div").height();
                //height = $($this.prev("div")).actual('height');
                $(this).css("height", height);
            });


        }
        else {
            getObject('menutab_' + m + '_' + i).style.display = 'none';
            getObject('menu_' + m + '_' + i).className = 'menub2_2';
        }
    }
}

function getObject(objectId) {
    if (document.getElementById && document.getElementById(objectId)) {
        return document.getElementById(objectId);
    } else if (document.all && document.all(objectId)) {
        return document.all(objectId);
    } else if (document.layers && document.layers[objectId]) {
        return document.layers[objectId];
    } else {
        return false;
    }
}

//$(document).ready(function (e) {
function ready() {
    // 设定每一行的宽度=屏幕宽度+按钮宽度
    $(".line-scroll-wrapper").width($(document.body).width() + $(".line-btn-delete").width());
    // 设定常规信息区域宽度=屏幕宽度
    $(".line-normal-wrapper").width($(document.body).width());

    //$(".line-btn-delete").height( $(this).prev($(".line-normal-wrapper").height()) );

    //$(".line-btn-delete").css("height", $(".line-btn-delete").prev("div").css("height"));

    $(".line-btn-delete").each(function (i, o) {
        var $this = $(o),
            height = $this.prev("div").height();
        //height = $($this.prev("div")).actual('height');
        $(this).css("height", height);
    });


    //$('#someElement').actual('width');

    // 获取所有行，对每一行设置监听
    var lines = $(".line-normal-wrapper");
    var len = lines.length;
    var lastX, lastXForMobile;

    // 用于记录被按下的对象
    var pressedObj;  // 当前左滑的对象
    var lastLeftObj; // 上一个左滑的对象

    // 用于记录按下的点
    var start;

    // 网页在移动端运行时的监听
    for (var i = 0; i < len; ++i) {
        lines[i].addEventListener('touchstart', function (e) {
            lastXForMobile = e.changedTouches[0].pageX;
            pressedObj = this; // 记录被按下的对象 

            // 记录开始按下时的点
            var touches = event.touches[0];
            start = {
                x: touches.pageX, // 横坐标
                y: touches.pageY  // 纵坐标
            };
        });

        lines[i].addEventListener('touchmove', function (e) {
            // 计算划动过程中x和y的变化量
            var touches = event.touches[0];
            delta = {
                x: touches.pageX - start.x,
                y: touches.pageY - start.y
            };

            // 横向位移大于纵向位移，阻止纵向滚动
            if (Math.abs(delta.x) > Math.abs(delta.y)) {
                event.preventDefault();
            }
        });

        lines[i].addEventListener('touchend', function (e) {
            if (lastLeftObj && pressedObj != lastLeftObj) { // 点击除当前左滑对象之外的任意其他位置
                $(lastLeftObj).animate({marginLeft: "0"}, 100); // 右滑
                lastLeftObj = null; // 清空上一个左滑的对象
            }
            var diffX = e.changedTouches[0].pageX - lastXForMobile;
            if (diffX < -60) {
                $(pressedObj).animate({marginLeft: "-80px"}, 100); // 左滑
                lastLeftObj && lastLeftObj != pressedObj &&
                $(lastLeftObj).animate({marginLeft: "0"}, 100); // 已经左滑状态的按钮右滑
                lastLeftObj = pressedObj; // 记录上一个左滑的对象
            } else if (diffX > 60) {
                if (pressedObj == lastLeftObj) {
                    $(pressedObj).animate({marginLeft: "0"}, 100); // 右滑
                    lastLeftObj = null; // 清空上一个左滑的对象
                }
            }
        });
    }

    // 网页在PC浏览器中运行时的监听
    for (var i = 0; i < len; ++i) {
        $(lines[i]).bind('mousedown', function (e) {
            lastX = e.clientX;
            pressedObj = this; // 记录被按下的对象
        });

        $(lines[i]).bind('mouseup', function (e) {
            if (lastLeftObj && pressedObj != lastLeftObj) { // 点击除当前左滑对象之外的任意其他位置
                $(lastLeftObj).animate({marginLeft: "0"}, 100); // 右滑
                lastLeftObj = null; // 清空上一个左滑的对象
            }
            var diffX = e.clientX - lastX;
            if (diffX < -60) {
                $(pressedObj).animate({marginLeft: "-80px"}, 100); // 左滑
                lastLeftObj && lastLeftObj != pressedObj &&
                $(lastLeftObj).animate({marginLeft: "0"}, 100); // 已经左滑状态的按钮右滑
                lastLeftObj = pressedObj; // 记录上一个左滑的对象
            } else if (diffX > 60) {
                if (pressedObj == lastLeftObj) {
                    $(pressedObj).animate({marginLeft: "0"}, 100); // 右滑
                    lastLeftObj = null; // 清空上一个左滑的对象
                }
            }
        });
    }
    confirmDel();
}
//});
/*
 $(document).ready(function(){

 var button_height = $(".line-normal-info-wrapper").height();
 $(".line-btn-delete").css("height", button_height);

 });
 */
//$(document).ready(function (e) {
function confirmDel () {
    $("div[name=line-btn-delete]").click(function () {
        var _this = $(this);
        var vhclId = $(this).attr("value");
        num1 = $(this).parent().parent("div").index();
        //alertMsg($(this).parent().parent("div").index());
        qikoo.dialog.confirm('删除后，买家将无法看到此车源！', function () {//确定
            var userId = $("#userId").val();
            $.ajax({
                url: "my/updCarStatus",
                type: "GET",
                dataType: "json",
                data: "userId=" + userId +"&vhclId="+vhclId,
                success: function (data) {
                    alertMsg(data.data);
                    //if(data.result == 1){
                        //console.info("success")
                        findMycar();
                    //}
                }
            });
        }, function () { //取消
        	_this.siblings().css("margin-left",0)
        });
    });
    $("div[name=line-btn-collect]").click(function () {
        var _this = $(this);
    	var collId = $(this).attr("value");
        num2 = $(this).parent().parent("div").index();
        //alertMsg($(this).parent().parent("div").index());
        qikoo.dialog.confirm('取消收藏后，您将无法收到留言提醒！', function () {//确定
            $.ajax({
        	    url: "my/updCollStatus",
        	    type: "GET",
        	    dataType: "json",
        	    data: "collId=" + collId,
        	    success: function (data) {
        	        //console.info(data);
                    alertMsg(data.data);
        	        //if(data.result==1){
                        findFavorite();
        	        //}
        	    }
            })
        }, function () {//取消
            _this.prev('.line-normal-wrapper').animate({marginLeft: "0"}, 100);
        });
    });
}
//})

function del() {
    $(".line-wrapper").each(function (index, element) {
        $(".console-btn-confirm").click(function () {
            if ($(".dialog-content p").html() == "确认删除？") {
                if (index == num1) {
                    var del = $(this).parents(".mod-dialog").siblings(".my_car_list").children().find('.line-wrapper').eq(index);
                    del.remove();
                }
            }else if ($(".dialog-content p").html() == "确认取消收藏？") {
                if (index == num2) {
                    var del = $(this).parents(".mod-dialog").siblings(".my_car_collect").children().find('.line-wrapper').eq(index);
                    del.remove();
                }
            }
        })
    })

}

function alertMsg(msg){
    var _height = $(window).height();
    $("#collect_zs").css('top',(_height *0.4)+$(window).scrollTop());
    $("#collect_zs").text(msg)
    $("#collect_zs").delay(500).show().delay(1000).fadeOut(1000);

}


