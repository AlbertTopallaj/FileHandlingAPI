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
public class UserService implements UserDetailsService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;


    public User createUser(String username, String password) throws Exception {
        if(username.isBlank() || username.length() < 4){
            throw new IllegalArgumentException("Username is either empty or too short. Must contain 4 or more characters");
        }
        if (password.isBlank() || password.length() < 8) {
            throw new IllegalArgumentException("Password is either empty or too short. Must contain 8 or more characters");
        }

        String hashedPassword = passwordEncoder.encode(password);

        var user = new User(username, hashedPassword);
        user = userRepository.save(user);

        return user;
    }
    public String login(String username, String password){
        var user = (User) loadUserByUsername(username);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return null;
        }
        return jwtService.generateToken(user.getId());
    }
    public Optional<User> verifyAuthentication(String token){
        try {
            UUID userId = jwtService.verifyToken(token);
            return findById(userId);
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    private Optional<User> findById(UUID userId) {
        return userRepository.findById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username was not found"));
    }
}
