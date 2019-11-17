package com.iit.expensetracker.Model;

import java.util.Date;

public class Transaction {
    private String transaction_id;
    private String category_id;
    private String user_id;
    private double transaction_amount;
    private Date transaction_time;
    private String transaction_notes;

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public double getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(double transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public Date getTransaction_time() {
        return transaction_time;
    }

    public void setTransaction_time(Date transaction_time) {
        this.transaction_time = transaction_time;
    }

    public String getTransaction_notes() {
        return transaction_notes;
    }

    public void setTransaction_notes(String transaction_notes) {
        this.transaction_notes = transaction_notes;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transaction_id='" + transaction_id + '\'' +
                ", category_id='" + category_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", transaction_amount=" + transaction_amount +
                ", transaction_time=" + transaction_time +
                ", transaction_notes='" + transaction_notes + '\'' +
                '}';
    }
}
