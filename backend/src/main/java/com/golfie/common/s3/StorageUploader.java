package com.golfie.common.s3;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StorageUploader {
    List<String> uploadFeedImages(Long userId, List<MultipartFile> multipartFiles) throws IOException;
}
