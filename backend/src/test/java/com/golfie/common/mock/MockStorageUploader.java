package com.golfie.common.mock;

import com.golfie.common.s3.StorageUploader;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Profile("test")
@Component
public class MockStorageUploader implements StorageUploader {

    @Override
    public List<String> uploadFeedImages(Long userId, List<MultipartFile> multipartFiles) throws IOException {
        return List.of("testImage.png");
    }

}
