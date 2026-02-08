package com.albert.api_file.services;

import com.albert.api_file.dtos.DeleteFileRequest;
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
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final IFileRepository fileRepository;
    private final IFolderRepository folderRepository;

    /**
     * Saves a multipart file to the database with the including metadata.
     * The file content is stored in bytes with the information such as
     * owner, folder, size and even the time it was created
     *
     * @param file   the multipart file to be saved
     * @param owner  the user who owns this file
     * @param folder the folder where the file will be stored
     * @throws Exception if the file cannot be read or saved to the database
     */

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
        } catch (Exception exception) {
            throw new RuntimeException("Could not save the file", exception);
        }

    }

    /**
     * Get all files owned by a specific user
     *
     * @param user the user whose files should be from
     * @return a list of all files owned by a specified user
     */

    public List<File> getAllFiles(User user) {
        try {
            return fileRepository.findAllByOwner(user);
        } catch (Exception exception) {
            throw new RuntimeException();
        }
    }

    /**
     * Deletes a file from the database based on the given file ID
     *
     * @param request the delete request containing the file ID which is going to be deleted
     */

    @Transactional
    public void deleteFile(DeleteFileRequest request, User user) {
        try {
            File file = fileRepository.findByIdAndOwner(request.getId(), user)
                    .orElseThrow(() -> new IllegalArgumentException("File not found"));

            fileRepository.deleteById(file.getId());
        } catch (Exception exception) {
            throw new RuntimeException("Could not delete file: " + exception.getMessage(), exception);
        }
    }

    /**
     * Gets a specific file based on its ID if the user is the owner
     *
     * @param id   the identifier of the file
     * @param user the user requesting access to the file
     * @return the file if found and owned by the user
     * @throws IllegalArgumentException if the file is not found or the user is not the right owner
     */

    public File getFileById(UUID id, User user) {
        try {
            return fileRepository.findByIdAndOwner(id, user)
                    .orElseThrow(() -> new IllegalArgumentException("File not found"));
        } catch (Exception e) {
            throw new RuntimeException("Could not find the file by id " + id + "" + e);
        }
    }
}
