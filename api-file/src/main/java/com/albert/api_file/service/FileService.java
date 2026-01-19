package com.albert.api_file.service;

import com.albert.api_file.dto.request.UploadFileRequest;
import com.albert.api_file.model.File;
import com.albert.api_file.repository.FileRepository;
import com.albert.api_file.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final UserRepository userRepository;

    public File createFile(UploadFileRequest request) {
        if (request.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title may not be blank");
        }
        var user = userRepository.findByUsername(request.getUsername());

        var file = new File(request.getTitle(), request.getContent(), user);
        fileRepository.save(file);
        return file;
    }

    public List<File> getAllFiles(){
        return fileRepository.findAll();
    }
}
