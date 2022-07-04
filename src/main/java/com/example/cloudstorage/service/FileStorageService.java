package com.example.cloudstorage.service;

import com.example.cloudstorage.Mapper.GuarantorMapper;
import com.example.cloudstorage.response.sendResponse;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Objects;

@Service
@Slf4j
public class FileStorageService {

    @Autowired
    GuarantorMapper mapper;

    private Environment environment;

    private StorageOptions storageOptions;

    public final String DOCUMENT_URL = "https://console.firebase.google.com/project/document-storage-b1776/storage/document-storage-b1776.appspot.com/files";

    private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of("dummy", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("./dummyAccountKey.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format(DOCUMENT_URL, URLEncoder.encode(fileName, String.valueOf(StandardCharsets.UTF_8)));
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public Object upload(MultipartFile multipartFile,String type) {

        try {
            String fileName = multipartFile.getOriginalFilename();
            fileName = generateFileName(multipartFile);
            File file = this.convertToFile(multipartFile, fileName);
            String TEMP_URL = this.uploadFile(file, fileName);
            file.delete();
            mapper.uploadDocuments(fileName,type,1);
            return new sendResponse("Successfully Uploaded !", TEMP_URL);
        } catch (Exception e) {
            e.printStackTrace();
            return new sendResponse("500", "Unsuccessfully Uploaded!");
        }

    }

    public Object download(String fileName) throws IOException {
        String destFileName = fileName.split("_")[1];     // to set random strinh for destination file name
        String destFilePath = "Z:\\New folder\\" + destFileName;                                    // to set destination file path

        ////////////////////////////////   Download  ////////////////////////////////////////////////////////////////////////
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("./serviceAccountKey.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        Blob blob = storage.get(BlobId.of("document-storage-b1776.appspot.com", fileName));
        blob.downloadTo(Paths.get(destFilePath));
        return new sendResponse("200", "Successfully Downloaded!");
    }

    public String generateFileName(MultipartFile file) {
        return new Date().getTime() + "_" + Objects.requireNonNull(file.getOriginalFilename())
                .toLowerCase()
                .replace(" ", "_");
    }

}
