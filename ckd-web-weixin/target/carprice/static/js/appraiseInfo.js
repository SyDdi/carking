/**
 * 创建图表
 * @param retailDataArray  零售价
 * @param purchaseDataArray 收购价
 */
function createChat(retailDataArray,purchaseDataArray,labels,startScale,endScale,spaceScale){
	var windowWidth = $(window).width();
	var data = [
		{
			name : '零售价',
			value:retailDataArray,
			color:'#aad0db',
			line_width:2
		},
		{
			name : '收购价',
			value:purchaseDataArray,
			color:'#f68f70',
			line_width:2
		}
	];
	var chart = new iChart.Area2D({
		render : 'canvasDiv',
		data: data,
		align:'center',
		footnote : '',
		width : windowWidth-20,
		height : 300,
        decimalsnum:2,
        turn_off_touchmove:true,
		sub_option:{
			smooth : true,//平滑曲线
			point_size:10
		},
        legend : {
            enable : true,
            row:1,//设置在一行上显示，与column配合使用
            column : 'max',
            valign:'top',
            background_color:null,//设置透明背景
            offsetx:-30,//设置x轴偏移，满足位置需要
			offsety:0,//设置y轴偏移，满足位置需要
            border : false
        },
		coordinate:{
//           width:windowWidth-80,
//           valid_width:$(window).width()-130,
//            height:160,
            width:'80%',
            valid_width:'85%',
            height : 200,

			axis:{
				color:'#9f9f9f',
				width:[0,0,2,2]
			},

          scale2grid:false,
            grids:{
                horizontal:{
                    way:'share_alike',
                    value:5
                }
            },
            scale:[{
                position:'left',
                start_scale:startScale,
                end_scale:endScale,
                //scale_space:spaceScale,
                scale_enable:false//禁用小横线
            },{
                position:'bottom',
                labels:labels
            }]
		}
	});

    //利用自定义组件构造左侧说明文本
    chart.plugin(new iChart.Custom({
        drawFn:function(){
            //计算位置
            var coo = chart.getCoordinate(),
                x = coo.get('originx'),
                y = coo.get('originy'),
                w = coo.width,
                h = coo.height;
            //在左上侧的位置，渲染一个单位的文字
            chart.target.textAlign('start')
                .textBaseline('bottom')
                .textFont('600 26px')
                .fillText('单位(万)',x,y-10,false,'#000000');
        }
    }));
    //开始画图
	chart.draw();


    function move(a) {
        $(".dotA")
            .animate({
                top:a.offset_topA//弹跳像素
            },
            o.speed,//动画速度
            o.fx//动画完成后执行函数
        );
    }
    o = {};
    o.spead = 500;//速度
    o.move = 0;//初始化动作
    o.fx = function(){
        if(o.move==0){//判断动作
            o.move = 1;//改变动作
            a = {
                offset_topA:"5px"//移动像素
            };
        }else{
            o.move = 0;
            a = {
                offset_topA:"0px"
            };
        }
        move(a);//执行
    }
    $(document).ready(function() {
        o.fx();//调用

        jQuery(".intro_qhys01 li").bind("click",function(){
            //更改标签样式
            jQuery(this).siblings("li").removeClass("on");
            jQuery(this).addClass("on");
            //当前标签的序号
            var index = jQuery(".intro_qhys01 li").index(this);
            //显示对应序号的价格内容
            var ulObj = jQuery(".intro_gzbb_xjt ul:gt(0)").eq(index);
            jQuery(ulObj).siblings("ul:gt(0)").hide();
            jQuery(ulObj).show();
        });
    });



}
