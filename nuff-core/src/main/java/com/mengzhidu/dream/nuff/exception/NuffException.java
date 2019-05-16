package com.mengzhidu.dream.nuff.exception;

import com.mengzhidu.dream.nuff.enums.NuffErrorCode;

/**
 * 系统级别的异常
 * NuffErrorCode用来对错误的原因进行归类
 * message用来作为具体的描述
 */
public class NuffException extends Exception {
    private NuffErrorCode errorCode;

    private String message;


    public NuffException(NuffErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public NuffException(NuffErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    @Override
    public String getMessage() {
        if (message == null) {
            return errorCode.toString();
        } else {
            return errorCode.toString() + "\n Detail:" + message;
        }
    }
}
