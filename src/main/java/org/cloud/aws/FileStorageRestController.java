package org.cloud.aws;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileStorageRestController {
    private final FileStorageService fileStorageService;

    @PutMapping
    public ResponseEntity<String> put(@RequestParam MultipartFile file) {
        String key = Integer.toString(file.hashCode());
        fileStorageService.upload(key, file);
        return new ResponseEntity<>(key, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ByteArrayResource> get(@RequestParam String key) {
        ByteArrayResource file = fileStorageService.download(key);
        return new ResponseEntity<>(file, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam String key) {
        fileStorageService.delete(key);
        return new ResponseEntity<>("Delete success", HttpStatus.OK);
    }

}
