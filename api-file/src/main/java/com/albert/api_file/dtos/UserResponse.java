package com.albert.api_file.dtos;

import com.albert.api_file.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class UserResponse {

    private final UUID id;

    private String username;
    private String password;

    private Date createdAt;

    public UserResponse(UUID id, String username, Date createdAt) {
        this.id = id;
        this.username = username;
        this.createdAt = createdAt;
    }

        public static UserResponse fromModel(User user){
            return new UserResponse(
                    user.getId(),
                    user.getUsername(),
                    user.getCreatedAt()
            );
        }
    }
