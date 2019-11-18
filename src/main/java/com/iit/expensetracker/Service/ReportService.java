package com.iit.expensetracker.Service;

public interface ReportService {
    Object getAllCategoriesWithExpensesAndLimit(String userId, String month);
    Object getTransactionSummary(String userId, String month);
}
