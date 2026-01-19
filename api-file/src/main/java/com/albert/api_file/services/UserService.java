package com.albert.api_file.services;

import com.albert.api_file.dtos.request.CreateUserRequest;
import com.albert.api_file.models.User;
import com.albert.api_file.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(CreateUserRequest request){
        var user = new User(request.getUsername(), request.getPassword());

        return userRepository.save(user);
    }
}
