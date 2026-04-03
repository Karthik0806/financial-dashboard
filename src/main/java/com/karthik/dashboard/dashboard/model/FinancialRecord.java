package com.karthik.dashboard.dashboard.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinancialRecord extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Double amount;
    @Enumerated(EnumType.STRING)
    private RecordType type;
    private String category;

    private LocalDate date;
    private String description;
    @ManyToOne
    private User createdBy;


}
