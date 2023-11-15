package com.aqua.fall23g1.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqua.fall23g1.entity.Country;
import com.aqua.fall23g1.entity.LoginReq;
import com.aqua.fall23g1.entity.Province;
import com.aqua.fall23g1.entity.Role;
import com.aqua.fall23g1.entity.TokenHistory;
import com.aqua.fall23g1.entity.User;
import com.aqua.fall23g1.mapper.UserMapper;
import com.aqua.fall23g1.service.UserService;

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
            int userId = userMapper.insertUserData(user);
            userMapper.insertUserRole(userId, user.getIdRole());
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

    // @Override
    // public List<Role> getAllRolesByPage(TestReqParam param) {
    // // set paging param before query
    // // then we don't need to write limit key word in our query sentences
    // PageHelper.startPage(param.getPageNum(), param.getPageSize());
    // List<Role> rolesByPaging = userMapper.getAllRoles();
    // return rolesByPaging;
    // }

    @Override
    public void addTokenHistory(TokenHistory tokenHistory) {
        userMapper.addTokenHistory(tokenHistory);
    }

    @Override
    public void removeTokenHistory(String token) {
        userMapper.removeToken(token);
    }

    @Override
    public User getUserById(String userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public List<Role> getRolesByUser(int userId) {
        return userMapper.getRolesByUserId(userId);
    }

	@Override
	public List<Country> getCountries() {
		return userMapper.getCountries();
	}

	@Override
	public List<Province> getProvinces() {
		return userMapper.getProvinces();
	}
}
