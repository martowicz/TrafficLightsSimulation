package com.example.TrafficLightsSimulation.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CommandList(List<Command> commands) {
    @JsonCreator
    public CommandList(@JsonProperty("commands") List<Command> commands) {
        this.commands = commands;
    }
}
