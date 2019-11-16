package com.iit.expensetracker.DataMapper;

import com.iit.expensetracker.Dto.TransactionResponseDTObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionResposeDataMapper implements RowMapper<TransactionResponseDTObject> {
    private static final Logger logger = LoggerFactory.getLogger(TransactionResposeDataMapper.class);

    @Override
    public TransactionResponseDTObject mapRow(ResultSet resultSet, int i) throws SQLException {
        TransactionResponseDTObject transactionModel = new TransactionResponseDTObject();
        transactionModel.setUserId(resultSet.getString("userId"));
        transactionModel.setCategoryId(resultSet.getString("categoryId"));
        transactionModel.setTransactionId(resultSet.getString("transactionId"));
        transactionModel.setAmount(resultSet.getDouble("amount"));
        transactionModel.setTimestamp(resultSet.getTimestamp("timestamp"));
        transactionModel.setRemarks(resultSet.getString("remarks"));
        transactionModel.setCategoryName(resultSet.getString("category"));
//        categoryModel.setType(CategoryEnum.valueOf(resultSet.getString("type")));
        transactionModel.setCategoryType(resultSet.getString("type"));
//        transactionModel.getCategoryType(resultSet.getString("type"));
        return transactionModel;
    }
}
