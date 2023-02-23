package com.zhangys.photo.service;

import java.util.List;

/**
 * @PROJECT_NAME: photo
 * @DESCRIPTION: menuService
 * @USER: 13905
 * @DATE: 2023/1/11 13:32
 */
public interface IMenuService {
    List<String> selectPermsByUserId(Integer id);
}
