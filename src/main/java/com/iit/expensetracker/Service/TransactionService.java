package com.iit.expensetracker.Service;

import com.iit.expensetracker.Dto.TransactionDto;

public interface TransactionService {
    Object saveTransaction(String userId, TransactionDto transactionDto);
    Object getAllTransactionsByUserId(String userId);
    Object editTransactionById(String id, TransactionDto transactionDto, String user);
}
