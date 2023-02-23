package com.zhangys.photo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangys
 * @since 2023-01-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Photo对象", description="")
public class Photo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String photoName;

    private String photoIntro;

    private String photoUrl;

    private String shootingTime;

    private String latitude;

    private String longitude;

    private String address;


}
