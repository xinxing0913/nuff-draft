package com.mengzhidu.dream.nuff.registry;

import java.util.List;

/**
 * 服务的注册
 * 它提供的方法有:
 * 1.
 */
public interface Registry {

    void registry(Key key, List<Address> addresses);

    void renew(Key key);

    void destroy(Key key, List<Address> addresses);
}
