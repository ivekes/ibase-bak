package com.wzk.v1;

public class RequestData {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RequestData{" +
                "name='" + name + '\'' +
                '}';
    }
}
