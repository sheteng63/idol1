package com.tengs.idol.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tengs.idol.controller.UserController;
import com.tengs.idol.core.util.HttpUtil;
import com.tengs.idol.core.util.JwtUtil;
import com.tengs.idol.core.util.UniCodeUtil;
import com.tengs.idol.entity.User;
import com.tengs.idol.mapper.UserMapper;
import com.tengs.idol.model.request.LoginRequest;
import com.tengs.idol.model.response.WxloginRes;
import com.tengs.idol.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-10-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.secret}")
    private String secret;

    @Autowired
    private UserMapper userMapper;

    @Override
    public String login(LoginRequest re) {
        String urls = "https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={code}&grant_type=authorization_code";

        StringBuilder url = new StringBuilder();
        url.append("https://api.weixin.qq.com/sns/jscode2session?appid=");
        url.append(appid);
        url.append("&secret=");
        url.append(secret);
        url.append("&js_code=");
        url.append(re.getCode());
        url.append("&grant_type=authorization_code");
        String userRet = HttpUtil.httpRequest(url.toString(), "GET", null);
        logger.info("-----获取得到的用户信息------" + userRet);
        JSONObject userObj = null;
        try {
            userObj = new JSONObject(userRet);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String openid = userObj.optString("openid");
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("open_id", openid);
        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            user = new User();
            user.setId(UniCodeUtil.createUUID());
            user.setAvatarUrl(re.getAvatarUrl());
            user.setCreateTime(new Date());
            user.setNickName(re.getAnickName());
            user.setOpenId(openid);
            user.setIsActive("0");
            userMapper.insert(user);
        }
        String token = JwtUtil.getToken(openid);
        return "Bearer:" + token;
    }
}
