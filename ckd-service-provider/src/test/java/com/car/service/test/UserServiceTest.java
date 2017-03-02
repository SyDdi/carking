package com.car.service.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.car.domain.User;
import com.car.repository.biz.UserMapper;

public class UserServiceTest extends BasicTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Autowired
	private SqlSession sqlSessionBiz;
//	@Test
//	public void select() {
//		UserMapper userMapper=sqlSessionBiz.getMapper(UserMapper.class);
//        User user = userMapper.selectByPrimaryKey(3);
//        System.out.println(user);
//	}
	@Test
	public void insert(){
		UserMapper userMapper=sqlSessionBiz.getMapper(UserMapper.class);
		User user=new User();
		user.setLoginName("华");
		int  result = userMapper.insert(user);
        System.out.println("result="+result);
        System.out.println("id="+user.getId());
    }

}
