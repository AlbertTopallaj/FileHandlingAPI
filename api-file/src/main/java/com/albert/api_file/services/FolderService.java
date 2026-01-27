package com.albert.api_file.services;

import com.albert.api_file.dtos.CreateFolderRequest;
import com.albert.api_file.models.Folder;
import com.albert.api_file.repositories.IFolderRepository;
import com.albert.api_file.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FolderService {

    private final IFolderRepository folderRepository;
    private final IUserRepository userRepository;

    public Folder createFolder(CreateFolderRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(IllegalArgumentException::new);

        var folder = new Folder(request.getName());
        folder = folderRepository.save(folder);

        return folder;
    }

    public Folder getAllFolders(){
        return (Folder) folderRepository.findAll();
    }

    public Folder deleteFolderById(UUID request) {
        var folder = folderRepository.findById(request.getId());

        return folderRepository.delete(folder);
    }
}
