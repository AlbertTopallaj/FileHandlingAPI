package com.albert.api_file.services;

import com.albert.api_file.models.User;

public interface IUserService {
    User createUser(String username, String password) throws Exception;
}
