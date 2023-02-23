package com.zhangys.photo.service;

import com.zhangys.photo.pojo.Result;
import com.zhangys.photo.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangys
 * @since 2023-01-08
 */

public interface IUserService extends IService<User> {

    User getByUsername(String username);

    Result register(User user);
}
