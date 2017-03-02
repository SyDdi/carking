package com.car.weixin.controller;

import com.car.core.utils.ImageUtil;
import com.car.exception.BusinessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by Administrator on 2016/11/10.
 */
@RestController
public class VehicleController extends GenericController {


    @RequestMapping(value = "/vehicle/test2", method = RequestMethod.GET)
//    @ResponseBody
    public String detail(ModelMap modelMap, HttpServletRequest request){
        throw new BusinessException("业务错误信息");
//        this.getUser(request);
//        return "vehicle/detail";
    }


    //生成验证码图片
//    @RequestMapping("/thumbnail/{prefix:[\\S]+}-{w:\\d+}x{h:\\d+}.{suffix:jpg|png|bmp|gif}")
//    public void thumbnail(@PathVariable String prefix, @PathVariable Integer w,@PathVariable Integer h,@PathVariable String suffix,HttpServletResponse response) throws Exception{


    /**
     * 动态生成缩略图
     * 仅仅是个例子。异常什么的都没处理，仅供参考
     * @param response
     * @param request
     * @throws Exception
     */

    @RequestMapping("/thumbnail/**")
    public void thumbnail(HttpServletResponse response,HttpServletRequest request) throws Exception{
        String url = "";
        url = request.getScheme() +"://" + request.getServerName()
                + ":" +request.getServerPort()
                + request.getServletPath();
        if (request.getQueryString() != null){
            url += "?" + request.getQueryString();
        }
        url = url.replaceFirst("thumbnail/","");

        String[] ks = url.split("^*-\\d+x\\d+.(jpg|png|bmp|gif)$");
        String end = url.substring(ks[0].length()+1);
        String ext = (end.split("\\."))[1];
        String pixels = (end.split("\\."))[0];
        String[] pixelss = pixels.split("x");
        int w= new Integer(pixelss[0]);
        int h = new Integer(pixelss[1]);
        String pic = ks[0]+"."+ext;



        //利用图片工具生成图片

        response.setContentType("image/jpeg");
        OutputStream os = response.getOutputStream();
        ImageUtil.resizeWithCrop(pic,os,w,h);
        response.flushBuffer();
        os.flush();
        os.close();
    }
}
