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

        Transaction transaction = copy(transactionDTObject, userId);

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

//    @Override
//    public Object transactionEditById(String id, TransactionDto transactionDto, String userId) {
//        TransactionModel transactionModel = transactionDAO.getTransactionById(id);
//        if (transactionModel == null)
//            return new Response(ResponseMessage.NO_RECORD, "No such Transaction found");
//        TransactionModel editTransaction = new TransactionModel();
//        editTransaction.setTransactionId(id);
//        editTransaction.setCategoryId(transactionDto.getCategoryId());
//        editTransaction.setUserId(userId);
//        editTransaction.setAmount(transactionDto.getAmount());
//        editTransaction.setRemarks(transactionDto.getRemarks());
//        Date date = new Date();
//        editTransaction.setTimestamp(date);
//        transactionDAO.editTransactionById(editTransaction);
//        return new Response(ResponseMessage.SUCCESS, transactionDto);
//    }
//
//    @Override
//    public Object retrieveAllTransactionsByMonth(String month, String userId) {
//        List<TransactionResponseDto> transactionModelList = transactionDAO.getTransactionsByMonth(month,userId);
//        if (transactionModelList.isEmpty())
//            return new Response(ResponseMessage.NO_RECORD, null);
//        return new Response(ResponseMessage.SUCCESS, transactionModelList);
//    }
//
//    @Override
//    public Object transactionDeleteById(String transactionId) {
//        TransactionModel transactionModel = transactionDAO.getTransactionById(transactionId);
//        if (transactionModel == null)
//            return new Response(ResponseMessage.NO_RECORD, "No such Transaction found");
//        transactionDAO.deleteTransactionById(transactionId);
//        return new Response(ResponseMessage.SUCCESS, "User deleted Successfully");
//    }

    public Transaction copy(TransactionDTObject transactionDTObject, String userId){
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        Transaction transaction = new Transaction();
        transaction.setTransaction_id(uuid);
        transaction.setUser_id(userId);
        transaction.setCategory_id(transactionDTObject.getCategory_id());
        transaction.setTransaction_amount(transactionDTObject.getTransaction_amount());
        transaction.setTransaction_notes(transactionDTObject.getTransaction_notes());
        Date date = new Date();
        transaction.setTransaction_time(date);

        return transaction;
    }
}
