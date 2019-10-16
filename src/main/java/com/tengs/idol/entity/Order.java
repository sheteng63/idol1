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
@TableName("idol_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 申请人id
     */
    private String applyUserId;

    /**
     * 接受人id
     */
    private String acceptUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 修改人
     */
    private Integer modifyUserId;

    /**
     * 开始时间
     */
    private Date startDateTime;

    /**
     * 开始地点
     */
    private String startLocation;

    /**
     * 申请方反馈
     */
    private String applyMark;

    /**
     * 接收方反馈
     */
    private String acceptMark;

    /**
     * 状态
     */
    private String status;

    @TableId("id")
    private String id;

    private String applyUserAvatar;

    private String acceptUserAvatar;


}
