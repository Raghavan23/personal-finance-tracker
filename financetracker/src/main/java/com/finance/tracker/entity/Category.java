package com.finance.tracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false,unique = true)
    private String name;

    // one category can have many transactions
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<Transaction> transactions;
}
