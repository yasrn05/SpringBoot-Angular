package com.project.backend.services;

import com.project.backend.dtos.UpdateUserDTO;
import com.project.backend.dtos.UserDTO;
import com.project.backend.models.User;

public interface IUserService {
    User createUser(UserDTO userDTO) throws Exception;

    String login(String phoneNumber, String password, Long roleId) throws Exception;

    User getUserDetailsFromToken(String token) throws Exception;

    User updateUser(Long userId, UpdateUserDTO updatedUserDTO) throws Exception;
}
