package com.monteroantonio.PDAM23.controllers;

import com.monteroantonio.PDAM23.services.store.MediaUrlResource;
import com.monteroantonio.PDAM23.services.store.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final StorageService storageService;

    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> getFile (@PathVariable String filename) {
        MediaUrlResource resource = (MediaUrlResource) storageService.loadAsResource(filename);

        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", resource.getType())
                .body(resource);
    }
}
