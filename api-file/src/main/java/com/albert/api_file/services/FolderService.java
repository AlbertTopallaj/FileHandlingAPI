package com.albert.api_file.services;

import com.albert.api_file.dtos.CreateFolderRequest;
import com.albert.api_file.dtos.DeleteFolderRequest;
import com.albert.api_file.models.Folder;
import com.albert.api_file.models.User;
import com.albert.api_file.repositories.IFileRepository;
import com.albert.api_file.repositories.IFolderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FolderService {

    private final IFolderRepository folderRepository;
    private final IFileRepository fileRepository;

    public Folder createFolder(CreateFolderRequest request, User user) {
        var folder = new Folder(request.getName());
        folder.setOwner(user);


        if (request.getName() == null){
            System.out.println("There is no given name for the folder");
        }

        folder = folderRepository.save(folder);
        log.info("Mappen sparades {name}", request.getName());
        return folder;
    }

    public List<Folder> getAllFolders(User user) {
        return folderRepository.findAllByOwner(user);
    }

    @Transactional
    public void deleteFolder(DeleteFolderRequest request){
        Folder folder = folderRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("Folder not found"));

        fileRepository.deleteAllByFolder(folder);

        folderRepository.delete(folder);

    }

    public Folder getFolderById(UUID id, User user){
        return folderRepository.findByIdAndOwner(id, user)
                .orElseThrow(() -> new IllegalArgumentException("Folder not found"));
    }
}
