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
        TransactionResponseDTObject transactionResponseDTObject = new TransactionResponseDTObject();
        transactionResponseDTObject.setUserId(resultSet.getString("user_id"));
        transactionResponseDTObject.setCategoryId(resultSet.getString("category_id"));
        transactionResponseDTObject.setTransactionId(resultSet.getString("transaction_id"));
        transactionResponseDTObject.setAmount(resultSet.getDouble("transaction_amount"));
        transactionResponseDTObject.setTimestamp(resultSet.getTimestamp("timestamp"));
        transactionResponseDTObject.setRemarks(resultSet.getString("remarks"));
        transactionResponseDTObject.setCategoryName(resultSet.getString("category"));
        transactionResponseDTObject.setCategoryType(resultSet.getString("type"));
        return transactionResponseDTObject;
    }
}
