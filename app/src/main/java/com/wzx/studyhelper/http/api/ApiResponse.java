package com.wzx.studyhelper.http.api;


/**
 * 服务器返回的json基类
 */
public class ApiResponse<T>{
    private int code;
    private T data;
    private String msg;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getResponse() {
        return data;
    }

    public void setResponse(T response) {
        this.data = response;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                ", code=" + code +
                ", response=" + data +
                ", message='" + msg + '\'' +
                '}';
    }
}
