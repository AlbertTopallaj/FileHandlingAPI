package com.albert.api_file.services;

import com.albert.api_file.dtos.CreateFolderRequest;
import com.albert.api_file.dtos.DeleteFolderRequest;
import com.albert.api_file.models.Folder;
import com.albert.api_file.models.User;
import com.albert.api_file.repositories.IFolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderService {

    private final IFolderRepository folderRepository;

    public Folder createFolder(CreateFolderRequest request, User user) {
        var folder = new Folder(request.getName());
        folder.setOwner(user);


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

    public void deleteFolder(DeleteFolderRequest request){
        folderRepository.deleteById(request.getId());
    }
}
