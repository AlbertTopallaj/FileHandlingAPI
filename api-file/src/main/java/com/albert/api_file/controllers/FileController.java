package com.albert.api_file.controllers;

import com.albert.api_file.dtos.DeleteFileRequest;
import com.albert.api_file.dtos.UploadFileRequest;
import com.albert.api_file.dtos.FileResponse;
import com.albert.api_file.models.File;
import com.albert.api_file.models.User;
import com.albert.api_file.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping
    public ResponseEntity<?> uploadFile(@RequestBody UploadFileRequest request, @AuthenticationPrincipal User user) {
        try {
            var file = fileService.createFile(request, user);

            return ResponseEntity.created(URI.create("/file")).body(FileResponse.fromModel(file));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", exception.getMessage()));
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(Map.of("error", "Unexcepted error, try again later"));
        }
    }

    @GetMapping("/all")
    public List<FileResponse> getAllFiles(@AuthenticationPrincipal User user) {
        return fileService.getAllFiles(user)
                .stream()
                .map(FileResponse::fromModel)
                .toList();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteFile(@RequestBody DeleteFileRequest request, @AuthenticationPrincipal User user) {
        try {
            fileService.deleteFile(request);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileResponse> getFileById(@PathVariable UUID id, @AuthenticationPrincipal User user) {
        try {
            File file = fileService.getFileById(id, user);
            return ResponseEntity.ok(FileResponse.fromModel(file));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}
