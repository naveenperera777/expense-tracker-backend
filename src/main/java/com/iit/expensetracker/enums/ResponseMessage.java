package com.iit.expensetracker.enums;

public enum ResponseMessage {
    SUCCESS("Success"),
    NO_RECORD("No record"),
    NO_VAULT_RECORD("No vault record"),
    NO_ACCESS("You don't have access"),
    RESOURCE_NOT_FOUND("Resource not found"),
    PENDING_GROUP("Pending group"),
    DUPLICATE("Duplicate key error"),
    Time_LIMIT_EXCEEDED("Time limit exceeded"),
    SWAP_MODE("Requested user already in swap mode"),
    ERROR("Error"),
    INSOLAR_ERROR("Error from insolar"),
    USER_LIST_MISMATCHED("User list mismatched"),
    PENDING_USER("Pending user"),
    REJECTED_USER("Rejected user"),
    DATA_FETCHING("Please wait... While we fetch data from ledger."),
    BAD_REQUEST("Bad request");

    private String message;

    private ResponseMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return this.message;
    }
}
