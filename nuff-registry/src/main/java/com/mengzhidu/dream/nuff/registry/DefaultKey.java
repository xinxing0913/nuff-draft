package com.mengzhidu.dream.nuff.registry;

/**
 * 默认的Key实现
 * 如何来实现数据的互通？
 * 默认是一个类作为一个key，当然也可能是一个接口作为一个key，还可以是一个版本作为一个key
 * 这里是按照比较粗的粒度来实现的
 */
public class DefaultKey implements Key {

    private Class clazz;

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public String getValue() {
        return clazz.getCanonicalName();
    }
}
