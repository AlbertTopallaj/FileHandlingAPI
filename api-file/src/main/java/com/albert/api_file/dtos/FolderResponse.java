package com.albert.api_file.dtos;

import com.albert.api_file.models.File;
import com.albert.api_file.models.Folder;
import com.albert.api_file.models.User;
import com.albert.api_file.utilites.DateFormatterUtility;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class FolderResponse {

    private final UUID id;
    private String name;
    private int content;
    private User owner;
    private String createdAt;

    public FolderResponse(UUID id, String name, int content, User owner, String createdAt) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.owner = owner;
        this.createdAt = createdAt;
    }

    public static FolderResponse fromModel(Folder folder){
        return new FolderResponse(
                folder.getId(),
                folder.getName(),
                folder.getContent(),
                folder.getOwner(),
                folder.getCreated_At().format(DateFormatterUtility.DATE_TIME_FORMATTER)
        );
    }
}
