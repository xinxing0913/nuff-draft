package com.mengzhidu.dream.nuff.registry;

import java.util.List;

/**
 * 服务发现
 */
public interface Discovery {

    List<Address> find(Key key);
}
