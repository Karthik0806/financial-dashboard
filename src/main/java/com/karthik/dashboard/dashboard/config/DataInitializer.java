package com.karthik.dashboard.dashboard.config;

import com.karthik.dashboard.dashboard.model.*;
import com.karthik.dashboard.dashboard.repo.FinancialRecordRepo;
import com.karthik.dashboard.dashboard.repo.RoleRepo;
import com.karthik.dashboard.dashboard.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepo roleRepo;
    private final UserRepo userRepo;
    private final FinancialRecordRepo recordRepo;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) {

        Role adminRole = roleRepo.findByName(RoleName.ROLE_ADMIN)
                .orElseGet(() -> roleRepo.save(Role.builder().name(RoleName.ROLE_ADMIN).build()));

        Role analystRole = roleRepo.findByName(RoleName.ROLE_ANALYST)
                .orElseGet(() -> roleRepo.save(Role.builder().name(RoleName.ROLE_ANALYST).build()));

        Role viewerRole = roleRepo.findByName(RoleName.ROLE_VIEWER)
                .orElseGet(() -> roleRepo.save(Role.builder().name(RoleName.ROLE_VIEWER).build()));
        User admin = userRepo.findByEmail("admin@test.com")
                .orElseGet(() -> userRepo.save(User.builder()
                        .name("Admin User")
                        .email("admin@test.com")
                        .password(encoder.encode("admin123"))
                        .status(Status.ACTIVE)
                        .roles(Set.of(adminRole))
                        .build()));

        User analyst = userRepo.findByEmail("analyst@test.com")
                .orElseGet(() -> userRepo.save(User.builder()
                        .name("Analyst User")
                        .email("analyst@test.com")
                        .password(encoder.encode("analyst123"))
                        .status(Status.ACTIVE)
                        .roles(Set.of(analystRole))
                        .build()));

        User viewer = userRepo.findByEmail("viewer@test.com")
                .orElseGet(() -> userRepo.save(User.builder()
                        .name("Viewer User")
                        .email("viewer@test.com")
                        .password(encoder.encode("viewer123"))
                        .status(Status.ACTIVE)
                        .roles(Set.of(viewerRole))
                        .build()));

        if (recordRepo.count() == 0) {

            recordRepo.save(FinancialRecord.builder()
                    .amount(5000.0)
                    .type(RecordType.INCOME)
                    .category("Salary")
                    .date(LocalDate.now().minusDays(5))
                    .description("Monthly salary")
                    .createdBy(admin)
                    .build());

            recordRepo.save(FinancialRecord.builder()
                    .amount(2000.0)
                    .type(RecordType.EXPENSE)
                    .category("Rent")
                    .date(LocalDate.now().minusDays(3))
                    .description("House rent")
                    .createdBy(admin)
                    .build());

            recordRepo.save(FinancialRecord.builder()
                    .amount(1500.0)
                    .type(RecordType.EXPENSE)
                    .category("Food")
                    .date(LocalDate.now().minusDays(2))
                    .description("Groceries")
                    .createdBy(analyst)
                    .build());

            recordRepo.save(FinancialRecord.builder()
                    .amount(3000.0)
                    .type(RecordType.INCOME)
                    .category("Freelance")
                    .date(LocalDate.now().minusDays(1))
                    .description("Side project")
                    .createdBy(analyst)
                    .build());
        }
    }
}