package com.albert.api_file.dtos;

import com.albert.api_file.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CreateFolderRequest {
    private String name;
    private User owner;
}
