package com.das.god_base.network;

public class BaseRequestEntity <T>{

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}