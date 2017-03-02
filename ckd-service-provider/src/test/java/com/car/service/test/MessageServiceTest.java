package com.car.service.test;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.car.domain.Message;
import com.car.repository.biz.MessageMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

public class MessageServiceTest extends BasicTest{

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
//		MessageMapper messageMapper=sqlSessionBiz.getMapper(MessageMapper.class);
//		Example example=new Example(Message.class);
//		Criteria criteria = example.createCriteria();
//		//vehicle_id等于3
//		criteria.andEqualTo("vehicleId", 3);
//		//id大于等于2
//		criteria.andGreaterThanOrEqualTo("id", 2);
//		List<Message> list = messageMapper.selectByExample(example);
//		//分页查询：第一页，共三页
//		PageHelper.startPage(1, 3);
//		PageInfo<Message> pageInfo=new PageInfo<Message>(list);
//
//		List<Message> dataList = pageInfo.getList();
//		for (Message message : dataList) {
//			System.out.println(message);
//		}
//		System.out.println("count: "+list.size());
//	}
//	@Test
//	public void insert(){
//		Message message=new Message();
//		//message.setParentId((long)11);
//		message.setUserId((long)2);
//		message.setVehicleId((long)3);
//		message.setContent("测试数据!!!");
//		MessageMapper messageMapper=sqlSessionBiz.getMapper(MessageMapper.class);
//		messageMapper.insertSelective(message);
//	}
//
//	@Test
//	public void delete(){
//		Message message=new Message();
//		message.setId((long)4);
//		MessageMapper messageMapper=sqlSessionBiz.getMapper(MessageMapper.class);
//		messageMapper.deleteByExample(message);
//	}
//
//	@Test
//	public void update(){
//		Message message=new Message();
//		message.setId((long)4);
//		message.setContent("测试新数据");
//		MessageMapper messageMapper=sqlSessionBiz.getMapper(MessageMapper.class);
//		messageMapper.updateByPrimaryKeySelective(message);
//	}
}
