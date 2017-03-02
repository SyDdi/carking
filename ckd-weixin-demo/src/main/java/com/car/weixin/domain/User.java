package com.car.weixin.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/23.
 */
public class User implements Serializable{

    String openId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return "User{" +
                "openId='" + openId + '\'' +
                '}';
    }
}
