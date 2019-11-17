package com.iit.expensetracker.Service.Impl;

import com.iit.expensetracker.DAO.CategoryDAO;
import com.iit.expensetracker.DAO.TransactionDAO;
import com.iit.expensetracker.DAO.UserDAO;
import com.iit.expensetracker.Dto.TransactionDto;
import com.iit.expensetracker.Dto.TransactionResponseDto;
import com.iit.expensetracker.Model.CategoryModel;
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
    private final CategoryDAO categoryDAO;


    public TransactionServiceImpl(TransactionDAO transactionDAO, UserDAO userDAO, CategoryDAO categoryDAO) {
        this.transactionDAO = transactionDAO;
        this.userDAO = userDAO;
        this.categoryDAO = categoryDAO;
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
        List transactionList = transactionDAO.getAllTransactionsByUserId(userId);
        if (transactionList.isEmpty())
            return new Response(ResponseMessage.NO_RECORD, "No Transactions found for this user!");

        return new Response(ResponseMessage.SUCCESS, transactionList);
    }

    @Override
    public Object editTransactionById(String id, TransactionDto transactionDto, String userId) {
       TransactionModel transactionModel = transactionDAO.getTransactionById(id);
       if (transactionModel == null)
           return new Response(ResponseMessage.NO_RECORD, "No such Transaction found");
       TransactionModel editTransaction = new TransactionModel();
       editTransaction.setTransactionId(id);
       editTransaction.setCategoryId(transactionDto.getCategoryId());
       editTransaction.setUserId(userId);
       editTransaction.setAmount(transactionDto.getAmount());
       editTransaction.setRemarks(transactionDto.getRemarks());
        Date date = new Date();
       editTransaction.setTimestamp(date);
       transactionDAO.editTransactionById(editTransaction);
       return new Response(ResponseMessage.SUCCESS, transactionDto);
    }

    @Override
    public Object getTransactionsByMonth(String month, String userId) {
        List<TransactionModel> transactionModelList = transactionDAO.getTransactionsByMonth(month,userId);
        if (transactionModelList.isEmpty())
            return new Response(ResponseMessage.NO_RECORD, "No Transactions made during inquired period");
        return new Response(ResponseMessage.SUCCESS, transactionModelList);
    }
}
