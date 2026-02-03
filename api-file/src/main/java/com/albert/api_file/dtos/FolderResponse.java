package com.albert.api_file.dtos;

import com.albert.api_file.models.File;
import com.albert.api_file.models.Folder;
import com.albert.api_file.models.User;
import com.albert.api_file.utilites.DateFormatterUtility;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class FolderResponse {

    private final UUID id;
    private String name;
    private List<FileResponse> files;
    private String owner;
    private String createdAt;

    public FolderResponse(UUID id, String name, List<FileResponse> files, String owner, String createdAt) {
        this.id = id;
        this.name = name;
        this.files = files;
        this.owner = owner;
        this.createdAt = createdAt;
    }

    public static FolderResponse fromModel(Folder folder){

        List<FileResponse> fileResponses = folder.getFiles().stream()
                .map(FileResponse::fromModel)
                .toList();

        return new FolderResponse(
                folder.getId(),
                folder.getName(),
                fileResponses,
                folder.getOwner().getUsername(),
                folder.getCreated_At().format(DateFormatterUtility.DATE_TIME_FORMATTER)
        );
    }
}
