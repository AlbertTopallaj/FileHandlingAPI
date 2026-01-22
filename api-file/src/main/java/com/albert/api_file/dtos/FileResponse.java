package com.albert.api_file.dtos;

import com.albert.api_file.models.File;
import com.albert.api_file.models.Folder;
import com.albert.api_file.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class FileResponse {

    private final UUID id;
    private String title;
    private String content;
    private User owner;
    private Folder folder;
    private Date createdAt;

    public FileResponse(UUID id, String title, String content, User owner, Folder folder, Date createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.owner = owner;
        this.folder = folder;
        this.createdAt = createdAt;
    }

    public static FileResponse fromModel(File file){
        return new FileResponse(
                file.getId(),
                file.getTitle(),
                file.getContent(),
                file.getOwner(),
                file.getFolder(),
                file.getCreatedAt()
        );
    }
}
