package com.iit.expensetracker.DAO;

import com.iit.expensetracker.Model.TransactionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TransactionDAO {
    private static final Logger logger = LoggerFactory.getLogger(TransactionDAO.class);

    public TransactionDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final JdbcTemplate jdbcTemplate;

    public void saveTransaction(TransactionModel transactionModel){
        String sql = "INSERT INTO transaction(transactionId, categoryId, amount, remarks, timestamp) VALUES(?,?,?,?,?)";
        logger.info("transaction DAO {}", transactionModel.toString());
        jdbcTemplate.update(sql, transactionModel.getTransactionId(), transactionModel.getCategoryId(), transactionModel.getAmount(), transactionModel.getRemarks(), transactionModel.getTimestamp());
    }

}
