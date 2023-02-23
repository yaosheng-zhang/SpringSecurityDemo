package com.zhangys.photo.security;

import com.zhangys.photo.pojo.User;
import com.zhangys.photo.service.IMenuService;
import com.zhangys.photo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @PROJECT_NAME: generator
 * @DESCRIPTION: 重写Security的UserDetailSevice 从数据库中对比
 * @USER: 13905
 * @DATE: 2023/1/9 11:44
 */
@Service
public class MyUserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private IUserService userService;
    @Autowired
    private IMenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
       final User user = userService.getByUsername(username);

        //如果没有查询到用户抛出异常
        if (Objects.isNull(user))
        {
            throw  new UsernameNotFoundException("用户名或密码错误");
        }
        //查询用户权限
        List<String> authorities = menuService.selectPermsByUserId(user.getId());
        return new MyUser(user,authorities);
    }
}
