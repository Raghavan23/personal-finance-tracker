package com.finance.tracker.dto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionRequest {
    private Double amount;
    private String description;
    private Long userId;
    private Long categoryId;
    private String type;
    private LocalDate date;
}
