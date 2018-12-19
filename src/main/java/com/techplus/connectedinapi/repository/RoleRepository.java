package com.techplus.connectedinapi.repository;

import com.techplus.connectedinapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(String role);

}
