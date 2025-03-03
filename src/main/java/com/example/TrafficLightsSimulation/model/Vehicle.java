package com.example.TrafficLightsSimulation.model;

public class Vehicle {

    private final String name;
    private final Position startingPosition;
    private final Position endingPosition;
    private final Turn turn;



    public Vehicle(String name, String startingPosition, String endingPosition){
        this.name = name;
        this.startingPosition = Position.stringToEnum(startingPosition);
        this.endingPosition = Position.stringToEnum(endingPosition);
        this.turn = Turn.getTurn(this.endingPosition, this.startingPosition);

    }

    @Override
    public String toString(){
        return name;
    }

    public Position getStartingPosition(){
        return startingPosition;
    }

    public Position getEndingPosition(){
        return endingPosition;
    }

    public Turn getTurn(){
        return this.turn;
    }


}
