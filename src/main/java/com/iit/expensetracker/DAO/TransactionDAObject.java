package com.iit.expensetracker.DAO;

import com.iit.expensetracker.Dto.TransactionResponseDTObject;
import com.iit.expensetracker.Model.Transaction;
import com.iit.expensetracker.DataMapper.TransactionResposeDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionDAObject {
    private static final Logger logger = LoggerFactory.getLogger(TransactionDAObject.class);

    public TransactionDAObject(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final JdbcTemplate jdbcTemplate;

    public void saveTransaction(Transaction transaction){
        String sql = "INSERT INTO transaction(transactionId, userId ,categoryId, amount, remarks, timestamp) VALUES(?,?,?,?,?,?)";
        logger.info("transaction DAO {}", transaction.toString());
        jdbcTemplate.update(sql, transaction.getTransactionId(), transaction.getUserId(), transaction.getCategoryId(), transaction.getAmount(), transaction.getRemarks(), transaction.getTimestamp());
    }

    public List<TransactionResponseDTObject> getAllTransactionsByUserId(String userTr){
//        String sql = "SELECT * FROM transaction WHERE userId=?";
        logger.info("Transaction DAO get all transactions by user id {}", userTr);
        String sql = "SELECT * FROM transaction LEFT JOIN category ON transaction.categoryId = category.categoryId WHERE transaction.userId=?";
        logger.info("Transaction DAO get all transactions by user id {}", userTr);
        return jdbcTemplate.query(sql, new String[]{userTr}, new TransactionResposeDataMapper());
    }

}
