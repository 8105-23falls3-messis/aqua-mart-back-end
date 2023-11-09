package com.aqua.fall23g1.service;

import java.util.List;

import com.aqua.fall23g1.entity.LoginReq;
import com.aqua.fall23g1.entity.Role;
import com.aqua.fall23g1.entity.TokenHistory;
import com.aqua.fall23g1.entity.User;

public interface UserService {

    int register(User user);

    int countUser(User user);

    User getUserByLoginData(LoginReq loginReq);

    List<Role> getAllRoles();

    // List<Role> getAllRolesByPage(TestReqParam param);

    void addTokenHistory(TokenHistory tokenHistory);

    void removeTokenHistory(String token);

    User getUserById(String userId);

    void updateUser(User user);

    List<Role> getRolesByUser(int userId);
}
