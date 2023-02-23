package com.zhangys.photo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangys.photo.pojo.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(Integer id);

}
