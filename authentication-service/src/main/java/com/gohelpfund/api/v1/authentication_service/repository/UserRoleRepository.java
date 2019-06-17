package com.gohelpfund.api.v1.authentication_service.repository;

import com.gohelpfund.api.v1.authentication_service.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {
    List<UserRole> findByUsername(String username);
}
