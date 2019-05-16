package com.mengzhidu.dream.nuff.enums;

/**
 * Nuff的错误码，这里我们是使用了自增的错误码设计
 */
public enum NuffErrorCode {

    UNKNOWN(-1, "UNKNOWN ERROR"),

    // 启动时错误
    INIT_FAILED(10000, "初始化失败"),

    // 运行时错误
    //

    // 数据解析失败

    //

    ;


    private int code;

    private String desc;

    NuffErrorCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "NuffErrorCode{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                '}';
    }
}
