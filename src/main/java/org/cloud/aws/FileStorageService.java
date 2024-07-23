package org.cloud.aws;

import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    void upload(String key, MultipartFile file);

    @SneakyThrows
    ByteArrayResource download(String key);

    void delete(String key);
}
