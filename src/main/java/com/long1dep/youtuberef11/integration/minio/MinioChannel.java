package com.long1dep.youtuberef11.integration.minio;

import com.long1dep.youtuberef11.common.utils.ConverterUtils;
import com.long1dep.youtuberef11.config.properties.MinioProperties;
import com.long1dep.youtuberef11.web.rest.error.BusinessException;
import io.minio.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class MinioChannel {
    private static final String BUCKET = "resources";
    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    @PostConstruct
    private void init() {
        createBucket(BUCKET);
    }

    @SneakyThrows
    private void createBucket(final String name) {
        // Kiểm tra nếu bucket đã tồn tại
        final var found = minioClient.bucketExists(
                BucketExistsArgs.builder()
                        .bucket(name)
                        .build()
        );
        if (!found) {
            // Tạo bucket nếu chưa tồn tại
            minioClient.makeBucket(
                    MakeBucketArgs.builder()
                            .bucket(name)
                            .build()
            );

            // Thiết lập bucket là public bằng cách set policy
            final var policy = """
                        {
                          "Version": "2012-10-17",
                          "Statement": [
                           {
                              "Effect": "Allow",
                              "Principal": "*",
                              "Action": "s3:GetObject",
                              "Resource": "arn:aws:s3:::%s/*"
                            }
                          ]
                        }
                    """.formatted(name);
            minioClient.setBucketPolicy(
                    SetBucketPolicyArgs.builder().bucket(name).config(policy).build()
            );
        } else {
            log.info("Bucket %s đã tồn tại.".formatted(name));
        }
    }

    @SneakyThrows
    public String upload(@NonNull final MultipartFile file) {
        log.info("Bucket: {}, file size: {}", BUCKET, file.getSize());

        if(file.isEmpty()) {
            throw new BusinessException("400", "File upload is empty");
        }

        if(file.getSize() > 5 * 1024 * 1024) {
            throw new BusinessException("400", "File upload is too large (Limit 5MB)");
        }

        final String originalFileName = file.getOriginalFilename();
        final String extension = StringUtils.getFilenameExtension(originalFileName);
        if(extension == null || !List.of("png", "jpg", "jpeg").contains(extension.toLowerCase())) {
            throw new BusinessException("400", "File upload is invalid. Just allow file with extension: .png, .jpg, .jpeg");
        }

        final String fileName = UUID.randomUUID().toString() + "." + extension.toLowerCase();
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(BUCKET)
                            .object(fileName)
                            .contentType(Objects.isNull(file.getContentType()) ? "image/png; image/jpg;" : file.getContentType())
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .build()
            );
        } catch (Exception ex) {
            log.error("Error saving image \n {} ", ex.getMessage());
            throw new BusinessException("400", "Unable to upload file", ex);
        }
        return minioProperties.getUrl() + "/" + BUCKET + "/" + fileName;
    }
    
    public byte[] download(String bucket, String name) {
        try (GetObjectResponse inputStream = minioClient.getObject(GetObjectArgs.builder()
                .bucket(bucket)
                .object(name)
                .build())) {
            String contentLength = inputStream.headers().get(HttpHeaders.CONTENT_LENGTH);
            int size = StringUtils.isEmpty(contentLength) ? 0 : Integer.parseInt(contentLength);
            return ConverterUtils.readBytesFromInputStream(inputStream, size);
        } catch (Exception e) {
            throw new BusinessException("400", "Unable to download file", e);
        }
    }

    @SneakyThrows
    public void deleteFile(String fileUrl) {
        if(!StringUtils.hasText(fileUrl)) {
            return;
        }

        try {
            String[] parts = fileUrl.split("/");
            String objectName = parts[parts.length - 1];

            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(BUCKET)
                            .object(objectName)
                            .build()
            );
            log.info("Delete success file {}.", objectName);
        }
        catch (Exception ex) {
            log.error("Error deleting file {}.", fileUrl, ex);
        }
    }
}
