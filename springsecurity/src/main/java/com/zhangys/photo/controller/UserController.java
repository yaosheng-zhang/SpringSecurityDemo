package com.zhangys.photo.controller;


import com.zhangys.photo.pojo.Result;
import com.zhangys.photo.pojo.User;
import com.zhangys.photo.service.IUserService;
import com.zhangys.photo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangys
 * @since 2023-01-08
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        Result login = loginService.login(user);
        return login;
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user)
    {
        Result register=iUserService.register(user);
        return register;
    }
    @PostMapping("/test")
    @PreAuthorize("hasAuthority('add_user')")
    public Result hello(){
        return new Result(200,"成功","hello");
    }


}
