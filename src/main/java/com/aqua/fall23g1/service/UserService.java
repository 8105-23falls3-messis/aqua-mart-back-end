package com.aqua.fall23g1.service;

import com.aqua.fall23g1.entity.LoginReq;
import com.aqua.fall23g1.entity.User;

public interface UserService {

    int register(User user);

    User getUserByLoginData(LoginReq loginReq);

}
