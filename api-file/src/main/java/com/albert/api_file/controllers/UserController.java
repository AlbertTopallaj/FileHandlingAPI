package com.albert.api_file.controllers;

import com.albert.api_file.dtos.CreateUserRequest;
import com.albert.api_file.dtos.LoginRequest;
import com.albert.api_file.dtos.LoginResponse;
import com.albert.api_file.dtos.UserResponse;
import com.albert.api_file.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        try {
            var user = userService.createUser(request.getUsername(), request.getPassword());
            return ResponseEntity.created(URI.create("/user")).body(UserResponse.fromModel(user));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body(Map.of(
                            "message", "Invalid password, password must be longer.",
                            "errors", "Something went wrong"
                    ));
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        // TODO: This does not work, fix it.
        try {
            String token = userService.login(loginRequest.getUsername(), loginRequest.getPassword());

            return ResponseEntity.ok(token);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .badRequest()
                    .body(Map.of(
                            "message", "Invalid credentalias"
                    ));
        }
    }
}

