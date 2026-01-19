package com.albert.api_file.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity(name = "folders")
@Getter
@Setter
@NoArgsConstructor
public class Folder {

    @Id
    private UUID id = UUID.randomUUID();

    @Column(unique = true)
    private String name;

    @Column
    private int content;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(nullable = false)
    private Date created_At;

    public Folder(String name, int content, User user, Date created_At){
        this.name = name;
        this.content = content;
        this.user = user;
        this.created_At = created_At;
    }
}
