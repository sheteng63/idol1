package com.tengs.idol.model.request;

import lombok.Data;

@Data
public class EventReq {
    /**
     * 活动名称
     */
    private String eventName;
    /**
     * 活动描述
     */
    private String eventDescrible;

    /**
     * 活动时间
     */
    private String time;

    /**
     * order id
     */
    private String orderId;

    private String id;

    private String status;

}
