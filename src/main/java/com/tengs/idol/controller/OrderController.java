package com.tengs.idol.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.tengs.idol.core.exception.BzException;
import com.tengs.idol.core.util.JsonUtils;
import com.tengs.idol.entity.Order;
import com.tengs.idol.entity.User;
import com.tengs.idol.model.request.CreateOrderReq;
import com.tengs.idol.model.response.BaseResponse;
import com.tengs.idol.model.response.LoginRes;
import com.tengs.idol.model.response.OrderDetRes;
import com.tengs.idol.service.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-10-13
 */
@RestController
@RequestMapping("/idol/order")
public class OrderController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IOrderService iOrderService;


    @PostMapping("create")
    public BaseResponse create(@RequestBody CreateOrderReq req, HttpServletRequest request) {
        logger.info("order/create :{}", JsonUtils.objectToJson(req));
        User user = (User) request.getAttribute("user");
        Order order = iOrderService.create(req, user);
        BaseResponse response = new BaseResponse();
        response.setData(order);
        return response;
    }

    @PostMapping("getOrder")
    public BaseResponse getOrder(@RequestBody HashMap<String, String> map) {
        String orderId = map.get("orderId").trim();
        OrderDetRes order = iOrderService.getOrder(orderId);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(order);
        return baseResponse;
    }

    @PostMapping("getList")
    public BaseResponse getList(@RequestBody HashMap<String, String> map, HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        logger.info("order/create :{}", JsonUtils.objectToJson(user));
        String page = map.get("page");
        String size = map.get("size");
        Page<Order> pageOrder = new Page<>(Long.parseLong(page), Long.parseLong(size));
        IPage<Order> list = iOrderService.getOrderList(user, pageOrder);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(list.getRecords());
        return baseResponse;
    }

    @PostMapping("accept")
    public BaseResponse accept(@RequestBody HashMap<String, String> map, HttpServletRequest request) throws BzException {
        User user = (User) request.getAttribute("user");
        String orderId = map.get("orderId").trim();
        String status = map.get("status").trim();
        logger.info("order/accept user :{}", JsonUtils.objectToJson(user));
        iOrderService.accept(orderId,user,status);
        BaseResponse baseResponse = new BaseResponse();
        return baseResponse;
    }

}
