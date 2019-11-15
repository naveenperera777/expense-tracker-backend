package com.iit.expensetracker.Response;

import com.iit.expensetracker.enums.ResponseMessage;

public class Response {
    private String title;
    private String message;
    private Object data;

    public Response() {
    }

    public Response(String message) {
        this.message = message;
    }

    public Response(ResponseMessage message) {
        this(message.toString());
    }

    public Response(ResponseMessage message, Object data) {
        this((String)null, message.toString(), data);
    }

    public Response(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public Response(String title, String message, Object data) {
        this.title = title;
        this.message = message;
        this.data = data;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}