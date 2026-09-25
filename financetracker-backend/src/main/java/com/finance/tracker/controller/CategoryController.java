package com.finance.tracker.controller;

import com.finance.tracker.entity.Category;
import com.finance.tracker.entity.Transaction;
import com.finance.tracker.service.CategoryService;
import com.finance.tracker.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final TransactionService transactionService;

    public CategoryController(CategoryService categoryService, TransactionService transactionService){
        this.categoryService = categoryService;
        this.transactionService = transactionService;
    }

    // ------------------- CATEGORY CRUD -------------------
    @PostMapping
    public Category createCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @GetMapping
    public List<Category> getCategories(){
        return categoryService.getAllCategories();
    }

    // ------------------- CATEGORY -> TRANSACTIONS -------------------

    @GetMapping("/{categoryId}/transactions")
    public List<Transaction> getTransactionsByCategory(@PathVariable Long categoryId){
        return transactionService.getTransactionByCategory(categoryId);
    }

    @GetMapping("/{categoryId}/transactions/user/{userId}")
    public List<Transaction> getTransactionsByCategoryAndUser(@PathVariable Long categoryId,
                                                              @PathVariable Long userId){
        return transactionService.getTransactionsByUserAndCategory(userId, categoryId);
    }
}
