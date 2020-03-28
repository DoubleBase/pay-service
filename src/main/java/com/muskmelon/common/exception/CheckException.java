package com.muskmelon.common.exception;

/**
 * @author muskmelon
 * @description 参数校验异常
 * @date 2020-3-28 23:49
 * @since 1.0
 */
public class CheckException extends RuntimeException {

    public CheckException() {
        super();
    }

    public CheckException(String msg) {
        super(msg);
    }

    public CheckException(String msg, Throwable e) {
        super(msg, e);
    }

}
