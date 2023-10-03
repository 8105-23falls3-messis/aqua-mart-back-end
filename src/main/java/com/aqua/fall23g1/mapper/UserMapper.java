package com.aqua.fall23g1.mapper;

import com.aqua.fall23g1.entity.LoginReq;
import com.aqua.fall23g1.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    int insertUserData(User user);

    User queryUser(LoginReq loginReq);
}
