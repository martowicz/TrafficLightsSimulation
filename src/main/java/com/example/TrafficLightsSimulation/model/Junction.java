package com.example.TrafficLightsSimulation.model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Junction {

    private EnumMap<Position, Queue<Vehicle>> trafficQueues;
    private EnumMap<Position, Integer> numberOfVehicles;
    private final List<Position> verticaList = List.of(Position.N, Position.S);
    private final List<Position> horizontalList = List.of(Position.W, Position.E);
    private boolean verticalLights = false; //red
    private boolean horizontalLights = false; //red
    private int lightTimer=0;
    private int lightDeadline=0;
    private List<Position> directionsToLetGo=null;


    public Junction(){
        trafficQueues = new EnumMap<>(Position.class);

        numberOfVehicles = new EnumMap<>(Position.class);

        // Dla każdego kierunku (N, E, S, W) przypisujemy nową pustą kolejkę
        for (Position direction : Position.values()) {
            trafficQueues.put(direction, new LinkedList<>());
            numberOfVehicles.put(direction,0);
        }
    }


    public void placeVehicle(Vehicle vehicle){
        Queue<Vehicle> q = trafficQueues.get(vehicle.getStartingPosition());
        q.add(vehicle);
        numberOfVehicles.put(vehicle.getStartingPosition(), numberOfVehicles.get(vehicle.getStartingPosition()) + 1);
    }



    public void show() {
        for (Position direction : Position.values()) {

            Queue<Vehicle> queue = trafficQueues.get(direction);

            System.out.println("Queue for " + direction + ":");
            for (Vehicle v : queue) {
                System.out.println(v);
            }
            System.out.println("----------------------");
        }
        System.out.println("=========================");
        for(Position d : Position.values()){
            System.out.println(numberOfVehicles.get(d));
        }
    }

    public List<Vehicle> handleTraffic(){

        List<Vehicle> passingVehicles = new ArrayList<>();

        if(lightTimer<lightDeadline){
            for(Position d: directionsToLetGo){
                Queue<Vehicle> q = trafficQueues.get(d);

                if(!q.isEmpty()){
                    Vehicle v = q.poll();
                    numberOfVehicles.put(v.getStartingPosition(), numberOfVehicles.get(v.getStartingPosition()) - 1);
                    passingVehicles.add(v);
                    //System.out.println("Passing vehicle: " + v);
                }
            }
            lightTimer+=1;
        }
        else{
            lightTimer=0;
            List<Position> oldDirections = directionsToLetGo;
            int max=0;
            for(Position d : Position.values()){
                if(numberOfVehicles.get(d)+numberOfVehicles.get(d.opposite())>max){
                    max=numberOfVehicles.get(d)+numberOfVehicles.get(d.opposite());
                    if(d.isVertical()){
                        directionsToLetGo=verticaList;
                    }
                    else{
                        directionsToLetGo=horizontalList;
                    }

                }
            }
            if(oldDirections==null || !oldDirections.equals(directionsToLetGo)){
                //była zmiana kierunku
                if(directionsToLetGo.equals(verticaList)){
                    verticalLights=true;
                    horizontalLights=false;
                }
                else{
                    verticalLights=false;
                    horizontalLights=true;
                }

            }
            lightDeadline=max/2;
            System.out.println("zmiana świateł. Nowy czas: " + lightDeadline);


            if(directionsToLetGo==null){
                return passingVehicles; //returning eempty List
            }
            for(Position d: directionsToLetGo){
                Queue<Vehicle> q = trafficQueues.get(d);

                if(!q.isEmpty()){
                    Vehicle v = q.poll();
                    numberOfVehicles.put(v.getStartingPosition(), numberOfVehicles.get(v.getStartingPosition()) - 1);


                    passingVehicles.add(v);
                    //System.out.println("Passing vehicle: " + v);
                }
            }
        }
        return passingVehicles;


    }



}


