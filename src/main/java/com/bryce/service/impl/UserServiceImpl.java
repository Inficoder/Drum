package com.bryce.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bryce.entity.User;
import com.bryce.mapper.UserMapper;
import com.bryce.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description
 * @Author Bryce
 * @Date 2020-07-20 13:19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
