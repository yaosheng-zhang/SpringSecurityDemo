package com.zhangys.photo.security.filter;

import com.zhangys.photo.config.JwtUtils;
import com.zhangys.photo.mapper.MenuMapper;
import com.zhangys.photo.mapper.UserMapper;
import com.zhangys.photo.pojo.User;
import com.zhangys.photo.security.MyUser;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @PROJECT_NAME: photo
 * @DESCRIPTION: jwt过滤器配置
 * @USER: 13905
 * @DATE: 2023/1/10 21:23
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String token=request.getHeader("token");
        //如果没有token 直接放行 （有可能没有登录的情况下）
        if (!StringUtils.hasText(token))
        {

            filterChain.doFilter(request,response);
            // 这里如果不 return，后续过滤器链执行完还会执行剩余代码，后续如果获取到了 token，那么执行后续代码就会有问题
            return;
        }
        // 解析 token
        String userId;
        try {
            final Claims claims = JwtUtils.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token 非法");
        }
        //获取用户信息 （从数据库中获取 可以使用redis来获取）
        final User user = userMapper.selectById(userId);
        List<String> perms = menuMapper.selectPermsByUserId(Integer.valueOf(userId));
        //数据库中没有该用户信息 说明用户未登录
        if(Objects.isNull(user))
        {
            throw new RuntimeException("用户未登录！");
        }
        //将user对象封装成UserDetail对象
        MyUser myUser = new MyUser(user,perms);
        //构造Authentication对象 存入Userdetails 用户信息 授权信息
        //存入到SecurityContextHolder
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(myUser, null, myUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        //放行
        filterChain.doFilter(request,response);


    }
}
