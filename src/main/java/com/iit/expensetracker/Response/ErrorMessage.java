package com.iit.expensetracker.Response;

import com.iit.expensetracker.enums.ErrorCodeEnum;
import com.iit.expensetracker.enums.ResponseMessage;
import org.springframework.http.HttpStatus;

import java.util.Date;

public class ErrorMessage {
    private String message;
    private String title;
    private HttpStatus error;
    private int status;
    private String code;
    private String timestamp;
    private Object data;

    public ErrorMessage() {
    }

    public ErrorMessage(String message, HttpStatus error, String code) {
        this.message = message;
        this.error = error;
        this.code = code;
    }

    public ErrorMessage(String message, String title, Integer statusCode, String code, String timestamp) {
        this.message = message;
        this.title = title;
        this.status = statusCode;
        this.error = HttpStatus.valueOf(statusCode);
        this.code = code;
        this.timestamp = timestamp;
    }

    public ErrorMessage(String message, HttpStatus error) {
        this(message, error, ErrorCodeEnum.ERROR.toString());
    }

    public ErrorMessage(ResponseMessage responseMessage, HttpStatus error) {
        this(responseMessage.toString(), error, ErrorCodeEnum.ERROR.toString());
    }

    public ErrorMessage(ResponseMessage responseMessage, HttpStatus error, ErrorCodeEnum code) {
        this(responseMessage.toString(), error, code.toString());
    }

    public ErrorMessage(ResponseMessage responseMessage, HttpStatus error, Object data) {
        this(responseMessage.toString(), error, ErrorCodeEnum.ERROR.toString());
        this.data = data;
    }

    public ErrorMessage(String errorMessage, HttpStatus error, Object data) {
        this(errorMessage, error, ErrorCodeEnum.ERROR.toString());
        this.data = data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HttpStatus getError() {
        return this.error;
    }

    public int getStatus() {
        return this.error.value();
    }

    public void setStatus(int status) {
        this.status = status;
        this.error = HttpStatus.valueOf(status);
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTimestamp() {
        return (new Date()).toString();
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}