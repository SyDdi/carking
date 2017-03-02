package com.car.mp.test;

import com.car.domain.User;
import com.car.mp.config.ApplicationConfig;
import com.car.mp.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/11/29.
 */
public class UserTest extends BasicTest {

    @Autowired
    UserService userService;
    @Test
    public void test() {

        User u = new User();
        u.setId(1l);
        u.setNickName("axy222");
        userService.updateUser(u);

    }

}
