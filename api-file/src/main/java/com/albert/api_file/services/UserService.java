package com.albert.api_file.services;

import com.albert.api_file.models.User;
import com.albert.api_file.repositories.IUserRepository;
import com.albert.api_file.security.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService  {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    /**
     * Creates a user with the information username and password given by the user
     *
     * @param username the username that the user inserts
     * @param password the password that the user inserts
     * @return the created user with the information inserted by the user
     */



    public User createUser(String username, String password) {
        if (username.isBlank() || username.length() < 4) {
            throw new IllegalArgumentException("Username is either empty or too short. Must contain 4 or more characters");
        }

        if (password.isBlank() || password.length() < 8) {
            throw new IllegalArgumentException("Password is either empty or too short. Must contain 8 or more characters");
        }

        if (userRepository.existsByUsername(username)){
            throw new IllegalArgumentException("Användarnamnet är upptaget.");
        }

        String hashedPassword = passwordEncoder.encode(password);

        var user = new User(username, hashedPassword);
        user = userRepository.save(user);

        return user;
    }

    /**
     * Login to the system with a username and a password and checks if the user actually exists in the database
     *
     * @param username the username that the user inserts
     * @param password the password that the user inserts
     * @return returns a token with is used to gain access to other endpoints with requires auth
     */

    public String login(String username, String password) {
        Optional<User> optional = userRepository.findByUsername(username);
        if (optional.isEmpty()) {
            return null;
        }
        User user = optional.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return null;
        }
        return jwtService.generateToken(user.getId());

    }
}
