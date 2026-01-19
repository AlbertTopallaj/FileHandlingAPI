package com.albert.api_file.service;

import com.albert.api_file.dto.request.CreateUserRequest;
import com.albert.api_file.model.User;
import com.albert.api_file.repository.UserRepository;
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
