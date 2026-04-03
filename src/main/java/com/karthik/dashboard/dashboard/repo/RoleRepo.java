package com.karthik.dashboard.dashboard.repo;

import com.karthik.dashboard.dashboard.model.Role;
import com.karthik.dashboard.dashboard.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepo extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(RoleName name);
}
