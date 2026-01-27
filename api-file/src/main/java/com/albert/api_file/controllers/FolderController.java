package com.albert.api_file.controllers;

import com.albert.api_file.dtos.CreateFolderRequest;
import com.albert.api_file.dtos.DeleteFolderRequest;
import com.albert.api_file.dtos.FolderResponse;
import com.albert.api_file.models.Folder;
import com.albert.api_file.services.FileService;
import com.albert.api_file.services.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/folder")
@RequiredArgsConstructor
public class FolderController {

    private final FolderService folderService;

    @PostMapping
    public ResponseEntity<?> createFolder(@RequestBody CreateFolderRequest createFolderRequest){
        try {
            var folder = folderService.createFolder(createFolderRequest);
            return ResponseEntity.created(URI.create("/file")).body(folder);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", exception.getMessage()));
        }
    }

    @GetMapping("/all")
    public List<Folder> getAllFolders(){
        return (List<Folder>) folderService.getAllFolders();
    }

    @DeleteMapping("/{folderId}")
    public ResponseEntity<?> deleteFolder(@PathVariable UUID folderId, @RequestBody DeleteFolderRequest deleteFolderRequest){
        var folder = folderService.deleteFolderById(deleteFolderRequest.getId());
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

}
