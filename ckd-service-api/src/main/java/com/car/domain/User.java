package com.car.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@Table(name = "t_user")
public class User extends IdEntity {
	/**
	 * admin id
	 */
	private Long adminUserId;
	
    /**
     * 用户登陆名
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 用户中文名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 用户手机号
     */
    @Column(name = "telephone")
    private String telephone;

    /**
     * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息
     */
    private Integer subscribe;

    /**
     * 用户的标识，对当前公众号唯一
     */
    @Column(name = "open_id")
    private String openId;

    /**
     * 用户的昵称 
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     */
    @Column(name = "head_img_url")
    private String headImgUrl;

    /**
     * 是否启用
     */
    private Integer enable;

    /**
     * 用户类型(1:普通用户，2：商家 ,3:后台用户）
     */
    private Integer type;

    /**
     * 用户类型(1:注册用户，2：管理员添加)
     */
    private Integer source;

    /**
     * 创建日期
     */
    private Date created;

    /**
     * 更新日期
     */
    private Date upcreated;

    /**
     * 登录次数
     */
    @Column(name = "login_num")
    private Integer loginNum;

    /**
     * 当前登录时间
     */
    @Column(name = "login_time")
    private Date loginTime;

    /**
     * 上次登录时间
     */
    @Column(name = "old_login_time")
    private Date oldLoginTime;

    /**
     * 当前登录ip
     */
    @Column(name = "login_ip")
    private String loginIp;

    /**
     * 商家描述
     */
    @Column(name = "quote_desc")
    private String quoteDesc;
    
    /**
     * 上次登录ip
     */
    @Column(name = "old_login_ip")
    private String oldLoginIp;
    
    @Transient
    private User user;
    public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@Transient
    private String plainPassword;
    // 不持久化到数据库，也不显示在Restful接口的属性.
    @Transient
    @JsonIgnore
    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }
    
    public Long getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(Long adminUserId) {
		this.adminUserId = adminUserId;
	}
	/**
     * 获取用户登陆名
     *
     * @return login_name - 用户登陆名
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置用户登陆名
     *
     * @param loginName 用户登陆名
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取盐值
     *
     * @return salt - 盐值
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置盐值
     *
     * @param salt 盐值
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * 获取用户中文名
     *
     * @return user_name - 用户中文名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户中文名
     *
     * @param userName 用户中文名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取用户手机号
     *
     * @return telephone - 用户手机号
     */

    public String getTelephone() {
        return telephone;
    }
	/**
     * 设置用户手机号
     *
     * @param telephone 用户手机号
     */
    public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

    /**
     * 获取用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息
     *
     * @return subscribe - 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息
     */
    public Integer getSubscribe() {
        return subscribe;
    }

    /**
     * 设置用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息
     *
     * @param subscribe 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息
     */
    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    /**
     * 获取用户的标识，对当前公众号唯一
     *
     * @return open_id - 用户的标识，对当前公众号唯一
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置用户的标识，对当前公众号唯一
     *
     * @param openId 用户的标识，对当前公众号唯一
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    /**
     * 获取用户的昵称 
     *
     * @return nick_name - 用户的昵称 
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置用户的昵称 
     *
     * @param nickName 用户的昵称 
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * 获取用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     *
     * @return head_img_url - 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     */
    public String getHeadImgUrl() {
        return headImgUrl;
    }

    /**
     * 设置用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     *
     * @param headImgUrl 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     */
    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl == null ? null : headImgUrl.trim();
    }

    /**
     * 获取是否启用
     *
     * @return enable - 是否启用
     */
    public Integer getEnable() {
        return enable;
    }

    /**
     * 设置是否启用
     *
     * @param enable 是否启用
     */
    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    /**
     * 获取用户类型(1:普通用户，2：商家 ,3:后台用户）
     *
     * @return type - 用户类型(1:普通用户，2：商家 ,3:后台用户）
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置用户类型(1:普通用户，2：商家 ,3:后台用户）
     *
     * @param type 用户类型(1:普通用户，2：商家 ,3:后台用户）
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取用户类型(1:注册用户，2：管理员添加)
     *
     * @return source - 用户类型(1:注册用户，2：管理员添加)
     */
    public Integer getSource() {
        return source;
    }

    /**
     * 设置用户类型(1:注册用户，2：管理员添加)
     *
     * @param source 用户类型(1:注册用户，2：管理员添加)
     */
    public void setSource(Integer source) {
        this.source = source;
    }

    /**
     * 获取创建日期
     *
     * @return created - 创建日期
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置创建日期
     *
     * @param created 创建日期
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * 获取更新日期
     *
     * @return upcreated - 更新日期
     */
    public Date getUpcreated() {
        return upcreated;
    }

    /**
     * 设置更新日期
     *
     * @param upcreated 更新日期
     */
    public void setUpcreated(Date upcreated) {
        this.upcreated = upcreated;
    }

    /**
     * 获取登录次数
     *
     * @return login_num - 登录次数
     */
    public Integer getLoginNum() {
        return loginNum;
    }

    /**
     * 设置登录次数
     *
     * @param loginNum 登录次数
     */
    public void setLoginNum(Integer loginNum) {
        this.loginNum = loginNum;
    }

    /**
     * 获取当前登录时间
     *
     * @return login_time - 当前登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 设置当前登录时间
     *
     * @param loginTime 当前登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 获取上次登录时间
     *
     * @return old_login_time - 上次登录时间
     */
    public Date getOldLoginTime() {
        return oldLoginTime;
    }

    /**
     * 设置上次登录时间
     *
     * @param oldLoginTime 上次登录时间
     */
    public void setOldLoginTime(Date oldLoginTime) {
        this.oldLoginTime = oldLoginTime;
    }

    /**
     * 获取当前登录ip
     *
     * @return login_ip - 当前登录ip
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 设置当前登录ip
     *
     * @param loginIp 当前登录ip
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    /**
     * 获取上次登录ip
     *
     * @return old_login_ip - 上次登录ip
     */
    public String getOldLoginIp() {
        return oldLoginIp;
    }

    /**
     * 设置上次登录ip
     *
     * @param oldLoginIp 上次登录ip
     */
    public void setOldLoginIp(String oldLoginIp) {
        this.oldLoginIp = oldLoginIp == null ? null : oldLoginIp.trim();
    }
    
    /**
     * 获取商家描述
     *
     * @return quoteDesc - 商家描述
     */
	public String getQuoteDesc() {
		return quoteDesc;
	}
    /**
     * 设置商家描述
     *
     * @param quoteDesc 商家描述
     */
	public void setQuoteDesc(String quoteDesc) {
		this.quoteDesc = quoteDesc;
	}
}