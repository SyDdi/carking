package com.car.service.test;

/**
 * Created by Administrator on 2016/11/14.
 */
import com.car.domain.Zone;
import com.car.repository.base.ZoneMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by liuzh on 2015/3/7.
 */
public class IZoneServiceTest extends BasicTest {

//    @Autowired
//    private ZoneMapper ZoneMapper;

    @Autowired
    private SqlSession sqlSessionBase;

    @Test
    public void test() {
        //

        ZoneMapper zoneMapper = sqlSessionBase.getMapper(ZoneMapper.class);
        Example example = new Example(Zone.class);
        example.createCriteria().andGreaterThan("id", 0);

        for(int i=0 ;i<10;i++) {

            PageHelper.startPage(2, 10);
            List<Zone> zones = zoneMapper.selectByExample(example);
            PageInfo<Zone> pageInfo = new PageInfo<Zone>(zones);
            System.out.println(pageInfo.getList().size());

            zones = zoneMapper.selectByExample(example);
            pageInfo = new PageInfo<Zone>(zones);
            System.out.println(pageInfo.getTotal());

            System.out.println("==============="+i+"=============");
        }
    }
}