package com.finance.tracker.service;

import com.finance.tracker.dto.TransactionRequest;
import com.finance.tracker.entity.Transaction;
import com.finance.tracker.entity.User;
import com.finance.tracker.entity.Category;
import com.finance.tracker.repository.TransactionRepository;
import com.finance.tracker.repository.UserRepository;
import com.finance.tracker.repository.CategoryRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public TransactionService(TransactionRepository transactionRepository,
                              UserRepository userRepository,
                              CategoryRepository categoryRepository){
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public Transaction addTransaction(TransactionRequest request){
        //Fetch User
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()-> new RuntimeException("User not found."));

        //Fetch Category
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(()-> new RuntimeException("Category not found."));

        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setType(request.getType());
        transaction.setDescription(request.getDescription());
        transaction.setUser(user);
        transaction.setCategory(category);
        transaction.setDate(
                request.getDate() != null ? request.getDate() : LocalDate.now()
        );

        return transactionRepository.save(transaction);
    }

    // READ ALL
    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    // READ ONE
    public Transaction getTransactionById(Long id){
        return transactionRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Transaction not found."));
    }

    // Get Transaction By User
    public List<Transaction> getTransactionByUser(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not found"));
        return transactionRepository.findByUser(user);
    }

    // Get Transaction By Category
    public List<Transaction> getTransactionByCategory(Long categoryId){
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new RuntimeException("Category not found."));
        return transactionRepository.findByCategory(category);
    }

    // Get Transaction By Both User and Category
    public List<Transaction> getTransactionsByUserAndCategory(Long userId, Long categoryId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found."));
        return transactionRepository.findByUserAndCategory(user, category);
    }

    public List<Transaction> getTransactionByDateAndRange(Long userId,LocalDate from,LocalDate to){
        return transactionRepository.findByUserIdAndDateBetween(userId,from,to);
    }

    public Map<String,Double> getMonthlySummary(Long userId,LocalDate from,LocalDate to){
        Double totalExpense = transactionRepository.getTotalByTypeAndDate(userId,"EXPENSE",from,to);
        Double totalIncome = transactionRepository.getTotalByTypeAndDate(userId,"INCOME",from,to);

        Map<String,Double> summary = new HashMap<>();

        summary.put("totalExpense",totalExpense != null ? totalExpense : 0.0);
        summary.put("totalIncome",totalIncome != null ? totalIncome : 0.0);
        summary.put("netBalance",(totalIncome != null ? totalIncome : 0.0) - (totalExpense != null ? totalExpense : 0.0));

        return summary;
    }

    public Map<String,Double> getCategoryWiseSummary(Long userId, LocalDate from, LocalDate to){
        List<Object[]> results = transactionRepository.getTotalsByCategory(userId,from,to);

        Map<String,Double> categorySummary = new HashMap<>();

        for(Object[] row : results){
            categorySummary.put((String) row[0],(Double) row[1]);
        }
        return categorySummary;
    }

    // UPDATE

    public Transaction updateTransaction(Long id, TransactionRequest request){
        Transaction transaction = getTransactionById(id);

        transaction.setAmount(request.getAmount());
        transaction.setType(request.getType());
        transaction.setDescription(request.getDescription());
        transaction.setDate(request.getDate() != null ? request.getDate() : LocalDate.now());

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()->new RuntimeException("User not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(()->new RuntimeException("Category not found."));

        transaction.setUser(user);
        transaction.setCategory(category);

        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(long id){
        Transaction transaction = getTransactionById(id);
        transactionRepository.delete(transaction);
    }
}
