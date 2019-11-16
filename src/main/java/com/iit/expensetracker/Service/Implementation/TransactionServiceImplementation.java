package com.iit.expensetracker.Service.Implementation;

import com.iit.expensetracker.DAO.CategoryDAObject;
import com.iit.expensetracker.DAO.TransactionDAObject;
import com.iit.expensetracker.DAO.UserDAObject;
import com.iit.expensetracker.Dto.TransactionDTObject;
import com.iit.expensetracker.Model.Transaction;
import com.iit.expensetracker.Model.User;
import com.iit.expensetracker.Response.Response;
import com.iit.expensetracker.Service.TransactionService;
import com.iit.expensetracker.enums.ResponseMessage;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImplementation implements TransactionService {
    private final TransactionDAObject transactionDAObject;
    private final UserDAObject userDAObject;
    private final CategoryDAObject categoryDAObject;


    public TransactionServiceImplementation(TransactionDAObject transactionDAObject, UserDAObject userDAObject, CategoryDAObject categoryDAObject) {
        this.transactionDAObject = transactionDAObject;
        this.userDAObject = userDAObject;
        this.categoryDAObject = categoryDAObject;
    }

    @Override
    public Object saveTransaction(String userId, TransactionDTObject transactionDTObject) {
        User user = userDAObject.getUserById(userId);
        if (user == null)
            return new Response(ResponseMessage.NO_RECORD, "User Not Found!");
        Transaction transaction = new Transaction();
        UUID uuid = UUID.randomUUID();
        transaction.setTransactionId(uuid.toString());
        transaction.setUserId(userId);
        transaction.setCategoryId(transactionDTObject.getCategoryId());
        transaction.setAmount(transactionDTObject.getAmount());
        transaction.setRemarks(transactionDTObject.getRemarks());
        Date date = new Date();
        transaction.setTimestamp(date);

        transactionDAObject.saveTransaction(transaction);
        return new Response(ResponseMessage.SUCCESS, transaction);

    }

    @Override
    public Object getAllTransactionsByUserId(String userId) {
        User user = userDAObject.getUserById(userId);
        if (user == null)
            return new Response(ResponseMessage.NO_RECORD, "User Not Found!");
        List transactionList = transactionDAObject.getAllTransactionsByUserId(userId);
        if (transactionList.isEmpty())
            return new Response(ResponseMessage.NO_RECORD, "No Transactions found for this user!");

//        CategoryModel categoryModel = categoryDAO.getCategoryById();
//        for (int i=0; i<transactionList.size(); i++){
//            TransactionResponseDto responseDto = new TransactionResponseDto();
////            responseDto
//        }

        return new Response(ResponseMessage.SUCCESS, transactionList);
    }
}
