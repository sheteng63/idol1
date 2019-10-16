package com.tengs.idol.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tengs.idol.core.util.DateUtil;
import com.tengs.idol.core.util.UniCodeUtil;
import com.tengs.idol.entity.Order;
import com.tengs.idol.entity.User;
import com.tengs.idol.mapper.OrderMapper;
import com.tengs.idol.mapper.UserMapper;
import com.tengs.idol.model.request.CreateOrderReq;
import com.tengs.idol.model.response.OrderDetRes;
import com.tengs.idol.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-10-13
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Order create(CreateOrderReq req, User user) {
        Order order = new Order();
        order.setCreateTime(new Date());
        order.setModifyTime(new Date());
        order.setApplyUserId(user.getId());
        order.setId(UniCodeUtil.createUUID());
        order.setStartDateTime(DateUtil.getDate(req.getDate(), DateUtil.DATE_FORMAT_DEFAULT));
        order.setStartLocation(req.getPlace().toString());
        order.setApplyUserAvatar(user.getAvatarUrl());
        order.setStatus("0");
        orderMapper.insert(order);
        return order;
    }

    @Override
    public OrderDetRes getOrder(String orderId) {
        OrderDetRes orderDetRes = new OrderDetRes();
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("id", orderId);
        Order order = orderMapper.selectOne(wrapper);
        orderDetRes.setOrder(order);
        User acceptUser = userMapper.selectById(order.getAcceptUserId());
        User applyUser = userMapper.selectById(order.getApplyUserId());
        orderDetRes.setAcceptUser(acceptUser);
        orderDetRes.setApplyUser(applyUser);
        return orderDetRes;
    }

    @Override
    public IPage<Order> getOrderList(User user, Page<Order> page) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("apply_user_id", user.getId());
        wrapper.or();
        wrapper.eq("accept_user_id", user.getId());
        wrapper.orderByDesc("start_date_time");
        IPage<Order> orderIPage = orderMapper.selectPage(page, wrapper);
        return orderIPage;
    }
}
