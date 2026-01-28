package com.albert.api_file.services;

import com.albert.api_file.dtos.UploadFileRequest;
import com.albert.api_file.models.File;
import com.albert.api_file.models.User;
import com.albert.api_file.repositories.IFileRepository;
import com.albert.api_file.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    private final IFileRepository fileRepository;

    public File createFile(UploadFileRequest request, User user){
        var file = new File(request.getTitle(), request.getContent());
        file.setOwner(user);
        file = fileRepository.save(file);

        return file;
    }

    public List<File> getAllFiles(User user){
        return fileRepository.findAllByOwner(user);
    }
}
