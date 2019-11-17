package com.iit.expensetracker.Service.Implementation;

import com.iit.expensetracker.DAO.ReportDAObject;
import com.iit.expensetracker.Dto.CategoryResponseDTObject;
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
        List<CategoryResponseDTObject> categoryList = reportDAObject.getAllCategoriesWithExpensesAndLimit(userId, month);
        if (categoryList.isEmpty())
            return new Response(ResponseMessage.NO_RECORD, "No records found");
        List<CategoryPercentageDto> listWithPercentage = new ArrayList<>();
        for (CategoryResponseDTObject category : categoryList) {
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
}
