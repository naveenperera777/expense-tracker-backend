package com.iit.expensetracker.DAO;

import com.iit.expensetracker.DataMapper.CategoryLimitResponseDataMapper;
import com.iit.expensetracker.DataMapper.TransactionDataMapper;
import com.iit.expensetracker.Dto.CategoryLimitResponseDto;
import com.iit.expensetracker.Model.TransactionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportDAO {

    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    public ReportDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CategoryLimitResponseDto> getAllCategoriesWithExpensesAndLimit(String userId, String month){
        logger.info("getAllCategoriesWithExpensesAndLimit {} for month {}",userId,month);
        String sql = "SELECT * , SUM(amount) as totalAmount FROM transaction LEFT JOIN category ON transaction.categoryId = category.categoryId WHERE transaction.userId=? &&  CONCAT(YEAR(timestamp),'-',MONTH(timestamp)) =? GROUP BY category.category";
        return jdbcTemplate.query(sql,new String[]{userId,month} , new CategoryLimitResponseDataMapper());
    }
}


