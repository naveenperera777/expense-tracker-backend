package com.iit.expensetracker.Service.Implementation;

import com.iit.expensetracker.DAO.ReportDAObject;
import com.iit.expensetracker.Dto.CategoryLimitResponseDTObject;
import com.iit.expensetracker.Dto.CategoryPercentageReponseDTObject;
import com.iit.expensetracker.Dto.OverAllDto;
import com.iit.expensetracker.Response.Response;
import com.iit.expensetracker.Service.ReportService;
import com.iit.expensetracker.enums.ResponseMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImplementation implements ReportService {

    private final ReportDAObject reportDAObject;

    public ReportServiceImplementation(ReportDAObject reportDAObject) {
        this.reportDAObject = reportDAObject;
    }

    @Override
    public Object getAllCategoriesWithExpensesAndLimit(String userId, String month) {
        List<CategoryLimitResponseDTObject> categoryList = reportDAObject.getAllCategoriesWithExpensesAndLimit(userId, month);
        if (categoryList.isEmpty())
            return new Response(ResponseMessage.NO_RECORD, "No records found");
        List<CategoryPercentageReponseDTObject> listWithPercentage = new ArrayList<>();
        for (CategoryLimitResponseDTObject category : categoryList) {
            CategoryPercentageReponseDTObject categoryPercentageDto = new CategoryPercentageReponseDTObject();
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
    public Object getTransactionSummaryOverAllByMonth(String userId, String month) {
        CategoryLimitResponseDTObject expenses =   reportDAObject.getExpensesSummary(userId,month);
        CategoryLimitResponseDTObject income = reportDAObject.getIncomeSummary(userId, month);
        OverAllDto overAllDto = new OverAllDto();
        overAllDto.setUserId(userId);
        overAllDto.setMonth(month);
        overAllDto.setExpenses((int)expenses.getTotalAmount());
        overAllDto.setIncome((int) income.getTotalAmount());
        int balnce = (int) income.getTotalExpenes() - (int) expenses.getTotalExpenes();
        overAllDto.setBalance(balnce);
        return overAllDto;
    }

}
