package com.aqua.fall23g1.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqua.fall23g1.entity.LoginReq;
import com.aqua.fall23g1.entity.Role;
import com.aqua.fall23g1.entity.TestReqParam;
import com.aqua.fall23g1.entity.User;
import com.aqua.fall23g1.mapper.UserMapper;
import com.aqua.fall23g1.service.UserService;
import com.github.pagehelper.PageHelper;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Role> getAllRoles() {
        return userMapper.getAllRoles();
    }

    @Override
    public int countUser(User user) {
        return userMapper.countUser(user);
    }

    @Override
    public int register(User user) {
        try {
            userMapper.insertUserData(user);
            // register succeed return 1
            return 1;
        } catch (Exception e) {
            // register failed return 0
            return 0;
        }
    }

    @Override
    public User getUserByLoginData(LoginReq loginReq) {
        return userMapper.queryUser(loginReq);
    }

    @Override
    public List<Role> getAllRolesByPage(TestReqParam param) {
        // set paging param before query
        // then we don't need to write limit key word in our query sentences
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<Role> rolesByPaging = userMapper.getAllRoles();
        return rolesByPaging;
    }
}
