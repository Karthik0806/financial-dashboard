package com.karthik.dashboard.dashboard.controller;

import com.karthik.dashboard.dashboard.dto.ApiResponse;
import com.karthik.dashboard.dashboard.dto.FinancialRecordRequest;
import com.karthik.dashboard.dashboard.dto.FinancialRecordResponse;
import com.karthik.dashboard.dashboard.model.RecordType;
import com.karthik.dashboard.dashboard.security.SecurityUtils;
import com.karthik.dashboard.dashboard.service.FinancialRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
@Slf4j
public class FinancialRecordController {


    private final FinancialRecordService service;
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<FinancialRecordResponse>> create(
            @Valid @RequestBody FinancialRecordRequest request) {

        log.info("User {} creating record with amount {}",
                SecurityUtils.getCurrentUserEmail(),
                request.getAmount());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("success", service.createRecord(request)));
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    public ResponseEntity<ApiResponse<?>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(
                new ApiResponse<>("success", service.getAllPaginated(page, size))
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    public ResponseEntity<ApiResponse<?>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(
                new ApiResponse<>("success", service.getById(id))
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<?>> update(
            @PathVariable UUID id,
            @Valid @RequestBody FinancialRecordRequest request) {

        return ResponseEntity.ok(
                new ApiResponse<>("success", service.update(id, request))
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok(
                new ApiResponse<>("success", "Record deleted")
        );
    }
    @GetMapping("/summary")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    public ResponseEntity<?> summary() {
        return ResponseEntity.ok(
                new ApiResponse<>("success", service.getSummary())
        );
    }
    @GetMapping("/filter")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    public ResponseEntity<ApiResponse<?>> filter(
            @RequestParam(required = false) RecordType type,
            @RequestParam(required = false) String category
    ) {
        return ResponseEntity.ok(
                new ApiResponse<>("success", service.filter(type, category))
        );
    }


    @GetMapping("/category-summary")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    public ResponseEntity<ApiResponse<?>> categorySummary() {
        return ResponseEntity.ok(
                new ApiResponse<>("success", service.getCategorySummary())
        );
    }


}
