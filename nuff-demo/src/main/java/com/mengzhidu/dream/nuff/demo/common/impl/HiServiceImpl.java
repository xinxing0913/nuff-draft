package com.mengzhidu.dream.nuff.demo.common.impl;

import com.mengzhidu.dream.nuff.demo.common.HiService;

/**
 * Created by xinxing on 2018/12/17
 */
public class HiServiceImpl implements HiService {

    @Override
    public String hello(String name) {
        return "nuff温馨提示:  hello " + name;
    }
}
