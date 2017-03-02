package com.car.service.impl;

import com.car.easypr.core.PlateLocate;
import com.car.service.IImageService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.File;

@Service("IImageService")
public class ImageService implements IImageService {

    /**
     * 日志
     */
    private final Logger log = Logger.getLogger(ImageService.class);

    @Override
    public File mosaicPlate(File file) {
        return null;
    }

    /**
     * 根据路径对图片进行处理，单机版
     * @param imgPath
     * @return
     */
    @Override
    public int mosaicPlate(String imgPath) {
        PlateLocate plate = new PlateLocate();
        plate.setDebug(false);
        plate.setLifemode(true);
        int iMosaicCount = plate.plateLocate(imgPath);
        log.debug("对图片的"+iMosaicCount+"个车牌进行打码");
        return iMosaicCount;
    }
}
