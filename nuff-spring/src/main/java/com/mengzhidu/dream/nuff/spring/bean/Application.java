package com.mengzhidu.dream.nuff.spring.bean;

/**
 * Created by xinxing on 2018/12/20
 */
public class Application {

    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
