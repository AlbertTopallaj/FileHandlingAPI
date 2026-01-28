package com.albert.api_file.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity(name = "files")
@Getter
@Setter
@NoArgsConstructor
public class File {

    @Id
    private UUID id = UUID.randomUUID();

    @Column(unique = true)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int size;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    private Folder folder;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public File(String title, String content) {
        this.title = title;
        this.content = content;
        this.owner =  owner;
        this.folder = folder;
        this.createdAt = LocalDateTime.now();
    }
}
