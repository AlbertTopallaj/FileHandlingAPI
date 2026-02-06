package com.albert.api_file.services;

import com.albert.api_file.dtos.DeleteFileRequest;
import com.albert.api_file.dtos.UploadFileRequest;
import com.albert.api_file.models.File;
import com.albert.api_file.models.Folder;
import com.albert.api_file.models.User;
import com.albert.api_file.repositories.IFileRepository;
import com.albert.api_file.repositories.IFolderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final IFileRepository fileRepository;
    private final IFolderRepository folderRepository;

    @Transactional
    public void saveFile(MultipartFile file, User owner, Folder folder) {
        try {
            File fileToSave = new File();
            fileToSave.setFolder(folder);
            fileToSave.setTitle(file.getOriginalFilename());
            fileToSave.setContent(file.getBytes());
            fileToSave.setSize((int) file.getSize());
            fileToSave.setCreatedAt(LocalDateTime.now());
            fileToSave.setOwner(owner);


            fileRepository.save(fileToSave);
        } catch (IOException e) {
            throw new RuntimeException("Could not save the file", e);
        }

    }

    public List<File> getAllFiles(User user) {
        return fileRepository.findAllByOwner(user);
    }

    public void deleteFile(DeleteFileRequest request) {
        fileRepository.deleteById(request.getId());
    }

    public File getFileById(UUID id, User user) {
        return fileRepository.findByIdAndOwner(id, user)
                .orElseThrow(() -> new IllegalArgumentException("File not found"));
    }
}
