package com.iit.expensetracker.Controller;

import com.iit.expensetracker.Response.ResponseController;
import com.iit.expensetracker.Service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportController extends ResponseController {
    private final ReportService reportService;
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/category/expense/limit")
    public ResponseEntity<Object> getAllCategoriesWithExpensesAndLimit(@RequestHeader("user") String userId, @RequestHeader("month") String month){
        return sendResponse(reportService.getAllCategoriesWithExpensesAndLimit(userId, month));
    }
}
