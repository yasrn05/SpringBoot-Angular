package com.project.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.backend.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
