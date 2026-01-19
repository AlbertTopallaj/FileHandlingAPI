package com.albert.api_file.services;

import com.albert.api_file.models.User;
import com.albert.api_file.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    @Override
    public User createUser(String username, String password) throws Exception {
        if(username.isBlank() || username.length() < 4){
            throw new IllegalArgumentException("Username is either empty or too short. Must contain 4 or more characters");
        }
        if (password.isBlank() || password.length() < 8) {
            throw new IllegalArgumentException("Password is either empty or too short. Must contain 8 or more characters");
        }

        var user = new User(username, password);
        user = userRepository.save(user);
    }
}
