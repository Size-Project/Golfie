package com.golfie.common.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Profile("dev")
@Component
public class S3Uploader implements StorageUploader {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public S3Uploader(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public List<String> uploadFeedImages(Long userId, List<MultipartFile> multipartFiles) throws IOException {
        List<File> uploadFiles = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            File uploadFile = convertToFile(multipartFile).orElseThrow(() ->
                    new IllegalArgumentException("파일 변환에 실패하였습니다.")
            );
            uploadFiles.add(uploadFile);
        }

        return uploadFiles.stream()
            .map(uploadFile -> {
                final String filename = "feed/images/" + userId + "/" + UUID.randomUUID() + extension(uploadFile);
                amazonS3.putObject(new PutObjectRequest(bucketName, filename, uploadFile)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
                removeFile(uploadFile);
                return amazonS3.getUrl(bucketName, filename).toString();
            }).collect(Collectors.toList());
    }

    private void removeFile(File uploadFile) {
        if (uploadFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일이 삭제되지 못했습니다.");
        }
    }

    private Optional<File> convertToFile(MultipartFile file) throws IOException {
        File convertFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        if (convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }

    private String extension(File file) {
        String[] split = file.getName().split("\\.");
        return "." + split[split.length - 1];
    }

}
