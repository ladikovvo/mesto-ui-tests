package com.company.mesto.api.models;

public class ApiResponse<T> {
    private T data;

    public ApiResponse() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
