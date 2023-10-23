package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.List;

public class Simulation {
    private List<Animal> animals = new java.util.ArrayList<>();
    private List<MoveDirection> moves;

    public Simulation(List<MoveDirection> moves, List<Vector2d> positions) {
        this.moves = moves;
        for (Vector2d position : positions) {
            this.animals.add(new Animal(position));
        }
    }

    public void run() {
        int i = 0;
        for (MoveDirection move : this.moves) {
            this.animals.get(i % this.animals.size()).move(move);
            System.out.println("Zwierze "+(i%this.animals.size())+":"+this.animals.get(i % this.animals.size()));
            i++;
        }
    }
}
