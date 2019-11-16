package com.iit.expensetracker.Service.Impl;

import com.iit.expensetracker.DAO.TransactionDAO;
import com.iit.expensetracker.DAO.UserDAO;
import com.iit.expensetracker.Dto.TransactionDto;
import com.iit.expensetracker.Model.TransactionModel;
import com.iit.expensetracker.Model.UserModel;
import com.iit.expensetracker.Response.Response;
import com.iit.expensetracker.Service.TransactionService;
import com.iit.expensetracker.enums.ResponseMessage;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionDAO transactionDAO;
    private final UserDAO  userDAO;

    public TransactionServiceImpl(TransactionDAO transactionDAO, UserDAO userDAO) {
        this.transactionDAO = transactionDAO;
        this.userDAO = userDAO;
    }

    @Override
    public Object saveTransaction(String userId, TransactionDto transactionDto) {
        UserModel userModel = userDAO.getUserById(userId);
        if (userModel == null)
            return new Response(ResponseMessage.NO_RECORD, "User Not Found!");
        TransactionModel transactionModel = new TransactionModel();
        UUID uuid = UUID.randomUUID();
        transactionModel.setTransactionId(uuid.toString());
        transactionModel.setUserId(userId);
        transactionModel.setCategoryId(transactionDto.getCategoryId());
        transactionModel.setAmount(transactionDto.getAmount());
        transactionModel.setRemarks(transactionDto.getRemarks());
        Date date = new Date();
        transactionModel.setTimestamp(date);

        transactionDAO.saveTransaction(transactionModel);
        return new Response(ResponseMessage.SUCCESS, transactionModel);

    }

    @Override
    public Object getAllTransactionsByUserId(String userId) {
        UserModel userModel = userDAO.getUserById(userId);
        if (userModel == null)
            return new Response(ResponseMessage.NO_RECORD, "User Not Found!");
        List<TransactionModel> transactionList = transactionDAO.getAllTransactionsByUserId(userId);
        if (transactionList.isEmpty())
            return new Response(ResponseMessage.NO_RECORD, "No Transactions found for this user!");
        return new Response(ResponseMessage.SUCCESS, transactionList);
    }
}
