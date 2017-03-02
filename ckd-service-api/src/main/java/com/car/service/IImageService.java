package com.car.service;

import com.car.domain.dto.ResultDto;
import com.car.domain.dto.UploadFileDto;
import com.car.exception.CommonException;

import java.io.File;

/**
 * 图片处理服务
 */
public interface IImageService {

    /**
     * 对车牌进行马赛克处理
     * @param file
     * @return 遮挡的车牌的数量
     */
    public  File mosaicPlate(File file);

    /**
     * 对车牌进行马赛克处理
     * @param imgPath
     * @return 遮挡的车牌的数量
     */
    public  int mosaicPlate(String  imgPath);

}
