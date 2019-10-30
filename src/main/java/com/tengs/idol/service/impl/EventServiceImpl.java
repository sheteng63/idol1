package com.tengs.idol.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tengs.idol.core.exception.BzException;
import com.tengs.idol.core.util.UniCodeUtil;
import com.tengs.idol.entity.Event;
import com.tengs.idol.entity.Order;
import com.tengs.idol.entity.User;
import com.tengs.idol.mapper.EventMapper;
import com.tengs.idol.mapper.OrderMapper;
import com.tengs.idol.model.request.EventReq;
import com.tengs.idol.service.IEventService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-10-13
 */
@Service
public class EventServiceImpl extends ServiceImpl<EventMapper, Event> implements IEventService {

    @Autowired
    private EventMapper eventMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void create(EventReq req, User user) throws BzException {
        String orderId = req.getOrderId();
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("id", orderId);
        wrapper.eq("status", "0");
        Order order = orderMapper.selectOne(wrapper);
        if (order == null) {
            throw new BzException("000002", "订单不存在");
        }
        if (StringUtils.equals(order.getStatus(), "2")) {
            throw new BzException("000002", "拒绝之后不能修改");
        }
        Event event = new Event();
        event.setId(UniCodeUtil.createUUID());
        event.setCreateTime(new Date());
        event.setCreateUser(user.getId());
        event.setEventDescrible(req.getEventDescrible());
        event.setOrderId(orderId);
        event.setModifyTime(new Date());
        event.setEventName(req.getEventName());
        event.setStatus("0");
        event.setTime(req.getTime());
        eventMapper.insert(event);
    }

    @Override
    public List<Event> getList(String orderId) throws BzException {
        QueryWrapper<Event> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id", orderId);
        wrapper.eq("status", "0");
        List<Event> events = eventMapper.selectList(wrapper);
        return events;
    }

    @Override
    public void update(EventReq req, User user) throws BzException {
        String orderId = req.getOrderId();
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("id", orderId);
        wrapper.eq("status", "0");
        Order order = orderMapper.selectOne(wrapper);
        if (order == null) {
            throw new BzException("000002", "订单不存在");
        }
        if (StringUtils.equals(order.getStatus(), "2")) {
            throw new BzException("000002", "拒绝之后不能修改");
        }
        if (!StringUtils.equals(order.getAcceptUserId(), user.getId()) && !StringUtils.equals(order.getApplyUserId(), user.getId())) {
            throw new BzException("000003", "无权限");
        }
        QueryWrapper<Event> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("id", req.getId());
        wrapper1.eq("status", "0");
        Event event = eventMapper.selectOne(wrapper1);
        if (event == null) {
            throw new BzException("000002", "活动不存在");
        }
        event.setEventDescrible(req.getEventDescrible());
        event.setOrderId(orderId);
        event.setModifyTime(new Date());
        event.setEventName(req.getEventName());
        event.setModifyUserId(user.getId());
        event.setStatus(req.getStatus());
        eventMapper.updateById(event);
    }

    @Override
    public Event getEvent(String eventId) throws BzException {
        QueryWrapper<Event> wrapper = new QueryWrapper<>();
        wrapper.eq("id", eventId);
        wrapper.eq("status", "0");
        Event event = eventMapper.selectOne(wrapper);
        if (event == null) {
            throw new BzException("000002", "活动不存在");
        }
        return event;
    }
}
