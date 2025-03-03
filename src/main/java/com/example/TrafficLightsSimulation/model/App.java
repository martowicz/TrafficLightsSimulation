package com.example.TrafficLightsSimulation.model;


import java.util.ArrayList;
import java.util.List;


public class App {

    public static void main(String[] args) {


        if (args.length < 2) {
            System.out.println("Proszę podać dwa argumenty: nazwę pliku wejściowego i nazwę pliku wyjściowego.");
            return; // Zatrzymanie programu, jeśli brak argumentów
        }

        String inputFile = args[0];
        String outputFile = args[1];

        try{
            List<Status> statusesList = new ArrayList<>();
            Junction junction = new Junction();
            JSONReader jsonReader = new JSONReader();
            JSONWriter jsonWriter = new JSONWriter();
            CommandList commandList = jsonReader.readFile(inputFile);
            for(Command c : commandList.commands()){
                if(c.isAddingNewVehicle()){
                    Vehicle vehicle = new Vehicle(c.getName(),
                            c.getStartingPosition(),
                            c.getEndingPosition());
                    junction.placeVehicle(vehicle);
                }
                else{
                    statusesList.add(new Status(junction.handleTraffic()));
                }
                //junction.show();

            }
            StatusList result = new StatusList(statusesList);
            jsonWriter.writeToFile(outputFile, result);



            //Zrobić czteroelementową listę kolejek


        } catch (Exception e){
            e.printStackTrace();
        }




    }
}
