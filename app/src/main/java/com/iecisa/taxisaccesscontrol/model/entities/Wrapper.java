package com.iecisa.taxisaccesscontrol.model.entities;

/**
 * @author Olavera
 */
public class Wrapper<T> {

    private int code;
    private String message;
    private T result;

    public Wrapper(){

    }

    public Wrapper(int code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isSuccessfully(){
        return code==0;
    }
}
