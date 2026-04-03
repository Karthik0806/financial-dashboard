package com.karthik.dashboard.dashboard.service;

import com.karthik.dashboard.dashboard.dto.CategorySummary;
import com.karthik.dashboard.dashboard.dto.DashboardResponse;
import com.karthik.dashboard.dashboard.dto.FinancialRecordRequest;
import com.karthik.dashboard.dashboard.dto.FinancialRecordResponse;
import com.karthik.dashboard.dashboard.dto.MapStructMapper.FinancialRecordMapper;
import com.karthik.dashboard.dashboard.exception.ResourceNotFoundException;
import com.karthik.dashboard.dashboard.model.FinancialRecord;

import com.karthik.dashboard.dashboard.model.RecordType;
import com.karthik.dashboard.dashboard.model.User;
import com.karthik.dashboard.dashboard.repo.FinancialRecordRepo;
import com.karthik.dashboard.dashboard.repo.UserRepo;
import com.karthik.dashboard.dashboard.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class FinancialRecordService {
    private final FinancialRecordRepo repo;
    private final FinancialRecordMapper mapper;
    private final UserRepo userRepo;

    public FinancialRecordResponse createRecord(FinancialRecordRequest request) {
        FinancialRecord entity = mapper.toEntity(request);

        String email = SecurityUtils.getCurrentUserEmail();
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        entity.setCreatedBy(user);

        return mapper.toResponse(repo.save(entity));
    }
    public List<FinancialRecordResponse> getAllPaginated() {
        return repo.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
    public FinancialRecordResponse getById(UUID id) {
        FinancialRecord record = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));
        return mapper.toResponse(record);
    }

    public FinancialRecordResponse update(UUID id, FinancialRecordRequest request) {

        FinancialRecord record = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));
        record.setAmount(request.getAmount());
        record.setCategory(request.getCategory());
        record.setType(request.getType());
        record.setDate(request.getDate());
        record.setDescription(request.getDescription());

        return mapper.toResponse(repo.save(record));
    }

    public void delete(UUID id) {

        FinancialRecord record = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));
        repo.delete(record);
    }
    public DashboardResponse getSummary() {
        Double income = repo.getTotalIncome();
        Double expense = repo.getTotalExpense();

        income = (income == null) ? 0 : income;
        expense = (expense == null) ? 0 : expense;

        return new DashboardResponse(
                income,
                expense,
                income - expense
        );
    }
    public List<FinancialRecordResponse> filter(RecordType type, String category) {

        List<FinancialRecord> records;

        if (type != null && category != null) {
            records = repo.findByTypeAndCategory(type, category);
        } else if (type != null) {
            records = repo.findByType(type);
        } else if (category != null) {
            records = repo.findByCategory(category);
        } else {
            records = repo.findAll();
        }

        return records.stream().map(mapper::toResponse).toList();
    }
    public Page<FinancialRecordResponse> getAllPaginated(int page, int size) {
        return repo.findAll(PageRequest.of(page, size))
                .map(mapper::toResponse);
    }
    public List<CategorySummary> getCategorySummary() {
        return repo.getCategoryWiseTotals()
                .stream()
                .map(obj -> new CategorySummary((String) obj[0], (Double) obj[1]))
                .toList();
    }


}
