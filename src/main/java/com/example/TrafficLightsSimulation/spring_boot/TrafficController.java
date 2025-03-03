package com.example.TrafficLightsSimulation.spring_boot;


import com.example.TrafficLightsSimulation.model.CommandList;
import com.example.TrafficLightsSimulation.model.StatusList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/simulation")
public class TrafficController {

    private final TrafficService trafficService;

    public TrafficController(TrafficService trafficService) {
        this.trafficService = trafficService;
    }

    @PostMapping("/run")
    public ResponseEntity<StatusList> runTraffic(@RequestBody CommandList commandList) {
        StatusList result = trafficService.processCommands(commandList);
        return ResponseEntity.ok(result);
    }

}
