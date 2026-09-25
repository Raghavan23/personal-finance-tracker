package com.finance.tracker.controller;

import com.finance.tracker.entity.User;
import com.finance.tracker.entity.Transaction;
import com.finance.tracker.service.UserService;
import com.finance.tracker.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final TransactionService transactionService;

    public UserController(UserService userService, TransactionService transactionService){
        this.userService = userService;
        this.transactionService = transactionService;
    }

    // ------------------- USER CRUD -------------------
    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    // ------------------- USER -> TRANSACTIONS -------------------

    @GetMapping("/{userId}/transactions")
    public List<Transaction> getTransactionsByUser(@PathVariable Long userId) {
        return transactionService.getTransactionByUser(userId);
    }

    @GetMapping("/{userId}/transactions/category/{categoryId}")
    public List<Transaction> getTransactionsByUserAndCategory(@PathVariable Long userId,
                                                              @PathVariable Long categoryId) {
        return transactionService.getTransactionsByUserAndCategory(userId, categoryId);
    }

    // ------------------- REPORTS & FILTERING -------------------

    // Filter by date range
    @GetMapping("/{userId}/transactions/filter")
    public List<Transaction> getTransactionsByDateRange(
            @PathVariable Long userId,
            @RequestParam LocalDate from,
            @RequestParam LocalDate to) {
        return transactionService.getTransactionByDateAndRange(userId, from, to);
    }

    // Income/Expense/Balance summary
    @GetMapping("/{userId}/transactions/summary")
    public Map<String, Double> getMonthlySummary(
            @PathVariable Long userId,
            @RequestParam LocalDate from,
            @RequestParam LocalDate to) {
        return transactionService.getMonthlySummary(userId, from, to);
    }

    // Category-wise summary
    @GetMapping("/{userId}/transactions/summary/category")
    public Map<String, Double> getCategoryWiseSummary(
            @PathVariable Long userId,
            @RequestParam LocalDate from,
            @RequestParam LocalDate to) {
        return transactionService.getCategoryWiseSummary(userId, from, to);
    }
}
