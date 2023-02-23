package com.zhangys.photo.Mapper;

import com.zhangys.photo.mapper.MenuMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @PROJECT_NAME: photo
 * @DESCRIPTION: dd
 * @USER: 13905
 * @DATE: 2023/1/11 13:30
 */
@SpringBootTest
public class testMenuMapper {
    @Autowired
    private MenuMapper menuMapper;
    @Test
    void menuMapper(){
        List<String> strings = menuMapper.selectPermsByUserId(1);
        System.out.println(strings);


    }

}
