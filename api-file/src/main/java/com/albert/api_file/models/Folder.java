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
    private int files;

    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;

    @Column(nullable = false)
    private LocalDateTime created_At;

    public Folder(String name){
        this.name = name;
        this.owner = owner;
        this.files = files;
        this.created_At = LocalDateTime.now();
    }
}
