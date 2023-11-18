package agh.ics.oop.model;

import agh.ics.oop.Simulation;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulationEngine {
    private final List<Simulation> simulations;

    public SimulationEngine(List<Simulation> simulations) {
        this.simulations = simulations;
    }

    public void runSync() {
        for (Simulation simulation : simulations) {
            simulation.run();
        }
    }

    public void runASync() {
        for (Simulation simulation : simulations) {
            new Thread(simulation::run).start();
        }
    }

}
