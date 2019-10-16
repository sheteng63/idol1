package com.tengs.idol.controller;

import com.tengs.idol.core.util.JsonUtils;
import com.tengs.idol.model.request.LoginRequest;
import com.tengs.idol.model.response.BaseResponse;
import com.tengs.idol.model.response.LoginRes;
import com.tengs.idol.service.IUserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-10-13
 */
@RestController
@RequestMapping("/idol/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private IUserService iUserService;

    @PostMapping("login")
    public BaseResponse login(@RequestBody LoginRequest request) {
        logger.info("login :{}", JsonUtils.objectToJson(request));
        String login = iUserService.login(request);
        LoginRes loginRes = new LoginRes();
        loginRes.setToken(login);
        return loginRes;

    }
}
