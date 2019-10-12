package com.tengs.idol.service.impl;

import com.tengs.idol.entity.User;
import com.tengs.idol.mapper.UserMapper;
import com.tengs.idol.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-10-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
