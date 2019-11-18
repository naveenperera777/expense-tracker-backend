package com.iit.expensetracker.Controller;

import com.iit.expensetracker.Response.ResponseController;
import com.iit.expensetracker.Service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/report")
public class Report extends ResponseController {
    private final ReportService reportService;
    private static final Logger logger = LoggerFactory.getLogger(Report.class);

    public Report(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/category/expense/limit")
    public ResponseEntity<Object> getAllCategoriesWithExpensesAndLimit(@RequestHeader("user") String userId, @RequestHeader("month") String month){
        return sendResponse(reportService.getAllCategoriesWithExpensesAndLimit(userId, month));
    }

    @GetMapping("/overall/{month}")
    public ResponseEntity<Object> getTransactionSummaryOverAllByMonth(@RequestHeader("user_id") String userId, @PathVariable("month") String month){
        return sendResponse(reportService.getTransactionSummaryOverAllByMonth(userId,month));
    }

}
