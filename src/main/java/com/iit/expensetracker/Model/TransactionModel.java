package com.iit.expensetracker.Model;

import java.util.Date;

public class TransactionModel {
    private String transactionId;
    private String categoryId;
    private double amount;
    private Date timestamp;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    private String remarks;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "TransactionModel{" +
                "transactionId='" + transactionId + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
