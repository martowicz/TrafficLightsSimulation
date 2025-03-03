package com.example.TrafficLightsSimulation.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;

public class JSONWriter {
    private final ObjectMapper objectMapper;

    public JSONWriter() {
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public void writeToFile(String fileName, StatusList result) throws IOException {
        objectMapper.writeValue(new File("src/main/resources/outputs/"+fileName), result);
        System.out.println("Dane zapisane do " + fileName);
    }
}

