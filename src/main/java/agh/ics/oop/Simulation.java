package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.Collections;
import java.util.List;

public class Simulation {
    private final List<Animal> animals = new java.util.LinkedList<>();
    private final List<MoveDirection> moves;
    private final WorldMap map;
    private final MapVisualizer mapVisualizer;
    private final Vector2d lowerLeft = new Vector2d(0, 0);
    private final Vector2d upperRight;

    public Simulation(List<MoveDirection> moves, List<Vector2d> positions, WorldMap map, int width, int height) {
        this.moves = moves;
        this.map = map;
        this.mapVisualizer = new MapVisualizer(map);
        this.upperRight = new Vector2d(width, height);
        for (Vector2d position : positions) {
            this.animals.add(new Animal(position));
            map.place(this.animals.get(this.animals.size()-1));
        }
    }

    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(this.animals);
    }

    public List<MoveDirection> getMoves() {
        return Collections.unmodifiableList(this.moves);
    }

    public void run() {
        System.out.println(mapVisualizer.draw(lowerLeft, upperRight));
        int i = 0;
        for (MoveDirection move : this.moves) {
            int index = i % this.animals.size();
            map.move(this.animals.get(index), move);
            System.out.println("Zwierze "+index+":"+this.animals.get(index));
            i++;
            System.out.println(mapVisualizer.draw(lowerLeft, upperRight));
        }
    }
}
