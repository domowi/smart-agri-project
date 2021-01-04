package com.smartagri.fileupload.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {

    private String uploadDir;
    private String uploadTrainDir;
    private String uploadPredictDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public String getUploadTrainDir() {
        return uploadTrainDir;
    }

    public void setUploadTrainDir(String uploadTrainDir) {
        this.uploadTrainDir = uploadTrainDir;
    }

    public String getUploadPredictDir() {
        return uploadPredictDir;
    }

    public void setUploadPredictDir(String uploadPredictDir) {
        this.uploadPredictDir = uploadPredictDir;
    }

}
