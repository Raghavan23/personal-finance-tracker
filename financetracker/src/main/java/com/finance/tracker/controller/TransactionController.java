package com.finance.tracker.controller;

import com.finance.tracker.dto.TransactionRequest;
import com.finance.tracker.entity.Transaction;
import com.finance.tracker.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody TransactionRequest request){
        return transactionService.addTransaction(request);
    }

    @GetMapping
    public List<Transaction> getTransactions(){
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id){
        return transactionService.getTransactionById(id);
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody TransactionRequest request){
        return transactionService.updateTransaction(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteTransaction(@PathVariable Long id){
        transactionService.deleteTransaction(id);
        return "Transaction deleted successfully with id: " + id;
    }
}
