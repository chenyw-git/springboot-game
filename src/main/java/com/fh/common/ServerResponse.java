package com.fh.common;


public class ServerResponse<T> {

    private int code;

    private String message;

    private T data;

    public static ServerResponse error(ServerEnum responseEnum){
        return new ServerResponse(responseEnum.getCode(),responseEnum.getMessage(),null);
    }

    public static ServerResponse error(){
        ServerEnum serverEnum = ServerEnum.ERROR;
        return new ServerResponse(serverEnum.getCode(),serverEnum.getMessage(),null);
    }
    public static ServerResponse error(String message){
        ServerEnum serverEnum = ServerEnum.ERROR;
        return new ServerResponse(serverEnum.getCode(),serverEnum.getMessage(),null);
    }
    public static ServerResponse success(){
        ServerEnum serverEnum = ServerEnum.SUCCESS;
        return new ServerResponse(serverEnum.getCode(),serverEnum.getMessage(),null);
    }

    public static ServerResponse success(Object data){
        ServerEnum serverEnum = ServerEnum.SUCCESS;
        return new ServerResponse(serverEnum.getCode(),serverEnum.getMessage(),data);
    }

    public ServerResponse(int code, String message,T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public  ServerResponse(){

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

}
