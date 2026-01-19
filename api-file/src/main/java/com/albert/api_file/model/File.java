package com.albert.api_file.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "files")
public class File {

    @Id
    private final UUID id = UUID.randomUUID();

    @Column(unique = true)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int size;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(nullable = false)
    private Date created_At;

    public File(String title, String content, User user){
        this.title = title;
        this.content = content;
        this.user =  user;
    }
}
