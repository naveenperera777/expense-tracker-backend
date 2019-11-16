package com.iit.expensetracker.Service;

import com.iit.expensetracker.Dto.TransactionDTObject;

public interface TransactionService {
    Object saveTransaction(String userId, TransactionDTObject transactionDTObject);
    Object getAllTransactionsByUserId(String userId);
}
