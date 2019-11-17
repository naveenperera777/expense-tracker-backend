package com.iit.expensetracker.Controller;

import com.iit.expensetracker.Dto.TransactionDto;
import com.iit.expensetracker.Response.ResponseController;
import com.iit.expensetracker.Service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("transaction")
@RestController
public class TransactionController extends ResponseController {
    private final TransactionService transactionService;

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity saveTransaction(@RequestBody TransactionDto transactionDto, @RequestHeader("user") String userId){
        return sendResponse(transactionService.saveTransaction(userId, transactionDto));
    }

    @GetMapping("/user")
    public ResponseEntity getAllTransactionsByUserId(@RequestHeader("user") String userId ){
        return sendResponse(transactionService.getAllTransactionsByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity editTransactionById(@PathVariable("id") String id, @RequestBody TransactionDto transactionDto, @RequestHeader("user") String user){
        return sendResponse(transactionService.editTransactionById(id, transactionDto, user));
    }

    @GetMapping("/month/{month}")
    public ResponseEntity getTransactionsByMonth(@PathVariable("month") String month, @RequestHeader("user") String user){
        return sendResponse(transactionService.getTransactionsByMonth(month, user));
    }


}
