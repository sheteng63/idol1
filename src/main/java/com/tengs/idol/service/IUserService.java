package com.tengs.idol.service;

import com.tengs.idol.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tengs.idol.model.request.LoginRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2019-10-13
 */
public interface IUserService extends IService<User> {
    String login(LoginRequest re);
}
