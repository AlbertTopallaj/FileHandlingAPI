package com.albert.api_file.services;

import com.albert.api_file.dtos.CreateFolderRequest;
import com.albert.api_file.models.Folder;
import com.albert.api_file.models.User;
import com.albert.api_file.repositories.IFolderRepository;
import com.albert.api_file.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FolderService {

    private final IFolderRepository folderRepository;
    private final IUserRepository userRepository;

    public Folder createFolder(CreateFolderRequest request) {
        var folder = new Folder(request.getName());


        if (request.getName() == null){
            System.out.println("There is no given name for the folder");
        }

        folder = folderRepository.save(folder);
        System.out.println("Mappen sparades " + request.getName());
        return folder;
    }

    public List<Folder> getAllFolders(User user) {
        return folderRepository.findAllByOwner(user);
    }
}
