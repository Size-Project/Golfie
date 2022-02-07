package com.golfie.common.util;

import org.apache.http.entity.ContentType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtils {
    public static final String FILE_KEY = "feedImages";

    public static MockMultipartFile fileToMultipart(File file) {
        try {
            return new MockMultipartFile(
                    FILE_KEY,
                    file.getName(),
                    ContentType.IMAGE_JPEG.getMimeType(),
                    Files.readAllBytes(file.toPath())
            );
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
