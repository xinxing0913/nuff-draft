package com.mengzhidu.dream.nuff.demo.common.impl;

import com.mengzhidu.dream.nuff.demo.common.CourseService;

/**
 * Created by xinxing on 2019/5/16
 */
public class CourseServiceImpl implements CourseService {

    @Override
    public String getCourseById(int id) {
        return "来自Nuff的课程" + id;
    }
}
