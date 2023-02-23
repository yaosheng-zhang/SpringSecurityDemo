package com.zhangys.photo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PROJECT_NAME: generator
 * @DESCRIPTION: 结果类
 * @USER: 13905
 * @DATE: 2023/1/8 15:17
 */
@Data
@NoArgsConstructor

public class Result {
    private int code;
    private String message;
    private Object object;

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(int code, String message, Object object) {
        this.code = code;
        this.message = message;
        this.object = object;
    }
    public static Result success(String message){
        return new Result(200,message,null);
    }

    public static Result success(String message,Object obj){
        return new Result(200,message,obj);
    }

    public static Result error(String message){
        return new Result(500,message,null);
    }

    public static Result error(String message,Object obj){
        return new Result(500,message,obj);
    }
}
