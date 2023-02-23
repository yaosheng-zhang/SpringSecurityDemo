package com.zhangys.photo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhangys.photo.mapper")
public class PhotoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotoApplication.class, args);
    }

}
