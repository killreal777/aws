package org.cloud.aws;

import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import org.springframework.util.StreamUtils;

@Service
@RequiredArgsConstructor
public class TextStorageServiceAws implements TextStorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    private final S3Client s3Client;

    @Override
    public void upload(String key, String content) {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        s3Client.putObject(request, RequestBody.fromString(content));
    }

    @Override
    @SneakyThrows
    public String download(String key) {
        ResponseInputStream<GetObjectResponse> response = s3Client.getObject(
        request -> request.bucket(bucketName).key(key));
        return StreamUtils.copyToString(response, StandardCharsets.UTF_8);
    }

    @Override
    public void delete(String key) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        s3Client.deleteObject(deleteObjectRequest);
    }

}

