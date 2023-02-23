package com.zhangys.photo.config;

import com.zhangys.photo.mapper.MenuMapper;
import com.zhangys.photo.mapper.UserMapper;
import com.zhangys.photo.pojo.User;
import com.zhangys.photo.security.MyUser;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @PROJECT_NAME: photo
 * @DESCRIPTION: JWT测试类
 * @USER: 13905
 * @DATE: 2023/1/9 16:04
 */
public class utils {


    @Test
    void testJwtToken() throws Exception{
        final String jwtToken = JwtUtils.createJWT("123456");
        System.out.println(jwtToken);

        final Claims claims = JwtUtils.parseJWT(jwtToken);
        final String subject = claims.getSubject();
        System.out.println(subject);
    }



}
