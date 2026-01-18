package com.albert.api_file.controller;

import com.albert.api_file.service.FileService;
import com.albert.api_file.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;
    private final FolderService folderService;

    @PostMapping
    public ResponseEntity<?> createFile(@RequestBody CreateFileRequest request){
        try {
            var file = fileService.createFile(file);

            return ResponseEntity.created(URI.create("/file")).body(FileResponse.from(file));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", exception.getMessage()));
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Unexcepted error, try again later"));
        }
    }

    @GetMapping("/all")
    public List<FileResponse> getAllFiles() {
        return fileService.getAllFiles.stream().map(FileResponse::from).toList();
    }
}
