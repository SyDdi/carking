//格式化金额
//优化负数格式化问题
function fmoney(s, n) {
    n = n > 0 && n <= 20 ? n : 2;
    f = s < 0 ? "-" : ""; //判断是否为负数
    s = parseFloat((Math.abs(s) + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";//取绝对值处理, 更改这里n数也可确定要保留的小数位
    var l = s.split(".")[0].split("").reverse(),
        r = s.split(".")[1];
    t = "";
    for (i = 0; i < l.length; i++) {
        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
    }
    return f + t.split("").reverse().join("") + "." + r.substring(0, 2);//保留2位小数  如果要改动 把substring 最后一位数改动就可
}


function loadData(area, curPage,q) {
    //console.log("load data ->area：" + area + ",page:" + curPage + ",q:" + q );
    var el, section;
    el = $('#scroller');
    //获取json数据
    $.ajax({
        url: "list?area=" + area + "&page=" + curPage +"&q="+encodeURI(q),
        type: "GET",
        dataType: 'json',
        success: function (result) {
            //设置totalPage
            $("#totalPage").val(result.totalPage);
            //如果是第一页，需要对页面的初始数据进行一些清理
            if ( curPage == 1) el.empty();
            //如果当前条件数据为空，提示稍后再试
            if (result.data.length <= 0) {
                el.html('<section><div class="intro_xcj_zwsj"><ul><li><img src="/static/images/intro_gth.png" align="absmiddle">暂无数据</li></ul></div></section>');
            }
            for (var i in result.data) {
                section = $("<section></section>");
                var sHtml = "";
                var color = "";
                var arrow = "";
                sHtml += '<div class="box xcj_zt_xx">' + result.data[i].area + '<span>|</span>' + result.data[i].factory + '<span>|</span>' + result.data[i].carLine + '<span>|</span>' + result.data[i].year + ' ' + result.data[i].carModel + '</div>';
                sHtml += '<div class="box xcj_zt_jg">';
                sHtml += '        <dl>';
                sHtml += '        <dt>昨日均价<br><span>' + fmoney(result.data[i].todayPrice, 2) + '万元</span></dt>';
                if (result.data[i].weekDiffPrice > 0) {
                    color = "ora";
                    arrow = '<img src="/static/images/hqj_xcj_pic03.png" align="absmiddle">';
                } else if (result.data[i].weekDiffPrice < 0) {
                    color = "gre";
                    arrow = '<img src="/static/images/hqj_xcj_pic02.png" align="absmiddle">';
                }
                sHtml += '<dd>7天前:' + fmoney(result.data[i].weekAgoPrice, 2) + '万元<br>变化率:<span class="' + color + '">';
                sHtml += fmoney(result.data[i].weekDiffPrice * 100, 2) + '%';
                sHtml += arrow;
                sHtml += '        </span></dd>';
                color = "";
                arrow = "";
                if (result.data[i].monthDiffPrice > 0) {
                    color = "ora";
                    arrow = '<img src="/static/images/hqj_xcj_pic03.png" align="absmiddle">';
                } else if (result.data[i].monthDiffPrice < 0) {
                    color = "gre";
                    arrow = '<img src="/static/images/hqj_xcj_pic02.png" align="absmiddle">';
                }
                sHtml += '<dd>30天前:' + fmoney(result.data[i].monthAgoPrice, 2) + '万元<br>变化率:<span class="' + color + '">';
                sHtml += fmoney(result.data[i].monthDiffPrice * 100, 2) + '%';
                sHtml += arrow;
                sHtml += '        </span></dd>';
                sHtml += '</dl>';
                sHtml += '</div>';
                section.html(sHtml);
                el.append(section);
            }
        },
        error: function (er) {
            BackErr(er);
        }
    });
}

//模拟ajax
function ajax(parms) {
    var a = [],
        i;
    var page = parms.url.replace("getData.do?page=", "");
    for (i = 1; i <= 20; i++) {
        a.push({
            "index": i + (page - 1) * 20,
            "text": "普通分页加载模式"
        });
    }
    setTimeout(function() {
        parms.success(a);
    }, 800);
}





var zonestr = '[{name:"北京",id:"1"},{name:"上海",id:"73"},{name:"天津",id:"2"},{name:"重庆",id:"257"},{name:"哈尔滨",id:"60"},{name:"长春",id:"51"},{name:"沈阳",id:"37"},{name:"呼和浩特",id:"25"},{name:"石家庄",id:"3"},{name:"乌鲁木齐",id:"348"},{name:"兰州",id:"321"},{name:"西宁",id:"335"},{name:"西安",id:"311"},{name:"银川",id:"343"},{name:"郑州",id:"153"},{name:"济南",id:"136"},{name:"太原",id:"14"},{name:"合肥",id:"99"},{name:"长沙",id:"187"},{name:"武汉",id:"170"},{name:"南京",id:"74"},{name:"成都",id:"258"},{name:"贵阳",id:"279"},{name:"昆明",id:"288"},{name:"南宁",id:"222"},{name:"拉萨",id:"304"},{name:"杭州",id:"88"},{name:"南昌",id:"125"},{name:"广州",id:"201"},{name:"福州",id:"116"},{name:"海口",id:"236"},{name:"襄樊",id:"174"},{name:"淄博",id:"138"},{name:"无锡",id:"75"},{name:"苏州",id:"78"},{name:"南通",id:"79"},{name:"常州",id:"77"},{name:"保定",id:"8"},{name:"平顶山",id:"156"},{name:"大同",id:"15"},{name:"深圳",id:"203"},{name:"温州",id:"90"},{name:"宁波",id:"89"},{name:"青岛",id:"137"}]';
var zone = eval(zonestr);
function localCity(data) {
    var geolocation = new BMap.Geolocation();
    var gc = new BMap.Geocoder();
    geolocation.getCurrentPosition(function (r) {   //定位结果对象会传递给r变量
            if (this.getStatus() == BMAP_STATUS_SUCCESS) {  //通过Geolocation类的getStatus()可以判断是否成功定位。
                var pt = r.point;
                gc.getLocation(pt, function (rs) {
                    var addComp = rs.addressComponents;
                    console.log(addComp.province + "|" + addComp.city + "|" + addComp.district + "|" + addComp.street + "|" + addComp.streetNumber);
                    var subData = null;
                    var flag = false;
                    for (var i = 0; i < data.length; i++) {
                        if (data[i].name.replace("市", "") == addComp.city.replace("市", "")) {
                            var str = data[i].name;
                            $("#areaId").val(data[i].id);
                            $("#area").text(str);
                            flag = true;
                            break;
                        }
                        if (flag) {
                            break;
                        }
                    }
                    if (!flag) {//默认为上海
                        $("#areaId").val(73);
                        $("#area").text('上海');
                    }
                    //$("#areaId").val(12345);
                    loadData($('#areaId').val(), 1,$('#q').val());
                });
            }
            else {
                switch (this.getStatus()) {
                    case 2:
                        console.log('位置结果未知 获取位置失败.');
                        break;
                    case 3:
                        console.log('导航结果未知 获取位置失败..');
                        break;
                    case 4:
                        console.log('非法密钥 获取位置失败.');
                        break;
                    case 5:
                        console.log('对不起,非法请求位置  获取位置失败.');
                        break;
                    case 6:
                        console.log('对不起,当前 没有权限 获取位置失败.');
                        break;
                    case 7:
                        console.log('对不起,服务不可用 获取位置失败.');
                        break;
                    case 8:
                        console.log('对不起,请求超时 获取位置失败.');
                        break;
                }
            }
        },
        {enableHighAccuracy: true}
    );
}

//模拟ajax
function ajax(parms) {
    var a = [],
        i;
    var page = parms.url.replace("getData.do?page=", "");
    for (i = 1; i <= 20; i++) {
        a.push({
            "index": i + (page - 1) * 20,
            "text": "普通分页加载模式"
        });
    }
    setTimeout(function() {
        parms.success(a);
    }, 800);
}

//定位城市
//localCity();
$(function () {
    //填充地地区
    var areaScope = $("#areaScope");
    $.each(zone, function (i, n) {
        var li = $("<li></li>");
        li.html(zone[i].name);
        li.attr("id", zone[i].id);
        areaScope.append(li);
    });

    $(".top_banner .L dd").click(function () {
        $("#bg").css({
            display: "block", height: $(document).height()
        });
        var $box = $('.payment_map_mask');
        $box.css({
            display: "block"
        });
    });
    //点击关闭按钮的时候，遮罩层关闭
    $(".payment_map_mask li").on('click',function () {
        $("#bg,.payment_map_mask").css("display", "none");
        $("#area").text($(this).html());
        var isChange = $("#areaId").val()!=$(this).attr("id");
        $("#areaId").val($(this).attr("id"));
        //如果地区改变，重新加载数据
        if(isChange){
            $("#mainFrm").submit();
        }
    });
    $(".payment_map_mask ol").on('click',function () {
        $("#bg,.payment_map_mask").css("display", "none");
    });
    if ($("#areaId").val() == ""||$("#areaId").val() == -1) {
        //根据地区，加载第一页数据
        // localCity(zone);
    }

    var jroll = new JRoll("#wrapper", {
        scrollBarY: true
    });

    jroll.infinite({
        getData: function(page, callback) { //获取数据的函数，必须传递一个数组给callback
            ajax({
                url: "getData.do?page=" + page,
                success: function(data) {
                    callback(data);
                }
            });
        },
        template: "<div class='item'>{{=_obj.index}}、{{=_obj.text}}</div>" //每条数据模板
    });

});
