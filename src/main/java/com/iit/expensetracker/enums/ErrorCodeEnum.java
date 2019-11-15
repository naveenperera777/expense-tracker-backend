package com.iit.expensetracker.enums;

public enum ErrorCodeEnum {
    ERROR("0000"),
    PENDING_USER("0001"),
    PENDING_GROUP("0002"),
    REJECTED_USER("0003");

    private String value;

    private ErrorCodeEnum(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
