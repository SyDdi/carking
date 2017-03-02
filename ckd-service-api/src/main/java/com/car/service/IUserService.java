package com.car.service;

import com.car.domain.User;

import java.util.List;

/**
 * Created by Administrator on 2016/11/6.
 */
public interface IUserService extends IService<User>{
    public List<User> selectByUser(User user, int page, int rows) ;

    public User selectByOpenId(String openId);

    /**
     * 查询用户信息  not in userId
     * @param userIds
     * @param type //1 普通用户 2 商家
     * @param find 1 not in 不传 in
     * @return
     */
    List<User> selectUserByInOrNotInUserId(List<Long> userIds , int type,int... find);
}
