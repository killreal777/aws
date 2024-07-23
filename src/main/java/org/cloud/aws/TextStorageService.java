package org.cloud.aws;

public interface TextStorageService {

    void upload(String key, String content);

    String download(String key);

    void delete(String key);

}
