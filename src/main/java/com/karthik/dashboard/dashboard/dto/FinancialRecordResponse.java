package com.karthik.dashboard.dashboard.dto;

import com.karthik.dashboard.dashboard.model.RecordType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class FinancialRecordResponse {
    private UUID id;
    private Double amount;
    private RecordType type;
    private String category;
    private LocalDate date;
    private String description;
}
