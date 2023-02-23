package com.zhangys.photo.security;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.zhangys.photo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @PROJECT_NAME: generator
 * @DESCRIPTION: 重写UserDetail类
 * @USER: 13905
 * @DATE: 2023/1/9 11:47
 */
//自己封裝从数据库查找到的User 和 Authorities 的信息
public class MyUser implements UserDetails {

    private com.zhangys.photo.pojo.User user;

    private List<String> permissions;


    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;


    public MyUser(User user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    public MyUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

//        if (authorities!=null)
//        {
//            return authorities;
//        }
//        authorities=new ArrayList<>();
//        //把permission中的String类的权限信息封装成SimpleGrantedAuthority 对象 因为最后要返回得失GrantedAuthority对象
//        //方法一
////        for (String permission : permissions) {
////            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
////            authorities.add(authority);
////        }
//        //方法二 函数式编程
        authorities=permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return authorities;



    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }



    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
