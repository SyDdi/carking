package com.car.service.impl;

import com.car.domain.PeopleRelation;
import com.car.domain.PeopleRelationExample;
import com.car.repository.biz.PeopleRelationMapper;
import com.car.security.HashIdsHelper;
import com.car.service.IPeopleRelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2016/12/26.
 */
@Service("IPeopleRelationService")
public class PeopleRelationServiceImpl implements IPeopleRelationService {

    @Resource
    private PeopleRelationMapper peopleRelationMapper;

    @Override
    public int insert(PeopleRelation record) {
        return peopleRelationMapper.insert(record);
    }

    @Override
    public int countByExample(PeopleRelationExample example) {
        return peopleRelationMapper.countByExample2(example);
    }

    @Override
    public int deleteByExample(PeopleRelationExample example) {
        return peopleRelationMapper.deleteByExample2(example);
    }

    @Override
    public List<PeopleRelation> selectByExample(PeopleRelationExample example) {
        return peopleRelationMapper.selectByExample2(example);
    }

    @Override
    public int updateByExampleSelective(PeopleRelation record, PeopleRelationExample example) {
        return peopleRelationMapper.updateByExampleSelective2(record,example);
    }

    @Override
    public int updateByExample(PeopleRelation record, PeopleRelationExample example) {
        return peopleRelationMapper.updateByExample2(record,example);
    }

    /**
     * 双向添加好友
     * @param source 来源
     * @param userId 当前用户
     */
    @Override
    public void saveFriend(String source, Long userId) {
        try {
            if (source != null && !"".equals(source)) {
                Long friendId = HashIdsHelper.decode(source, -1);
                if (friendId != null && friendId > 0) {
                    if (friendId != userId && !friendId.equals(userId)) {
                        PeopleRelationExample pre = new PeopleRelationExample();
                        pre.createCriteria().andUserIdEqualTo(userId).andFriendUserIdEqualTo(friendId);
                        List<PeopleRelation> list = this.selectByExample(pre);
                        if (list.size() <= 0) { //没有添加过好友
                            PeopleRelation pr = new PeopleRelation();
                            pr.setUserId(userId);
                            pr.setFriendUserId(friendId);
                            this.insert(pr);
                            //双向添加好友
                            pr.setUserId(friendId);
                            pr.setFriendUserId(userId);
                            this.insert(pr);
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
