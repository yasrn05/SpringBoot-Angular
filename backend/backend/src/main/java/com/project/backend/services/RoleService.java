package com.project.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.backend.models.Role;
import com.project.backend.repositories.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

}
