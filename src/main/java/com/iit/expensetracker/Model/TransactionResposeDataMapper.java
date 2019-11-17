package com.iit.expensetracker.Model;

import com.iit.expensetracker.Dto.TransactionResponseDto;
import com.iit.expensetracker.enums.CategoryEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionResposeDataMapper implements RowMapper<TransactionResponseDto> {
    private static final Logger logger = LoggerFactory.getLogger(TransactionResposeDataMapper.class);

    @Override
    public TransactionResponseDto mapRow(ResultSet resultSet, int i) throws SQLException {
        TransactionResponseDto transactionModel = new TransactionResponseDto();
        transactionModel.setUserId(resultSet.getString("userId"));
        transactionModel.setCategoryId(resultSet.getString("categoryId"));
        transactionModel.setTransactionId(resultSet.getString("transactionId"));
        transactionModel.setAmount(resultSet.getDouble("amount"));
        transactionModel.setTimestamp(resultSet.getTimestamp("timestamp"));
        transactionModel.setRemarks(resultSet.getString("remarks"));
        transactionModel.setCategoryName(resultSet.getString("category"));
        transactionModel.setCategoryType(resultSet.getString("type"));
        return transactionModel;
    }
}
