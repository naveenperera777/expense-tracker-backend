package com.iit.expensetracker.DAO;

import com.iit.expensetracker.DataMapper.TransactionDataMapper;
import com.iit.expensetracker.Dto.TransactionResponseDto;
import com.iit.expensetracker.Model.TransactionModel;
import com.iit.expensetracker.Model.TransactionResposeDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public List<TransactionResponseDto> getAllTransactionsByUserId(String userTr){
//        String sql = "SELECT * FROM transaction WHERE userId=?";
        logger.info("Transaction DAO get all transactions by user id {}", userTr);
        String sql = "SELECT * FROM transaction LEFT JOIN category ON transaction.categoryId = category.categoryId WHERE transaction.userId=?";
        logger.info("Transaction DAO get all transactions by user id {}", userTr);
        return jdbcTemplate.query(sql, new String[]{userTr}, new TransactionResposeDataMapper());
    }

    public TransactionModel getTransactionById(String id){
        String sql = "SELECT * FROM transaction WHERE transactionId=?";
        try {
            return jdbcTemplate.queryForObject(sql, new String[]{id}, new TransactionDataMapper());
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public void editTransactionById(TransactionModel transactionModel){
        logger.info("Transaction edit {}", transactionModel.toString());
        String sql = "UPDATE transaction SET transactionId=?,userId=?,categoryId=?,amount=?,remarks=?, timestamp=? WHERE transactionId=?";
        jdbcTemplate.update(sql,transactionModel.getTransactionId(), transactionModel.getUserId(), transactionModel.getCategoryId(), transactionModel.getAmount(), transactionModel.getRemarks(), transactionModel.getTimestamp(), transactionModel.getTransactionId());
    }


}
