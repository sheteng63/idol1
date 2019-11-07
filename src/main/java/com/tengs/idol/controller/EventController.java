package com.tengs.idol.controller;


import com.tengs.idol.core.exception.BzException;
import com.tengs.idol.core.util.JsonUtils;
import com.tengs.idol.entity.Event;
import com.tengs.idol.entity.Order;
import com.tengs.idol.entity.User;
import com.tengs.idol.model.request.CreateOrderReq;
import com.tengs.idol.model.request.EventReq;
import com.tengs.idol.model.response.BaseResponse;
import com.tengs.idol.service.IEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-10-13
 */
@RestController
@RequestMapping("/idol/event")
public class EventController {
    private static Logger logger = LoggerFactory.getLogger(EventController.class);
    @Autowired
    private IEventService iEventService;

    @PostMapping("create")
    public BaseResponse create(@RequestBody EventReq req, HttpServletRequest request) throws BzException {
        logger.info("event/create :{}", JsonUtils.objectToJson(req));
        User user = (User) request.getAttribute("user");
        iEventService.create(req,user);
        BaseResponse response = new BaseResponse();
        return response;
    }

    @PostMapping("getList")
    public BaseResponse getList(@RequestBody HashMap<String, String> map)throws BzException{
        String orderId = map.get("orderId").trim();
        logger.info("event/getList :{}", orderId);
        List<Event> list = iEventService.getList(orderId);
        BaseResponse response = new BaseResponse();
        response.setData(list);
        return response;
    }

    @PostMapping("getEvent")
    public BaseResponse getEvent(@RequestBody HashMap<String, String> map)throws BzException{
        String eventId = map.get("eventId").trim();
        logger.info("event/get :{}", eventId);
        Event event = iEventService.getEvent(eventId);
        BaseResponse response = new BaseResponse();
        response.setData(event);
        return response;
    }

    @PostMapping("update")
    public BaseResponse update(@RequestBody EventReq req, HttpServletRequest request)throws BzException{
        logger.info("event/update :{}", JsonUtils.objectToJson(req));
        User user = (User) request.getAttribute("user");
        iEventService.update(req,user);
        BaseResponse response = new BaseResponse();
        return response;
    }

}
