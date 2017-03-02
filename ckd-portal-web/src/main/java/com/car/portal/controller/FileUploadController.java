package com.car.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.car.core.utils.FileUtils;
import com.car.core.utils.JsonUtils;
import com.car.domain.Province;
import com.car.domain.Zone;
import com.car.domain.dto.ResultDto;
import com.car.domain.dto.UploadFileDto;
import com.car.exception.CommonException;
import com.car.portal.utils.RestResultGenerator;
import com.car.service.CommonUploadFileService;
import com.car.service.IProvinceService;
import com.car.service.IZoneService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/10.
 */
@RestController
@RequestMapping(value = "/upload")
public class FileUploadController {

    Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Reference
    CommonUploadFileService commonUploadFileService;

    @RequestMapping(value = "/file")
    @ResponseBody
    public Object upload(@RequestParam("photo") MultipartFile file) throws Exception {
        ResultDto result = null;
        String filename = file.getOriginalFilename();    //得到上传时的文件名
        UploadFileDto uploadFileDto = new UploadFileDto(FileUtils.getFileType(filename),file.getBytes());
        uploadFileDto.setMosaic(true);
        result = commonUploadFileService.uploadFile(uploadFileDto);
        return RestResultGenerator.genResult(result, "成功!");
    }

    @RequestMapping(value = "/base64")//,method = RequestMethod.POST
    @ResponseBody
    public Object base64(@RequestParam("data") String data,HttpServletRequest request,HttpServletResponse response) throws Exception {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods","POST");
        String ext = StringUtils.substringBetween(data, "data:image/", ";");//图片后缀
        String base64ImgData =StringUtils.substringAfter(data, "base64,");//图片数据
        byte[] pic = Base64Utils.decodeFromString(base64ImgData);
        UploadFileDto uploadFileDto = new UploadFileDto(ext,pic);
        uploadFileDto.setMosaic(true);
        ResultDto result = commonUploadFileService.uploadFile(uploadFileDto);
        return RestResultGenerator.genResult(result, "成功!");
    }

    //base64字符串转化成图片
    public  boolean GenerateImage(String imgStr)
    {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            String imgFilePath = "d://222.jpg";//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
