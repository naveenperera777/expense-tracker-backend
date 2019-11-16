package com.iit.expensetracker.DAO;

import com.iit.expensetracker.DataMapper.TransactionDataMapper;
import com.iit.expensetracker.Model.TransactionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionDAO {
    private static final Logger logger = LoggerFactory.getLogger(TransactionDAO.class);

    public TransactionDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final JdbcTemplate jdbcTemplate;

    public void saveTransaction(TransactionModel transactionModel){
        String sql = "INSERT INTO transaction(transactionId, userId ,categoryId, amount, remarks, timestamp) VALUES(?,?,?,?,?,?)";
        logger.info("transaction DAO {}", transactionModel.toString());
        jdbcTemplate.update(sql, transactionModel.getTransactionId(), transactionModel.getUserId(), transactionModel.getCategoryId(), transactionModel.getAmount(), transactionModel.getRemarks(), transactionModel.getTimestamp());
    }

    public List<TransactionModel> getAllTransactionsByUserId(String userId){
        String sql = "SELECT * FROM transaction WHERE userId=?";
        logger.info("Transaction DAO get all transactions by user id {}", userId);
        return jdbcTemplate.query(sql, new String[]{userId}, new TransactionDataMapper());
    }

}
