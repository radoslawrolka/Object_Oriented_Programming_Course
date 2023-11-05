package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractWorldMap implements WorldMap<WorldElement, Vector2d> {
    protected final Map<Vector2d, Animal> animals = new HashMap<>();

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(isOccupied(position) && objectAt(position) instanceof Animal);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
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
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
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
    public List<WorldElement> getElements() {
        List<WorldElement> elements = new ArrayList<>(animals.values());
        return elements;
    }
}
