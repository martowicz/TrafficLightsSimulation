package com.example.TrafficLightsSimulation.spring_boot;

import com.example.TrafficLightsSimulation.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrafficService {

    public StatusList processCommands(CommandList commandList) {
        List<Status> statusesList = new ArrayList<>();
        Junction junction = new Junction();

        for (Command c : commandList.commands()) {
            if (c.isAddingNewVehicle()) {
                Vehicle vehicle = new Vehicle(c.getName(), c.getStartingPosition(), c.getEndingPosition());
                junction.placeVehicle(vehicle);
            } else {
                statusesList.add(new Status(junction.handleTraffic()));
            }
        }
        return new StatusList(statusesList);
    }
}
