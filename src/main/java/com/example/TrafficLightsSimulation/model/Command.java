package com.example.TrafficLightsSimulation.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Command {

    private final String type;
    private final String vehicleName;
    private final String startRoad;
    private final String endRoad;



    @JsonCreator
    public Command(
            @JsonProperty("type") String type,
            @JsonProperty("vehicleId") String vehicleId,
            @JsonProperty("startRoad") String startRoad,
            @JsonProperty("endRoad") String endRoad) {
        this.type = type;
        this.vehicleName = vehicleId;
        this.startRoad = startRoad;
        this.endRoad = endRoad;
    }

    @Override
    public String toString(){
        return type + " " + vehicleName + " ";
    }

    public boolean isAddingNewVehicle(){
        return type.equals("addVehicle");
    }

    public String getStartingPosition(){
        return startRoad;
    }

    public String getEndingPosition(){
        return endRoad;
    }

    public String getName(){
        return vehicleName;
    }

}