package com.iecisa.model.entities;

/**
 * @author darevalo
 */
public abstract class Response {

    private int code;
    private String message;

    protected Response() {
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

    public boolean isSuccessful(){
        return this.code==0;
    }
}
