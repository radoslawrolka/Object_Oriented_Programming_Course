package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.lang.Math.sqrt;

public class GrassField implements WorldMap<WorldElement, Vector2d> {
    private final int grassNumber;
    private final Map<Vector2d, Grass> grasses = new HashMap<>();
    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final Vector2d lowerLeft;
    private final Vector2d upperRight;

    public GrassField(int grassNumber) {
        this.grassNumber = grassNumber;
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d((int)sqrt(grassNumber*10), (int)sqrt(grassNumber*10));

        randomPositionGenerator generator = new randomPositionGenerator(grassNumber, lowerLeft, upperRight);
        for(Vector2d grassPosition : generator) {
            System.out.println(grassPosition);
            grasses.put(grassPosition, new Grass(grassPosition));
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    @Override
    public boolean place(WorldElement object) {
        if (canMoveTo(object.getPosition())) {
            if(object instanceof Animal) {
                animals.put(object.getPosition(), (Animal) object);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if(animals.get(position) != null) {
            return animals.get(position);
        }
        return grasses.get(position);
    }

    @Override
    public void move(WorldElement object, MoveDirection direction) {
        Vector2d oldPosition = object.getPosition();
        if (object instanceof Animal) {
            ((Animal) object).move(this, direction);
            Vector2d newPosition = object.getPosition();
            if (oldPosition != newPosition) {
                animals.remove(oldPosition);
                animals.put(newPosition, (Animal) object);
            }
        }
    }

    @Override
    public String toString() {
        Vector2d ll = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Vector2d ur = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        for (Vector2d position : animals.keySet()) {
            ll = ll.lowerLeft(position);
            ur = ur.upperRight(position);
        }
        return new MapVisualizer(this).draw(ll, ur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grassNumber, grasses);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof GrassField))
            return false;
        GrassField that = (GrassField) other;
        return this.grassNumber == that.grassNumber && this.grasses.equals(that.grasses);
    }
}
