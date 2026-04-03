package com.karthik.dashboard.dashboard.repo;

import com.karthik.dashboard.dashboard.model.FinancialRecord;
import com.karthik.dashboard.dashboard.model.RecordType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FinancialRecordRepo extends JpaRepository<FinancialRecord, UUID> {
    @Query("SELECT SUM(f.amount) FROM FinancialRecord f WHERE f.type = 'INCOME'")
    Double getTotalIncome();
    @Query("SELECT SUM(f.amount) FROM FinancialRecord f WHERE f.type = 'EXPENSE'")
    Double getTotalExpense();
    List<FinancialRecord> findByType(RecordType type);
    List<FinancialRecord> findByCategory(String category);
    List<FinancialRecord> findByTypeAndCategory(RecordType type, String category);
    @Query("""
        SELECT f.category, SUM(f.amount)
        FROM FinancialRecord f
        GROUP BY f.category
    """)
    List<Object[]> getCategoryWiseTotals();
}
