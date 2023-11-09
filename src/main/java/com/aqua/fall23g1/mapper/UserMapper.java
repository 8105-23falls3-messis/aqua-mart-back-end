package com.aqua.fall23g1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.aqua.fall23g1.entity.LoginReq;
import com.aqua.fall23g1.entity.Role;
import com.aqua.fall23g1.entity.TokenHistory;
import com.aqua.fall23g1.entity.User;

@Repository
public interface UserMapper {

    int insertUserData(User user);

    int countUser(User user);

    User queryUser(LoginReq loginReq);

    List<Role> getAllRoles();

    int getTokenCount(@Param("token") String token);

    void addTokenHistory(TokenHistory tokenHistory);

    void removeToken(@Param("token") String token);

    User getUserById(@Param("userId") String userId);

    void updateUser(User user);

    void insertUserRole(@Param("userId") int userId, @Param("idRole") int idRole);

    List<Role> getRolesByUserId(int userId);
}
