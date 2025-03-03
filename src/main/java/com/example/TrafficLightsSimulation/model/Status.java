package com.example.TrafficLightsSimulation.model;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Status {
    private List<Vehicle> leftVehicles;

    @JsonCreator
    public Status(
            @JsonProperty("leftVehicles") List<Vehicle> leavingVehicles){
        this.leftVehicles = leavingVehicles;
    }

    public List<String> getLeftVehicles() {
        return leftVehicles.stream()
                .map(Vehicle::toString)
                .collect(Collectors.toList());
    }



}
