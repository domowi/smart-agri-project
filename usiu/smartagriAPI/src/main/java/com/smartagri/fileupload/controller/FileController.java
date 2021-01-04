package com.smartagri.fileupload.controller;

import com.smartagri.fileupload.payload.UploadFileResponse;
import com.smartagri.fileupload.property.URLProperties;
import com.smartagri.fileupload.service.FileStorageService;
import com.smartagri.fileupload.service.ModelService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = { "/fileupload/" })
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private URLProperties urlProperties;

    @Value("${url.model-train-api}")
    private String modelTrainAPI;

    @Value("${url.model-predict-api}")
    private String modelPredictAPI;

    @Autowired
    private ModelService modelService;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {

        ResponseEntity<String> modelWebServiceResp = null;
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();


        // On successful file upload, invoke the predict or train flask APi to process the request
        if(StringUtils.containsIgnoreCase(fileName,"predict")){
/*
            // fetch the model train URl
            modelPredictAPI = urlProperties.getModelPredictAPI();
            logger.info("Processing sendTrainModel() :: Model Train Url: " + modelTrainAPI);*/

            // invoke the API
            modelWebServiceResp = modelService.sendPredictModel(this.modelPredictAPI);

        } else {
/*
            // fetch the model train URl
            modelTrainAPI = urlProperties.getModelTrainAPI();
            logger.info("Processing sendTrainModel() :: Model Train Url: " + modelTrainAPI);*/

            // invoke the API
            modelWebServiceResp = modelService.sendTrainModel(this.modelTrainAPI);

        }
        logger.info("Model Web Service Response: " + modelWebServiceResp);

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
