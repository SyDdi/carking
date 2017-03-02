package com.car.mp.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.car.domain.User;
import com.car.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/11/24.
 */
@Service
public class UserService {

    @Reference
    IUserService userService;

    public User getUserByOpenId(String openId){
        return userService.selectByOpenId(openId);
    }

    public User createUser(User user){
        User u = userService.insert(user);
        return u;
    }

    public Integer updateUser(User user){
        return userService.updateNotNull(user);
    }

}
