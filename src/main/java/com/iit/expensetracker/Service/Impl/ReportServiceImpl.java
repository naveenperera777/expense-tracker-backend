package com.iit.expensetracker.Service.Impl;

import com.iit.expensetracker.DAO.ReportDAO;
import com.iit.expensetracker.Service.ReportService;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportDAO reportDAO;

    public ReportServiceImpl(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }

    @Override
    public Object getAllCategoriesWithExpensesAndLimit(String userId, String month) {
        return reportDAO.getAllCategoriesWithExpensesAndLimit(userId, month);
    }
}
