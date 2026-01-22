package com.albert.api_file.services;

import com.albert.api_file.dtos.UploadFileRequest;
import com.albert.api_file.models.File;
import com.albert.api_file.repositories.IFileRepository;
import com.albert.api_file.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    private final IUserRepository userRepository;
    private final IFileRepository fileRepository;

    public File createFile(UploadFileRequest request){
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(IllegalArgumentException::new);

        var file = new File(request.getTitle(), request.getContent());
        file.setOwner(user);
        file.setCreatedAt(new Date());
        file = fileRepository.save(file);

        return file;
    }

    public List<File> getAllFiles(){
        return fileRepository.findAll();
    }
}
