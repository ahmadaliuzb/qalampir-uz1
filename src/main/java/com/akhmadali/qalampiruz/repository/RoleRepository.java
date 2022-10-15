package com.akhmadali.qalampiruz.repository;

import com.akhmadali.qalampiruz.entity.Role;
import com.akhmadali.qalampiruz.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
