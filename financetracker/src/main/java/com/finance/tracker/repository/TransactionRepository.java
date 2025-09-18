package com.finance.tracker.repository;

import com.finance.tracker.entity.Category;
import com.finance.tracker.entity.Transaction;
import com.finance.tracker.entity.User;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByUser(User user);
    List<Transaction> findByUserAndCategory(User user, Category category);
    List<Transaction> findByCategory(Category category);

    List<Transaction> findByUserIdAndDateBetween(Long userId, LocalDate from, LocalDate to);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.user.id = :userId AND t.type = :type AND t.date BETWEEN :from AND :to")
    Double getTotalByTypeAndDate(Long userId, String type, LocalDate from, LocalDate to);

    @Query("SELECT t.category.name, SUM(t.amount) FROM Transaction t WHERE t.user.id = :userId AND t.date BETWEEN :from AND :to GROUP BY t.category.name")
    List<Object[]> getTotalsByCategory(Long userId, LocalDate from, LocalDate to);
}
