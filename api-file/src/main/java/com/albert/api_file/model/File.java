package com.albert.api_file.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class File {

    @Id
    private final UUID id = UUID.randomUUID();

    @Column(unique = true)
    private String name;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int size;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(nullable = false)
    private LocalDateTime created_At;

    public File(String name, String content, int size, User user, LocalDateTime created_At) {
        this.name = name;
        this.content = content;
        this.size = size;
        this.user = user;
        this.created_At = created_At;
    }
}
