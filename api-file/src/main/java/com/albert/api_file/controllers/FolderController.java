package com.albert.api_file.controllers;

import com.albert.api_file.dtos.CreateFolderRequest;
import com.albert.api_file.dtos.DeleteFolderRequest;
import com.albert.api_file.dtos.FileResponse;
import com.albert.api_file.dtos.FolderResponse;
import com.albert.api_file.models.Folder;
import com.albert.api_file.models.User;
import com.albert.api_file.services.FileService;
import com.albert.api_file.services.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<?> createFolder(@RequestBody CreateFolderRequest createFolderRequest, @AuthenticationPrincipal User user){
        try {
            var folder = folderService.createFolder(createFolderRequest, user);
            return ResponseEntity.status(HttpStatus.CREATED).body(FolderResponse.fromModel(folder));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", exception.getMessage()));
        }
    }

    @GetMapping("/all")
    public List<FolderResponse> getAllFolders(@AuthenticationPrincipal User user){
        return folderService.getAllFolders(user)
                .stream()
                .map(FolderResponse::fromModel)
                .toList();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteFolder(@AuthenticationPrincipal User user, @RequestBody DeleteFolderRequest request){
        try {
            folderService.deleteFolder(request);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException exception){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FolderResponse> getFolderById(@PathVariable UUID id, @AuthenticationPrincipal User user) {
        try {
            Folder folder = folderService.getFolderById(id, user);
            return ResponseEntity.ok(FolderResponse.fromModel(folder));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}
