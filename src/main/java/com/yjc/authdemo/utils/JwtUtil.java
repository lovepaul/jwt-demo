package com.yjc.authdemo.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yjc.authdemo.po.MyException;
import com.yjc.authdemo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author admin
 */
@Slf4j
@Component
public class JwtUtil {

    private static String password = "123456";

    private JwtUtil() {
    }

    /**
     * 获取token
     *
     * @param u user
     * @return token
     */
    public static String getToken(User u) {
        Date date = new Date();
        //默认令牌过期时间7天
        DateTime offsetDay = DateUtil.offsetDay(date, 10);
        JWTCreator.Builder builder = JWT.create();
        return builder.withClaim("userId", u.getId())
                .withClaim("username", u.getUsername())
                .withClaim("phone", u.getPhone())
                .withExpiresAt(offsetDay)
                .withIssuedAt(date)
                .sign(Algorithm.HMAC256(password));
    }

    /*** 验证token合法性 成功返回token
     */
    public static DecodedJWT verify(String token) throws MyException {
        if (StringUtils.isEmpty(token)) {
            throw new MyException("token不能为空");
        }
        //获取登录用户真正的密码假如数据库查出来的是123456
        JWTVerifier build = JWT.require(Algorithm.HMAC256(password)).build();
        Algorithm algorithm = Algorithm.HMAC256(password);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT verifyResult = verifier.verify(token);
        Integer userId = verifyResult.getClaim("userId").asInt();
        String username = verifyResult.getClaim("username").asString();
        String phone = verifyResult.getClaim("phone").asString();
        log.info("{}-{}-{}", username, userId, phone);

        ArrayList<Object> objects = new ArrayList<>();
        Object o = objects.get(0);
        String name = o.getClass().getName();
        log.info(name);
        return build.verify(token);
    }

}

