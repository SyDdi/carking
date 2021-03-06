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
var myScroll;
var pullDownEl, pullDownL;
var pullUpEl, pullUpL;
var Downcount = 0 , Upcount = 0;
var loadingStep = 0;//加载状态0默认，1显示加载状态，2执行加载数据，只有当为0时才能再次加载，这是防止过快拉动刷新

function pullDownAction() {//下拉事件
    setTimeout(function () {
//            var el, li, i;
//            el = $('#add');
//            for (i = 0; i < 3; i++) {
//                li = $("<li></li>");
//                Downcount++;
//                li.text('new Add ' + Downcount + " ！");
//                el.prepend(li);
//            }
        pullDownEl.removeClass('loading');
        pullDownL.html('下拉显示更多...');
        pullDownEl['class'] = pullDownEl.attr('class');
        pullDownEl.attr('class', '').hide();
        myScroll.refresh();
        loadingStep = 0;
    }, 1000); //1秒
}
function pullUpAction() {//上拉事件
    setTimeout(function () {
        var flashText = "";
        var page = $('#curPage').val();
        var totalPage = $('#totalPage').val();
        var area = $('#areaId').val();
        var q = $('#q').val();
        page++;//拖下一页，页面数+1
        //console.log("pull -> "+page + ":" + totalPage +":"+q);
        if (page > totalPage) {
            flashText = "没有更多数据了";
        } else {
            loadData(area, page, q);
            $('#curPage').val(page);
            flashText = "上拉显示更多...";
        }
        pullUpEl.removeClass('loading');

        pullUpL.html(flashText);
        pullUpEl['class'] = pullUpEl.attr('class');
        //pullUpEl.attr('class', '').hide();
        myScroll.refresh();
        loadingStep = 0;
    }, 1000);
}

function loadData(area, curPage,q) {
    //console.log("load data ->area：" + area + ",page:" + curPage + ",q:" + q );
    var el, section;
    el = $('#add');
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
function loaded() {
    pullDownEl = $('#pullDown');
    pullDownL = pullDownEl.find('.pullDownLabel');
    pullDownEl['class'] = pullDownEl.attr('class');
    pullDownEl.attr('class', '').hide();

    pullUpEl = $('#pullUp');
    pullUpL = pullUpEl.find('.pullUpLabel');
    pullUpEl['class'] = pullUpEl.attr('class');
    pullUpEl.attr('class', '').hide();

    myScroll = new IScroll('#content', {
        probeType: 2,//probeType：1对性能没有影响。在滚动事件被触发时，滚动轴是不是忙着做它的东西。probeType：2总执行滚动，除了势头，反弹过程中的事件。这类似于原生的onscroll事件。probeType：3发出的滚动事件与到的像素精度。注意，滚动被迫requestAnimationFrame（即：useTransition：假）。
        scrollbars: true,//有滚动条
        mouseWheel: true,//允许滑轮滚动
        fadeScrollbars: true,//滚动时显示滚动条，默认影藏，并且是淡出淡入效果
        bounce: true,//边界反弹
        interactiveScrollbars: true,//滚动条可以拖动
        shrinkScrollbars: 'scale',// 当滚动边界之外的滚动条是由少量的收缩。'clip' or 'scale'.
        click: true,// 允许点击事件
        keyBindings: true,//允许使用按键控制
        momentum: true
    });
    //滚动时
    myScroll.on('scroll', function () {
        if (loadingStep == 0 && !pullDownEl.attr('class').match('flip|loading') && !pullUpEl.attr('class').match('flip|loading')) {
            if (this.y > 5) {
                //下拉刷新效果
                pullDownEl.attr('class', pullUpEl['class'])
                pullDownEl.show();
                myScroll.refresh();
                pullDownEl.addClass('flip');
                pullDownL.html('准备刷新...');
                loadingStep = 1;
            } else if (this.y < (this.maxScrollY - 5)) {
                //上拉刷新效果
                pullUpEl.attr('class', pullUpEl['class'])
                pullUpEl.show();
                myScroll.refresh();
                pullUpEl.addClass('flip');
                pullUpL.html('准备刷新...');
                loadingStep = 1;
            }
        }
    });
    //滚动完毕
    myScroll.on('scrollEnd', function () {
        if (loadingStep == 1) {
            if (pullUpEl.attr('class').match('flip|loading')) {
                pullUpEl.removeClass('flip').addClass('loading');
                pullUpL.html('Loading...');
                loadingStep = 2;
                pullUpAction();
            } else if (pullDownEl.attr('class').match('flip|loading')) {
                pullDownEl.removeClass('flip').addClass('loading');
                pullDownL.html('Loading...');
                loadingStep = 2;
                pullDownAction();
            }
        }
    });
}
document.addEventListener('touchmove', function (e) {
    //e.preventDefault();
    // /^(?:INPUT|TEXTAREA|A)$/.test(e.target.tagName)||e.preventDefault();
}, false);

//定义存在的
//var zonestr = '[{name:"上海",id:"73"},{name:"北京",id:"1"},{name:"深圳",id:"203"},{name:"天津",id:"2"}]';
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
                    myScroll.refresh();
                });
            }
            else {
                //关于状态码
                //BMAP_STATUS_SUCCESS   检索成功。对应数值“0”。
                //BMAP_STATUS_CITY_LIST 城市列表。对应数值“1”。
                //BMAP_STATUS_UNKNOWN_LOCATION  位置结果未知。对应数值“2”。
                //BMAP_STATUS_UNKNOWN_ROUTE 导航结果未知。对应数值“3”。
                //BMAP_STATUS_INVALID_KEY   非法密钥。对应数值“4”。
                //BMAP_STATUS_INVALID_REQUEST   非法请求。对应数值“5”。
                //BMAP_STATUS_PERMISSION_DENIED 没有权限。对应数值“6”。(自 1.1 新增)
                //BMAP_STATUS_SERVICE_UNAVAILABLE   服务不可用。对应数值“7”。(自 1.1 新增)
                //BMAP_STATUS_TIMEOUT   超时。对应数值“8”。(自 1.1 新增)
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
        localCity(zone);
    }
});
