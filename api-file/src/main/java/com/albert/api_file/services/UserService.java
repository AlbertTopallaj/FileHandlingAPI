package com.albert.api_file.services;

import com.albert.api_file.models.User;
import com.albert.api_file.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final IUserRepository userRepository;

    @Override
    public User createUser(String username, String password) throws Exception{
        if (username.isBlank() || username.length() < 4){
            throw new IllegalArgumentException();
        }
        var user = new User(username, password);
        return userRepository.save(user);
    }
}
