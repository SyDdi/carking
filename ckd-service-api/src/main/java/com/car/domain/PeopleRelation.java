package com.car.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "t_people_relation")
public class PeopleRelation implements Serializable {
    /**
     * 用户 t_user.id
     */
    @Id
    @Column(name = "user_id")
    private Long userId;

    /**
     * 好友id t_user.id
     */
    @Id
    @Column(name = "friend_user_id")
    private Long friendUserId;

    /**
     * 获取用户 t_user.id
     *
     * @return user_id - 用户 t_user.id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户 t_user.id
     *
     * @param userId 用户 t_user.id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取好友id t_user.id
     *
     * @return friend_user_id - 好友id t_user.id
     */
    public Long getFriendUserId() {
        return friendUserId;
    }

    /**
     * 设置好友id t_user.id
     *
     * @param friendUserId 好友id t_user.id
     */
    public void setFriendUserId(Long friendUserId) {
        this.friendUserId = friendUserId;
    }
}