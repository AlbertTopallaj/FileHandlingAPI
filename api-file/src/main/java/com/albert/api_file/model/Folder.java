package com.albert.api_file.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Folder {

    @Id
    private final UUID id = UUID.randomUUID();

    @Column(unique = true)
    private String name;

    @Column
    private int content;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(nullable = false)
    private LocalDateTime created_At;

    public Folder(String name, int content, User user, LocalDateTime created_At){
        this.name = name;
        this.content = content;
        this.user = user;
        this.created_At = created_At;
    }
}
