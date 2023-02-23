package com.zhangys.photo.service.impl;

import com.zhangys.photo.pojo.Photo;
import com.zhangys.photo.mapper.PhotoMapper;
import com.zhangys.photo.service.IPhotoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class PhotoServiceImpl extends ServiceImpl<PhotoMapper, Photo> implements IPhotoService {

}
