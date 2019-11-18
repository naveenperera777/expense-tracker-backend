package com.iit.expensetracker.Service;

public interface ReportService {
    Object getAllCategoriesWithExpensesAndLimit(String userId, String month);
    Object getTransactionSummaryOverAllByMonth(String userId, String month);

}
