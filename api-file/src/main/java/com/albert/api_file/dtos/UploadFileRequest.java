package com.albert.api_file.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UploadFileRequest {
    private String title;
    private String content;
    private String username;
}
