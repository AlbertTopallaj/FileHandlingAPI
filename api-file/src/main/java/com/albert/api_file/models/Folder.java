package com.albert.api_file.models;

import com.albert.api_file.dtos.FileResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
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

    @OneToMany(mappedBy = "folder", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<File> files;

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
