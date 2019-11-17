package com.iit.expensetracker.Controller;

import com.iit.expensetracker.Dto.TransactionDTObject;
import com.iit.expensetracker.Response.ResponseController;
import com.iit.expensetracker.Service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/transaction")
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

    @GetMapping("/all")
    public ResponseEntity getAllTransactionsByUserId(@RequestHeader("user_id") String user_id ){
        return sendResponse(transactionService.getAllTransactionsByUserId(user_id));
    }

    @PutMapping("/{id}")
    public ResponseEntity transactionEditById(@PathVariable("id") String id, @RequestBody TransactionDTObject transactionDto, @RequestHeader("user") String user){
        return sendResponse(transactionService.transactionEditById(id, transactionDto, user));
    }

    @GetMapping("/time/{month}")
    public ResponseEntity retrieveAllTransactionsByMonth(@PathVariable("month") String month, @RequestHeader("user") String user){
        return sendResponse(transactionService.retrieveAllTransactionsByMonth(month, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity transactionDeleteById(@PathVariable("id") String transactionId){
        return sendResponse(transactionService.transactionDeleteById(transactionId));
    }


}
