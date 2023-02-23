package com.zhangys.photo.service.impl;

import com.zhangys.photo.config.JwtTokenUtil;
import com.zhangys.photo.config.JwtUtils;
import com.zhangys.photo.pojo.Result;
import com.zhangys.photo.pojo.User;
import com.zhangys.photo.security.MyUser;
import com.zhangys.photo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * @PROJECT_NAME: generator
 * @DESCRIPTION: 登录功能实现类
 * @USER: 13905
 * @DATE: 2023/1/9 11:23
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public Result login(User user) {
        //通过Authentication 进行登录认证
            //1.将传来的用户密码 封装成 Authentication对象
        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user
                .getPassword());
        // 该方法会调用自定义 MyUserDetailsServerImpl 中的 loadUserByUsername 方法，把数据库查到的用户名密码和传入的进行对比
        // 以及将查出来的权限信息再封装到 Authentication 对象
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        //如果认证失败
        if (Objects.isNull(authenticate))
        {
            throw  new RuntimeException("登录失败");
        }
        //认证通过 利用用户ID生成jwt
        final MyUser principal = (MyUser)authenticate.getPrincipal();
        final String userId = principal.getUser().getId().toString();
        //将token返回给前端
        String token = JwtUtils.createJWT(userId);
        final HashMap<String, String> rs = new HashMap<>();
        rs.put("token",token);
        return  new Result(200,"登录成功",rs);
    }
}
