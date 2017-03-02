package com.car.domain;

import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;


@Entity
@Table(name = "t_sys_province")
public class Province extends IdEntity {


    private String provinceName;
    @OrderBy(" asc")
    private String orderBy;
    private String shortProvinceName;
    private int status;

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceName() {
        return this.provinceName;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return this.orderBy;
    }

    public String getShortProvinceName() {
        return shortProvinceName;
    }

    public void setShortProvinceName(String shortProvinceName) {
        this.shortProvinceName = shortProvinceName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}