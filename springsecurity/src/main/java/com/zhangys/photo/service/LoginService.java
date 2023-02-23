package com.zhangys.photo.service;

import com.zhangys.photo.pojo.Result;
import com.zhangys.photo.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @PROJECT_NAME: generator
 * @DESCRIPTION: 登录操作接口
 * @USER: 13905
 * @DATE: 2023/1/9 11:22
 */

public interface LoginService {
    Result login(User user);
}
