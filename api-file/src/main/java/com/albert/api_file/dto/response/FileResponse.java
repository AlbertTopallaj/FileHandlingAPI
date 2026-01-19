package com.albert.api_file.dto.response;

import com.albert.api_file.model.File;
import com.albert.api_file.repository.FileRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class FileResponse {
    private final UUID id;
    private String title;
    private Date created_At;
    private UUID userId;

    public static FileResponse from(File file){
        return new FileResponse(
                file.getId(),
                file.getTitle(),
                file.getCreated_At(),
                file.getUser().getId()
        );
    }
}
