package com.xihu.conference.xihu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    private Integer code;

    private String message;

    private T data;

    //增删改 成功响应
    public static Result success() {
        return new Result(200, "success", null);
    }

    //查询成功响应
    public static <T> Result<T> success(T data) {
        return new Result(200, "success", data);
    }

    //失效响应
    public static Result error() {
        return new Result(400, "error", null);
    }

    public static Result error(String msg) {
        return new Result(400, msg, null);
    }
}
