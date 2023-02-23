package com.zhangys.photo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhangys.photo.pojo.Result;
import com.zhangys.photo.pojo.User;
import com.zhangys.photo.mapper.UserMapper;
import com.zhangys.photo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangys
 * @since 2023-01-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getByUsername(String username) {
        LambdaQueryWrapper<User> queryWraper = new LambdaQueryWrapper<>();
        queryWraper.eq(User::getUsername,username);
        return userMapper.selectOne(queryWraper);
    }

    @Override
    public Result register(User user) {
        String password = user.getPassword();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(password);
        user.setPassword(encode);

        try {
            userMapper.insert(user);
        } catch (Exception e) {
            throw  new RuntimeException("注册失败!");
        }
        return new Result(200,"注册成功");
    }


}
