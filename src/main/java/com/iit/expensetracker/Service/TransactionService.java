package com.iit.expensetracker.Service;

import com.iit.expensetracker.Dto.TransactionDto;

public interface TransactionService {
    Object saveTransaction(TransactionDto transactionDto);
}
