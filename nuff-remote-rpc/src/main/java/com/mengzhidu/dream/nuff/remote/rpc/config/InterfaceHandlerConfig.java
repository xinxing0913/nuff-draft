package com.mengzhidu.dream.nuff.remote.rpc.config;

import java.util.Map;

/**
 * 接口处理器配置项
 */
public class InterfaceHandlerConfig {

    private Map<Class<?>, Integer> threadMap;

    public Map<Class<?>, Integer> getThreadMap() {
        return threadMap;
    }

    public void setThreadMap(Map<Class<?>, Integer> threadMap) {
        this.threadMap = threadMap;
    }
}
