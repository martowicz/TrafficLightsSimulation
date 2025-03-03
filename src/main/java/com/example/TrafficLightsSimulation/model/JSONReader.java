package com.example.TrafficLightsSimulation.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.example.TrafficLightsSimulation.TrafficLightsSimulationApplication;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONReader {

    ObjectMapper objectMapper = new ObjectMapper();

    public JSONReader(){

    }


    public CommandList readFile(String fileName) throws StreamReadException, DatabindException, IOException{
        InputStream inputStream = TrafficLightsSimulationApplication.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream==null){
            throw new FileNotFoundException("Plik "+fileName+" nie został znaleziony");
        }
        return objectMapper.readValue(inputStream, CommandList.class);
    }




}
