package com.mengzhidu.dream.nuff.registry;

/**
 * 最终需要存储在注册中心的唯一键
 * 它可以选择加密存储，也可以选择各种各样的方式，但是它应该得到唯一的字符串
 */
public interface Key {

    String getValue();
}
