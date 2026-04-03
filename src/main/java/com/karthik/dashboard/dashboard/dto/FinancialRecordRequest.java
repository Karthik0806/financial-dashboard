package com.karthik.dashboard.dashboard.dto;

import com.karthik.dashboard.dashboard.model.RecordType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FinancialRecordRequest {
    @NotNull
    @Positive
    private Double amount;
    @NotNull
    private RecordType type;
    @NotBlank
    private String category;
    private LocalDate date;
    private String description;
}
