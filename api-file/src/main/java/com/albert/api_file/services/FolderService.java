package com.albert.api_file.services;

import com.albert.api_file.dtos.CreateFolderRequest;
import com.albert.api_file.dtos.DeleteFolderRequest;
import com.albert.api_file.models.Folder;
import com.albert.api_file.repositories.IFileRepository;
import com.albert.api_file.repositories.IFolderRepository;
import com.albert.api_file.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    /*
    public void deleteFolderByName(DeleteFolderRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(IllegalArgumentException::new);

        var folder = folderRepository.findByName(request.getName());

        folderRepository.delete(folder);
    }

     */
}
