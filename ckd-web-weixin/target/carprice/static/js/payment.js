$(function () {
    $(".top_banner .L dd").click(function () {
        $("#bg").css({
            display: "block", height: $(document).height()
        });
        var $box = $('.payment_map_mask');
        $box.css({
            display: "block",
        });
    });
    //点击关闭按钮的时候，遮罩层关闭
    $(".payment_map_mask li").on('click',function () {
        $("#bg,.payment_map_mask").css("display", "none");
        $(".top_banner .L dd").text($(this).html());
    });
	$(".payment_map_mask ol").on('click',function () {
        $("#bg,.payment_map_mask").css("display", "none");
    });
});