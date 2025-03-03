package com.example.TrafficLightsSimulation.model;

public enum Position {
    N,E,S,W;

    public static Position stringToEnum(String direction){
        return switch (direction){
            case "north" -> N;
            case "south" -> S;
            case "west" -> W;
            case "east" -> E;
            default -> throw new IllegalArgumentException("No such argument");
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case N -> "^";
            case S -> "v";
            case W -> "<";
            case E -> ">";
        };

    }

    public int toNumber(){
        return switch (this) {
            case N -> 0;
            case E -> 1;
            case S -> 2;
            case W -> 3;
        };
    }

    public Position opposite(){
        return switch (this) {
            case N -> S;
            case W -> E;
            case S -> N;
            case E -> W;
        };
    }

    public boolean isVertical(){
        return this==N || this==S;
    }


}
