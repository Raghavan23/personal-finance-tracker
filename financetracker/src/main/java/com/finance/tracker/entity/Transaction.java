package com.finance.tracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String type;    // expense or income

    @Column(nullable = false)
    private String description;

    private LocalDate date;

    //Many Transaction belong to one user
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    //Many Transaction belong to one category
    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;
}
