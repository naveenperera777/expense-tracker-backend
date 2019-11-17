package com.iit.expensetracker.Dto;

public class TransactionDTObject {

    private String category_id;
    private double transaction_amount;
    private String transaction_notes;


    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public double getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(double transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public String getTransaction_notes() {
        return transaction_notes;
    }

    public void setTransaction_notes(String transaction_notes) {
        this.transaction_notes = transaction_notes;
    }




}
