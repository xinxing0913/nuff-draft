package com.mengzhidu.dream.nuff.registry;

import java.util.Map;

/**
 * 用来表示一个应用，或者叫做一个服务
 */
public class App {

    // 应用名称
    private String appName;

    // 扩展信息
    // 比如想扩展 author: xxx,xxx
    //
    private Map<String, Object> extInfo;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Map<String, Object> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, Object> extInfo) {
        this.extInfo = extInfo;
    }
}
