package com.mengzhidu.dream.nuff.remote.rpc.server;

/**
 * ServerConfig的配置项
 */
public class ServerConfigHelper {

    public static ServerConfig fillServerConfig(ServerConfig serverConfig) {
        if (serverConfig == null) {
            serverConfig = new ServerConfig();
        }

        if (serverConfig.getBossThreads() == null) {
            serverConfig.setBossThreads(1);
        }

        if (serverConfig.getPort() == null) {
            serverConfig.setPort(3190);
        }

        if (serverConfig.getWorkerThreads() == null) {
            serverConfig.setWorkerThreads(15);
        }
        return serverConfig;
    }
}
