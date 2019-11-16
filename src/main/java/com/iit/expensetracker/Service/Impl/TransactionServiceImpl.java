package com.iit.expensetracker.Service.Impl;

import com.iit.expensetracker.DAO.TransactionDAO;
import com.iit.expensetracker.Dto.TransactionDto;
import com.iit.expensetracker.Model.TransactionModel;
import com.iit.expensetracker.Response.Response;
import com.iit.expensetracker.Service.TransactionService;
import com.iit.expensetracker.enums.ResponseMessage;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionDAO transactionDAO;

    public TransactionServiceImpl(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    @Override
    public Object saveTransaction(String userId, TransactionDto transactionDto) {
        TransactionModel transactionModel = new TransactionModel();
        UUID uuid = UUID.randomUUID();
        transactionModel.setTransactionId(uuid.toString());
        transactionModel.setCategoryId(transactionDto.getCategoryId());
        transactionModel.setAmount(transactionDto.getAmount());
        transactionModel.setRemarks(transactionDto.getRemarks());
        Date date = new Date();
        transactionModel.setTimestamp(date);

        transactionDAO.saveTransaction(transactionModel);
        return new Response(ResponseMessage.SUCCESS, transactionModel);

    }
}
