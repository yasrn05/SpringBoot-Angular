package com.project.backend.services;

import com.project.backend.dtos.UserDTO;
import com.project.backend.exceptions.DataNotFoundException;
import com.project.backend.models.User;

public interface IUserService {
    User createUser(UserDTO userDTO) throws DataNotFoundException;

    String login(String phoneNumber, String password);
}
