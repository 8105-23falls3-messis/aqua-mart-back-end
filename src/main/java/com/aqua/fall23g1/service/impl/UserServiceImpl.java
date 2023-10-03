package com.aqua.fall23g1.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqua.fall23g1.entity.LoginReq;
import com.aqua.fall23g1.entity.Role;
import com.aqua.fall23g1.entity.User;
import com.aqua.fall23g1.mapper.UserMapper;
import com.aqua.fall23g1.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Role> getAllRoles() {
        return null;
    }

    @Override
    public int register(User user) {
        try {
            userMapper.insertUserData(user);
            //register succeed return 1
            return 1;
        } catch (Exception e) {
            //register failed return 0
            return 0;
        }
    }

    @Override
    public User getUserByLoginData(LoginReq loginReq) {
        return userMapper.queryUser(loginReq);
    }
}
