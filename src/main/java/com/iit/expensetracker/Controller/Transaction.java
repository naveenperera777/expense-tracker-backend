package com.iit.expensetracker.Controller;

import com.iit.expensetracker.Dto.TransactionDTObject;
import com.iit.expensetracker.Response.ResponseController;
import com.iit.expensetracker.Service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("transaction")
@RestController
public class Transaction extends ResponseController {
    private final TransactionService transactionService;

    private static final Logger logger = LoggerFactory.getLogger(Transaction.class);

    public Transaction(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity saveTransaction(@RequestBody TransactionDTObject transactionDTObject, @RequestHeader("user") String userId){
        return sendResponse(transactionService.saveTransaction(userId, transactionDTObject));
    }

    @GetMapping("/user")
    public ResponseEntity getAllTransactionsByUserId(@RequestHeader("user") String userId ){
        return sendResponse(transactionService.getAllTransactionsByUserId(userId));
    }


}
