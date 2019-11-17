package com.iit.expensetracker.DAO;

import com.iit.expensetracker.DataMapper.TransactionResposeDataMapper;
import com.iit.expensetracker.Dto.CategoryLimitResponseDTObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportDAObject {

    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ReportDAObject.class);

    public ReportDAObject(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CategoryLimitResponseDTObject> getAllCategoriesWithExpensesAndLimit(String userId, String month) {
        String sql = "SELECT * , SUM(amount) as totalAmount FROM transaction LEFT JOIN category ON transaction.categoryId = category.categoryId WHERE transaction.userId=? &&  CONCAT(YEAR(timestamp),'-',MONTH(timestamp)) =? GROUP BY category.category";
        return jdbcTemplate.query(sql, new String[]{userId, month}, new TransactionResposeDataMapper());
    }

}