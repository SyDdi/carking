package com.car.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;
import java.util.Vector;

import com.car.core.utils.FileUtils;
import com.car.core.utils.ImageUtil;
import com.car.domain.dto.ResultDto;
import com.car.domain.dto.UploadFileDto;
import com.car.easypr.core.PlateLocate;
import com.car.exception.CommonException;
import com.car.exception.ERRORS;
import com.car.service.CommonUploadFileService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.bytedeco.javacpp.opencv_core;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static org.bytedeco.javacpp.opencv_highgui.imread;

/**
 * 公共上传文件服务接口实现类
 * 
 * @author mr_smile
 * 
 */
@Service("CommonUploadFileService")
public class CommonUploadFileServiceImpl implements CommonUploadFileService {

    @Value("${fileRootPath}")
    private String fileRootPath;

    @Value("${image.width}")
    private int width;
    @Value("${image.height}")
    private int height;

    /**
     * 日志
     */
    private final Logger log = Logger.getLogger(CommonUploadFileServiceImpl.class);

    /**
     * 上传文件
     *
     * @param uploadFileDto 上传文件dto
     * @return
     */
    @Override
    public ResultDto uploadFile(UploadFileDto uploadFileDto)
            throws CommonException {

        /** 日志标识 */
        String logId = UUID.randomUUID().toString();
        log.info("[上传文件（uploadFile）][" + logId + "][prams：uploadFileDto="
                + uploadFileDto.toString() + "]");
        try {
            /** 判断参数是否为空 */
            vlidParamIsNull(uploadFileDto, logId);

            /** 调用工具类上传 */
            String fileName = this.genFileName(uploadFileDto.getFileExtName()) ;
            String realPath = fileRootPath+fileName;
            int iMosaicCount = 0;

            try {
                FileUtils.saveFile(uploadFileDto.getFileBytes(), realPath);
                //如果是图片，进行压缩处理
                log.debug("file="+realPath);
                log.debug("file exist="+new File(realPath).exists());
                if(isImage(uploadFileDto.getFileExtName())){
                    ImageUtil.resize(realPath,realPath,width,height,80d,false);
                    //如果打马赛克，则将原图拷贝备份
                    if(uploadFileDto.isMosaic()){
                        PlateLocate plate = new PlateLocate();
                        plate.setDebug(false);
                        plate.setLifemode(true);
                        iMosaicCount = plate.plateLocate(realPath);
                        log.debug("对图片的"+iMosaicCount+"个车牌进行打码");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            ResultDto result = new ResultDto(fileName, "group1",realPath);
            result.setMosaicCount(iMosaicCount);

            log.info("[上传文件（uploadFile)-返回结果][" + logId
                    + "][results：resultDto=" + result.toString() + "]");

            return result;

        } catch (CommonException e) {
            log.error("[上传文件（uploadFile)][" + logId + "][异常：" + e + "]");
            throw e;
        }
    }
    /**
     * 删除fastdfs服务器中文件
     *
     * @param filePath 文件远程地址
     * @return
     */
    public void deleteFile(String filePath) throws CommonException {

        /** 日志标识 */
        String logId = UUID.randomUUID().toString();
        log.info("[ 删除文件（deleteFile）][" + logId + "][parms：filePath="
                + filePath + "]");
        try {
            if (StringUtils.isBlank(filePath)) {
                throw ERRORS.PARAMETER_IS_NULL.ERROR();
            }

//			/** 解析文件路径 */
//			String[] results = CommonUtils.parseFilePath(filePath, logId);
//
//			/** 删除文件 */
//			fastDfsUtil.deleteFile(results[0], results[1]);

        } catch (CommonException e) {
            log.error("[ 删除文件（deleteFile）][" + logId + "][异常：" + e + "]");
            throw e;

        }
    }

    /**
     * @param uploadFileDto
     * @param logId
     * @Description: 验证上传方法参数是否为空
     */
    public void vlidParamIsNull(UploadFileDto uploadFileDto, String logId)
            throws CommonException {

        log.info("[验证上传方法参数是否为空（vlidParamIsNull）][" + logId
                + "][prams：uploadFileDto=" + uploadFileDto.toString() + "]");

        if (null == uploadFileDto || uploadFileDto.getFileBytes() == null
                || null == uploadFileDto.getFileExtName()) {
            throw ERRORS.PARAMETER_IS_NULL.ERROR();
        }

    }

    /**
     * 生成图片目录
     * @param ext
     * @return
     */
    private String genFileName(String ext) {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        String time = formatter.format(now.getTime());
        String fileName=File.separator+time+ File.separator+System.currentTimeMillis()+ RandomStringUtils.randomNumeric(10)+"."+ext;
        return fileName;
    }


    private boolean isImage(String ext){
        boolean isImage = false;
        if(ext==null) return false;
        if(ext.equalsIgnoreCase("jpg")||ext.equalsIgnoreCase("jpeg")||ext.equalsIgnoreCase("png")){
            isImage = true;
        }
        return isImage;
    }
}
