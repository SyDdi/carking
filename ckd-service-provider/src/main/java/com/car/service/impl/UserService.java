package com.car.service.impl;


import com.car.domain.User;
import com.car.service.IUserService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

/**
 * Created by Administrator on 2016/11/6.
 */
@Service("IUserService")
public class UserService extends BaseService<User> implements IUserService {
    public List<User> selectByUser(User user, int page, int rows) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (user.getId() != null) {
            criteria.andEqualTo("id", user.getId());
        }
        //分页查询
        PageHelper.startPage(page, rows);
        return selectByExample(example);
    }

    @Override
    public User selectByOpenId(String openId){
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("openId",openId );
        PageHelper.startPage(1, 1);
        List<User> users = selectByExample(example);
        if(users.size()>0){
            return users.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<User> selectUserByInOrNotInUserId(List<Long> userIds, int type ,int... find) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if(userIds.size()>0) {
            if (find != null) {
                criteria.andNotIn("id", userIds);
            } else {
                criteria.andIn("id", userIds);
            }
        }
        criteria.andEqualTo("type",type);
        List<User> users = selectByExample(example);
        if(users.size()>0){
            return users;
        }else{
            return null;
        }
    }
}
