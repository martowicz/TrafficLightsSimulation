package com.example.TrafficLightsSimulation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record StatusList(@JsonProperty("stepStatuses") List<Status> statusList) { }
