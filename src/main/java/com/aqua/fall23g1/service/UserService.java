package com.aqua.fall23g1.service;

import java.util.List;

import com.aqua.fall23g1.entity.*;

public interface UserService {

    int register(User user);

    int countUser(User user);

    User getUserByLoginData(LoginReq loginReq);

    List<Role> getAllRoles();

    List<Role> getAllRolesByPage(TestReqParam param);

    void addTokenHistory(TokenHistory tokenHistory);

    void removeTokenHistory(String token);

}
