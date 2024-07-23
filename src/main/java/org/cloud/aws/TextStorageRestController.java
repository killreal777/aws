package org.cloud.aws;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/text")
@RequiredArgsConstructor
public class TextStorageRestController {
    private final TextStorageService textStorageService;

    @PutMapping
    public ResponseEntity<String> put(@RequestParam String text) {
        String key = Integer.toString(text.hashCode());
        textStorageService.upload(key, text);
        return new ResponseEntity<>(key, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<String> get(@RequestParam String key) {
        String text = textStorageService.download(key);
        return new ResponseEntity<>(text, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam String key) {
        textStorageService.delete(key);
        return new ResponseEntity<>("Delete success", HttpStatus.OK);
    }

}
