package com.karthik.dashboard.dashboard.security;

import com.karthik.dashboard.dashboard.exception.ResourceNotFoundException;
import com.karthik.dashboard.dashboard.model.FinancialRecord;
import com.karthik.dashboard.dashboard.repo.FinancialRecordRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RecordSecurity {

    private final FinancialRecordRepo repo;

    public boolean isOwner(UUID recordId) {
        String email = SecurityUtils.getCurrentUserEmail();

        FinancialRecord record = repo.findById(recordId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        return record.getCreatedBy().getEmail().equals(email);
    }
}