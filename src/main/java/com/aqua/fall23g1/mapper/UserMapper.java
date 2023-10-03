package com.aqua.fall23g1.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aqua.fall23g1.entity.LoginReq;
import com.aqua.fall23g1.entity.Role;
import com.aqua.fall23g1.entity.User;

@Repository
public interface UserMapper {

    int insertUserData(User user);

    User queryUser(LoginReq loginReq);

    List<Role> getAllRoles();
}
