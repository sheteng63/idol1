package com.tengs.idol.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2019-10-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("idol_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 微信昵称
     */
    private String nickName;

    /**
     * openid
     */
    private String openId;

    /**
     * 头像链接
     */
    private String avatarUrl;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 上次登录
     */
    private Date lastLoginTime;

    /**
     * 用户状态
     */
    private String isActive;

    /**
     * 性别
     */
    private String sex;

    /**
     * id
     */
    @TableId("id")
    private String id;

}
