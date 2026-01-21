package com.albert.api_file.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteFolderRequest {
    private String username;
    private String password;
    private String name;
}
