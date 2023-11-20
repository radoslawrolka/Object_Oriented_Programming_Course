package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.Collections;
import java.util.List;

public class Simulation {
    private final List<Animal> animals = new java.util.LinkedList<>();
    private final List<MoveDirection> moves;
    private final WorldMap map;

    public Simulation(List<MoveDirection> moves, List<Vector2d> positions, WorldMap map) {
        this.moves = moves;
        this.map = map;
        for (Vector2d position : positions) {
            Animal animal = new Animal(position);
            if (map.place(animal)) {
                this.animals.add(animal);

            }
        }
    }

    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(this.animals);
    }

    public List<MoveDirection> getMoves() {
        return Collections.unmodifiableList(this.moves);
    }

    public void run() {
        int i = 0;
        for (MoveDirection move : this.moves) {
            int index = i % this.animals.size();
            map.move(this.animals.get(index), move);
            i++;
        }
    }
}
