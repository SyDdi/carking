package com.car.service.impl;

import com.car.repository.biz.PictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.car.domain.Picture;
import com.car.service.IPictureService;

import java.util.List;

/**
 * @author admin
 */
@Component
@Service("IPictureService")
public class PictureService extends BaseService<Picture> implements IPictureService {

    @Autowired
    public PictureMapper pictureMapper;

    @Override
    public List<Picture> findImgByVehiclId(Long vehicleId,Integer type) {
        return pictureMapper.findImgByVehiclId(vehicleId,type);
    }
}
