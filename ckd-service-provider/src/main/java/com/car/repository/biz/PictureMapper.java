package com.car.repository.biz;

import com.car.domain.Picture;
import com.car.repository.IMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PictureMapper extends IMapper<Picture> {

    List<Picture> findImgByVehiclId(@Param("vehicleId") Long vehicleId,@Param("type") Integer type);

}