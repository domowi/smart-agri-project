package com.smartagri.fileupload.service;

import com.smartagri.fileupload.property.URLProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class ModelService {

    private static final Logger logger = LoggerFactory.getLogger(ModelService.class);

    @Autowired
    RestTemplate restTemplate;

    /*private String modelPredictAPI;
    private String modelTrainAPI;

    @Autowired
    public ModelService(URLProperties urlProperties) {
        this.modelPredictAPI = urlProperties.getModelPredictAPI();
        this.modelTrainAPI = urlProperties.getModelTrainAPI();
    }*/

    /*
    Send Predict Model
    */
    public ResponseEntity<String> sendPredictModel(String modelPredictAPI) {

        logger.info("Processing sendPredictModel() :: Model Predict Url: " + modelPredictAPI);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity<String> result = restTemplate.exchange(modelPredictAPI, HttpMethod.GET, entity,
                    String.class);

            logger.info("sendPredictModel ::: Result: " + result);

        } catch (RestClientException e){
            logger.info("sendPredictModel ::: RestClientException " + e.getMessage());
        } catch (Exception e) {
            logger.info("sendPredictModel ::: Exception " + e.getMessage());
        }

        return new ResponseEntity<String>(HttpStatus.OK);

    }

    /*
    Send Train Model
     */
    public ResponseEntity<String> sendTrainModel(String modelTrainAPI) {

        logger.info("Processing sendTrainModel() :: Model Train Url: " + modelTrainAPI);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity < String > entity = new HttpEntity < String > ("parameters", headers);
            ResponseEntity < String > result = restTemplate.exchange(modelTrainAPI, HttpMethod.GET, entity,
                    String.class);

            logger.info("sendTrainModel ::: Result: " + result);

        } catch (RestClientException e){
            logger.info("sendPredictModel ::: RestClientException " + e.getMessage());
        } catch (Exception e) {
            logger.info("sendTrainModel ::: Exception " + e.getMessage());
        }

        return new ResponseEntity<String>(HttpStatus.OK);

    }

}
