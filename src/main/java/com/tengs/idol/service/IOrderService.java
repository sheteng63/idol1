package com.tengs.idol.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tengs.idol.core.exception.BzException;
import com.tengs.idol.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tengs.idol.entity.User;
import com.tengs.idol.model.request.CreateOrderReq;
import com.tengs.idol.model.response.OrderDetRes;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2019-10-13
 */
public interface IOrderService extends IService<Order> {
    Order create(CreateOrderReq req, User user);

    OrderDetRes getOrder(String orderId);

    IPage<Order> getOrderList(User user, Page<Order> page);

    void accept(String orderId, User user,String status) throws BzException;
}
