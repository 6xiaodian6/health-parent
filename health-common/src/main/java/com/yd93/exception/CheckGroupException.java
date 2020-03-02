package com.yd93.exception;

/**
 * @description: 自定义异常类，实现对检查组编码的校验
 * @author: yd93
 * @createTime: 2019-10-12 21:09:13
 **/
public class CheckGroupException extends Exception {
    public CheckGroupException(String message) {
        super(message);
    }
}
