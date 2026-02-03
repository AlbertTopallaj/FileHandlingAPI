package com.albert.api_file.services;

import com.albert.api_file.dtos.DeleteFileRequest;
import com.albert.api_file.dtos.UploadFileRequest;
import com.albert.api_file.models.File;
import com.albert.api_file.models.Folder;
import com.albert.api_file.models.User;
import com.albert.api_file.repositories.IFileRepository;
import com.albert.api_file.repositories.IFolderRepository;
import com.albert.api_file.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final IFileRepository fileRepository;
    private final IFolderRepository folderRepository;

    public File createFile(UploadFileRequest request, User user){
        Folder folder = folderRepository.findById(UUID.fromString(request.getFolderId()))
                .orElseThrow(() -> new IllegalArgumentException("Folder not found"));

        var file = new File();
        file.setTitle(request.getTitle());
        file.setContent(request.getContent());
        file.setOwner(user);
        file.setFolder(folder);
        file.setCreatedAt(LocalDateTime.now());
        file.setSize(request.getContent().length());

        fileRepository.save(file);

        return file;
    }

    public List<File> getAllFiles(User user){
        return fileRepository.findAllByOwner(user);
    }

    public void deleteFile(DeleteFileRequest request){
        fileRepository.deleteById(request.getId());
    }
}
