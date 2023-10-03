package com.aqua.fall23g1.utils;

import com.aqua.fall23g1.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class TokenUtil {
    //expire time: 30 min
    private static final long EXPIRE_TIME = 1000 * 60 * 30;
    //salt
    private static final String TOKEN_SECRET = "aqua";

    /**
     * Create token
     *
     * @param user login user entity
     * @return token
     */
    public static String sign(User user) {
        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create().withIssuer("auth0").withClaim("username", user.getFirstName()).withExpiresAt(expiresAt)
                    //encryption algorithm
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(user.getFirstName() + "'s token is " + token);
        return token;
    }

    /**
     * Verify token
     *
     * @param token the token got from request head
     * @return true or false
     */
    public static boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("verify access");
            System.out.println("username: " + jwt.getClaim("username").asString());
            System.out.println("expire time: " + jwt.getExpiresAt());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
