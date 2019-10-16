package com.tengs.idol.service;

import com.tengs.idol.core.exception.BzException;
import com.tengs.idol.entity.Event;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tengs.idol.entity.User;
import com.tengs.idol.model.request.EventReq;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2019-10-13
 */
public interface IEventService extends IService<Event> {

    void create(EventReq req, User user) throws BzException;

    List<Event> getList(String orderId) throws BzException;

    void update(EventReq req, User user) throws BzException;

    Event getEvent(String eventId) throws BzException;
}
