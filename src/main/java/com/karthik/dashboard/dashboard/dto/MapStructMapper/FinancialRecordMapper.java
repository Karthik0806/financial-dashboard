package com.karthik.dashboard.dashboard.dto.MapStructMapper;

import com.karthik.dashboard.dashboard.dto.FinancialRecordRequest;
import com.karthik.dashboard.dashboard.dto.FinancialRecordResponse;
import com.karthik.dashboard.dashboard.model.FinancialRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FinancialRecordMapper {
    FinancialRecord toEntity(FinancialRecordRequest request);
    FinancialRecordResponse toResponse(FinancialRecord entity);
}
