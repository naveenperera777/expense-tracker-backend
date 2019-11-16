package com.iit.expensetracker.DataMapper;

import com.iit.expensetracker.Model.TransactionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDataMapper implements RowMapper<TransactionModel> {

    private static final Logger logger = LoggerFactory.getLogger(TransactionDataMapper.class);

    @Override
    public TransactionModel mapRow(ResultSet resultSet, int i) throws SQLException {
        TransactionModel transactionModel = new TransactionModel();
        transactionModel.setUserId(resultSet.getString("userId"));
        transactionModel.setTransactionId(resultSet.getString("transactionId"));
        transactionModel.setCategoryId(resultSet.getString("categoryId"));
        transactionModel.setAmount(resultSet.getDouble("amount"));
        transactionModel.setRemarks(resultSet.getString("remarks"));
        transactionModel.setTimestamp(resultSet.getTimestamp("timestamp"));

        return transactionModel;
    }
}
