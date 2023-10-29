package com.sep29th.rest.webservices.restfultwebservices.aws;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class S3Resource {

    private final S3Service s3Service;
    private final S3Buckets s3Buckets;

    public S3Resource(S3Service s3Service, S3Buckets s3Buckets) {
        this.s3Service = s3Service;
        this.s3Buckets = s3Buckets;
    }

    @PostMapping(path = "/upload-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            this.s3Service.putObject(this.s3Buckets.getName(), "user/images/test-file", file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(path = "/download-file")
    public byte[] downloadFile() {
        return this.s3Service.getObject(this.s3Buckets.getName(), "user/images/test-file");
    }
}
