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
        transaction.setUser_id(resultSet.getString("user_id"));
        transaction.setTransaction_id(resultSet.getString("transaction_id"));
        transaction.setCategory_id(resultSet.getString("category_id"));
        transaction.setTransaction_amount(resultSet.getDouble("transaction_amount"));
        transaction.setTransaction_notes(resultSet.getString("transaction_notes"));
        transaction.setTransaction_time(resultSet.getTimestamp("transaction_time"));

        return transaction;
    }
}
