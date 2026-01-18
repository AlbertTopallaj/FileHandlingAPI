package com.albert.api_file.service;

import com.albert.api_file.model.File;
import com.albert.api_file.repository.FileRepository;
import com.albert.api_file.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final UserRepository userRepository;

    public File createFile(CreateFileRequest request){
        if (request.getTitle.isBlank()) {
            throw new IllegalArgumentException("Title may not be blank");
        }
    }

}
