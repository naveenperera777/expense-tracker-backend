package com.iit.expensetracker.DAO;

import com.iit.expensetracker.DataMapper.TransactionMapper;
import com.iit.expensetracker.Dto.TransactionResponseDTObject;
import com.iit.expensetracker.Model.Transaction;
import com.iit.expensetracker.DataMapper.TransactionResposeDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
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
        String sql = "INSERT INTO transaction(transaction_id, user_Id ,category_id, transaction_amount, transaction_notes, transaction_time) VALUES(?,?,?,?,?,?)";
        logger.info("transaction DAO {}", transaction.toString());
        jdbcTemplate.update(sql, transaction.getTransaction_id(), transaction.getUser_id(), transaction.getCategory_id(), transaction.getTransaction_amount(), transaction.getTransaction_notes(), transaction.getTransaction_time());
    }

    public List<TransactionResponseDTObject> getAllTransactionsByUserId(String user_id){
        logger.info("Transaction DAO get all transactions by user id {}", user_id);
        String sql = "SELECT * FROM transaction LEFT JOIN category ON transaction.category_id = category.category_id WHERE transaction.user_Id=?";
        logger.info("Transaction DAO get all transactions by user id {}", user_id);
        return jdbcTemplate.query(sql, new String[]{user_id}, new TransactionResposeDataMapper());
    }

    public Transaction getTransactionById(String id){
        String sql = "SELECT * FROM transaction WHERE transaction_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, new String[]{id}, new TransactionMapper());
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public void editTransactionById(Transaction transaction){
        logger.info("Transaction edit {}", transaction.toString());
        String sql = "UPDATE transaction SET transaction_id=?,user_Id=?,category_id=?,transaction_amount=?,transaction_notes=?, transaction_time=? WHERE transaction_id=?";
        jdbcTemplate.update(sql,transaction.getTransaction_id(), transaction.getUser_id(), transaction.getCategory_id(), transaction.getTransaction_amount(), transaction.getTransaction_notes(), transaction.getTransaction_time(), transaction.getTransaction_id());
    }

    public List<TransactionResponseDTObject> getTransactionsByMonth(String month, String user){
        logger.info("Get transactions for {} in {} period", month, user);
        String sql = "SELECT * FROM transaction  LEFT JOIN category ON transaction.category_id = category.category_id WHERE transaction.user_Id=?  &&  CONCAT(YEAR(transaction_time),'-',MONTH(transaction_time)) =?";
        return jdbcTemplate.query(sql, new String[]{user,month}, new TransactionResposeDataMapper());
    }

    public void deleteTransactionById(String id){
        String sql = "DELETE FROM transaction WHERE transaction_id=?";
        jdbcTemplate.update(sql,id);
    }

}
