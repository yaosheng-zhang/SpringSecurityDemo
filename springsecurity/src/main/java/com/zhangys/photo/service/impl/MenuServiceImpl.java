package com.zhangys.photo.service.impl;

import com.zhangys.photo.mapper.MenuMapper;
import com.zhangys.photo.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PROJECT_NAME: photo
 * @DESCRIPTION: shixialei
 * @USER: 13905
 * @DATE: 2023/1/11 13:34
 */
@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public List<String> selectPermsByUserId(Integer id) {
        List<String> authorties = menuMapper.selectPermsByUserId(1);
        return authorties;
    }
}
