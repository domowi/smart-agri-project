package com.smartagri.fileupload.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "url")
public class URLProperties {

    private String modelTrainAPI;
    private String modelPredictAPI;

    public String getModelTrainAPI() {
        return modelTrainAPI;
    }

    public void setModelTrainAPI(String modelTrainAPI) {
        this.modelTrainAPI = modelTrainAPI;
    }

    public String getModelPredictAPI() {
        return modelPredictAPI;
    }

    public void setModelPredictAPI(String modelPredictAPI) {
        this.modelPredictAPI = modelPredictAPI;
    }

}
