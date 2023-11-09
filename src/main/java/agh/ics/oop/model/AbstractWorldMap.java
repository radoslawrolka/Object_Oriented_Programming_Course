package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap<WorldElement, Vector2d> {
    protected final Map<Vector2d, Animal> animals = new HashMap<>();
    protected List<MapChangeListener> observers = new ArrayList<>();

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
        try {
            if (canMoveTo(object.getPosition())) {
                if (object instanceof Animal) {
                    animals.put(object.getPosition(), (Animal) object);
                    notifyObservers("Dodano zwierzę na pozycji " + object.getPosition());
                }
                return true;
            }
            throw new PositionAlreadyOccupiedException(object.getPosition());
        } catch (PositionAlreadyOccupiedException e) {
            System.err.println("Błąd: " + e.getMessage());
            return false;
        }
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
                notifyObservers("Zwierzę przemieściło się z " + oldPosition + " na " + newPosition);
            }
        }
    }

    @Override
    public List<WorldElement> getElements() {
        List<WorldElement> elements = new ArrayList<>(animals.values());
        return elements;
    }

    public abstract Boundary getCurrentBounds();

    @Override
    public String toString() {
        Boundary bounds = getCurrentBounds();
        return new MapVisualizer(this).draw(bounds.lowerLeft(), bounds.upperRight());
    }

    public void addObserver(MapChangeListener observer) {
        observers.add(observer);
    }

    public void removeObserver(MapChangeListener observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (MapChangeListener observer : observers) {
            observer.mapChanged(this, message);
        }
    }
}
