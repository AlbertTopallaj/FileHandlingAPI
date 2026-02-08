package com.albert.api_file.controllers;

import com.albert.api_file.dtos.DeleteFileRequest;
import com.albert.api_file.dtos.FileResponse;
import com.albert.api_file.models.File;
import com.albert.api_file.models.Folder;
import com.albert.api_file.models.User;
import com.albert.api_file.services.FileService;
import com.albert.api_file.services.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;
    private final FolderService folderService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("folderId") UUID folderId, @AuthenticationPrincipal User user) {
        Folder folder = folderService.getFolderById(folderId, user);
        fileService.saveFile(file, user, folder);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("download/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable UUID id, @AuthenticationPrincipal User user) {

        File file = fileService.getFileById(id, user);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getTitle() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(file.getContent());
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllFiles(@AuthenticationPrincipal User user) {
        List<FileResponse> files = fileService.getAllFiles(user)
                .stream()
                .map(FileResponse::fromModel)
                .toList();
        return ResponseEntity.ok(files);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFile(@RequestBody DeleteFileRequest request, @AuthenticationPrincipal User user) {
        fileService.deleteFile(request, user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFileById(@PathVariable UUID id, @AuthenticationPrincipal User user) {
        File file = fileService.getFileById(id, user);
        return ResponseEntity.ok(FileResponse.fromModel(file));
    }
}
