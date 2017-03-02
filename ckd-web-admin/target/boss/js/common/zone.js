/**
 * Created by shenyong on 2016/11/29.
 */
$(function(){

});
/**
 *  初始化省市级联下拉框
 * @param provinceElement 省份下拉框 id
 * @param zoneElement 市 下拉框ID
 *
 * deom：
 *      initZoneData("siteProvince","siteZone");
 * @author  yong.shen@carking001.com
 */
function initZoneElement(provinceElement,zoneElement){
    queryProvince(provinceElement,zoneElement)
}

/**
 *
 *  初始化省市级联下拉框 回显数据
 * @param provinceElement 省份下拉框 id
 * @param zoneElement 市 下拉框ID
 * @param provinceId 省Id
 * @param zoneId 市ID
 *
 * demo：
 *      initZoneData("siteProvince","siteZone",vhcl.provinceId,vhcl.zoneId);
 *
 * @author  yong.shen@carking001.com
 */
function initZoneData(provinceElement,zoneElement,provinceId,zoneId){
    queryProvince(provinceElement,zoneElement,provinceId,zoneId);
}
function queryProvince(provinceElement,zoneElement,provinceId,zoneId){
    $.ajax({
        type: "GET",
        url: basePath+"/portal/admin/city/province.shtml",
        dataType: 'json',
        async: false,
        success: function (data) {
            for (var i in data) {
                var province = data[i];
                if(province.id != null ){
                    if(province.id != provinceId ) {
                        $("#" + provinceElement).append("<option value='" + province.id + "'>" + province.name + "</option>");
                    }else{
                        $("#" + provinceElement).append("<option value='" + province.id + "' selected>" + province.name + "</option>");
                    }
                }
            }
            /*省份点击事件*/
            $('#'+provinceElement).on('change', function () {
                var provinceId = $(this).val();
                queryZone(zoneElement,provinceId,zoneId);
                $("#" + zoneElement).html("<option value=''>请选择</option>");
            });
            if(provinceId != null && ""!=provinceId){
                $("#" + provinceElement).change();
            }

        }
    });
}

function queryZone(zoneElement,provinceId,zoneId){
    $.ajax({
        type: "GET",
        url: basePath+"/portal/admin/city/zoneList.shtml?provinceId="+provinceId,
        dataType: 'json',
        success: function (data) {
            for (var i in data) {
                var zone = data[i];
                if(zone.id != null ){
                    if(zone.id != zoneId) {
                        $("#" + zoneElement).append("<option value='" + zone.id + "'>" + zone.name + "</option>");
                    }else{
                        $("#" + zoneElement).append("<option value='" + zone.id + "'selected>" + zone.name + "</option>");
                    }
                }
            }
        }
    });
}

