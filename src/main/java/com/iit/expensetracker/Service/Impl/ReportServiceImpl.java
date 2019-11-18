package com.iit.expensetracker.Service.Impl;

import com.iit.expensetracker.DAO.ReportDAO;
import com.iit.expensetracker.Dto.CategoryLimitResponseDto;
import com.iit.expensetracker.Dto.CategoryPercentageDto;
import com.iit.expensetracker.Dto.SummaryResponseDto;
import com.iit.expensetracker.Response.Response;
import com.iit.expensetracker.Service.ReportService;
import com.iit.expensetracker.enums.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportDAO reportDAO;
    private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    public ReportServiceImpl(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }

    @Override
    public Object getAllCategoriesWithExpensesAndLimit(String userId, String month) {
        List<CategoryLimitResponseDto> categoryList = reportDAO.getAllCategoriesWithExpensesAndLimit(userId, month);
        if (categoryList.isEmpty())
            return new Response(ResponseMessage.NO_RECORD, "No records found");
        List<CategoryPercentageDto> listWithPercentage = new ArrayList<>();
        for (CategoryLimitResponseDto category : categoryList) {
            CategoryPercentageDto categoryPercentageDto = new CategoryPercentageDto();
            categoryPercentageDto.setUserId(category.getUserId());
            categoryPercentageDto.setCategoryId(category.getCategoryId());
            categoryPercentageDto.setCategory(category.getCategory());
            categoryPercentageDto.setType(category.getType());
            categoryPercentageDto.setLimit(category.getLimit());
            categoryPercentageDto.setTotalExpenes(category.getTotalExpenes());

            if (category.getTotalExpenes()<category.getLimit() || category.getTotalExpenes() == category.getLimit()){
               double percentage = (category.getTotalExpenes()/category.getLimit())*100;
                categoryPercentageDto.setExceeded(false);
                categoryPercentageDto.setPercentage((int)percentage);
            } else {
                double percentage = (category.getLimit()/category.getTotalExpenes())*100;
                categoryPercentageDto.setExceeded(true);
                categoryPercentageDto.setPercentage((int)percentage);
            }
            listWithPercentage.add(categoryPercentageDto);
        }
        return new Response(ResponseMessage.SUCCESS, listWithPercentage);
    }

    @Override
    public Object getTransactionSummary(String userId, String month) {
        CategoryLimitResponseDto expenses =   reportDAO.getExpensesSummary(userId,month);
        CategoryLimitResponseDto income = reportDAO.getIncomeSummary(userId, month);
        SummaryResponseDto summaryResponseDto = new SummaryResponseDto();
        summaryResponseDto.setUserId(userId);
        summaryResponseDto.setMonth(month);
        summaryResponseDto.setExpenses((int)expenses.getTotalExpenes());
        summaryResponseDto.setIncome((int) income.getTotalExpenes());
        int balnce = (int) income.getTotalExpenes() - (int) expenses.getTotalExpenes();
        summaryResponseDto.setBalance(balnce);
        return summaryResponseDto;
    }
}
