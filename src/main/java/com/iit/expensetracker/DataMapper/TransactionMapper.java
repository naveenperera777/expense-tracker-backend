package com.iit.expensetracker.DataMapper;

import com.iit.expensetracker.Model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionMapper implements RowMapper<Transaction> {

    private static final Logger logger = LoggerFactory.getLogger(TransactionMapper.class);

    @Override
    public Transaction mapRow(ResultSet resultSet, int i) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setUserId(resultSet.getString("userId"));
        transaction.setTransactionId(resultSet.getString("transactionId"));
        transaction.setCategoryId(resultSet.getString("categoryId"));
        transaction.setAmount(resultSet.getDouble("amount"));
        transaction.setRemarks(resultSet.getString("remarks"));
        transaction.setTimestamp(resultSet.getTimestamp("timestamp"));

        return transaction;
    }
}
