package com.zhangyfvir.jarticle.common.entity;

import lombok.Data;

/**
 * 响应结果
 * 实体类
 *
 * @param <T>
 */
@Data
public class Result<T> {
    int code;
    T data;
    String message;


    public Result() {
    }


    public Result(int code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.message = msg;
        this.data = data;
    }

    /**
     * 成功返回，code=200, message=ok, data 自定义
     *
     * @param m   data 字段
     * @param <M> 类型
     * @return
     */
    public static <M> Result<M> success(M m) {
        Result<M> msgObj = new Result<M>();
        msgObj.code = 200;
        msgObj.message = "ok";
        msgObj.data = m;
        return msgObj;
    }

    public static Result success() {
        Result msgObj = new Result();
        msgObj.code = 200;
        msgObj.message = "ok";
        msgObj.data = null;
        return msgObj;
    }

    public static Result<String> error(int code, String msg) {
        return new Result<String>(code, msg, "");
    }

    public static Result<String> error(int code, Exception ex) {
        return new Result<String>(code, ex.getMessage(), "");
    }
}


