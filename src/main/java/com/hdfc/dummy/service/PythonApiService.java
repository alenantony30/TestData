package com.hdfc.dummy.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PythonApiService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public PythonApiService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public String getFullName() {
        String url = "http://localhost:5000/get-full-name";  // Python API URL

        // Call the Python Flask API and get the response
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                // Parse the JSON response to extract "last_name"
                JsonNode jsonNode = objectMapper.readTree(response.getBody());
                return jsonNode.get("full_name").asText();  // Return last name
            } else {
                return "Error: Unable to fetch last name, status code: " + response.getStatusCode();
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
