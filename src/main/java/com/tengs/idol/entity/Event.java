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
@TableName("idol_event")
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 活动名称
     */
    private String eventName;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 活动描述
     */
    private String eventDescrible;

    /**
     * order id
     */
    private String orderId;

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
    private String modifyUserId;

    /**
     * 状态
     */
    private String status;

    /**
     * 活动时间
     */
    private String time;

    @TableId("id")
    private String id;


}
