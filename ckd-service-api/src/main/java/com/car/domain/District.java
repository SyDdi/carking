package com.car.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_sys_district")
public class District extends IdEntity {

    private static final long serialVersionUID = -5031470235579721322L;

    private String districtName;

    private Long zoneId;


    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictName() {
        return this.districtName;
    }

    public Long getZoneId() {
        return zoneId;
    }

    public void setZoneId(Long zoneId) {
        this.zoneId = zoneId;
    }





}