package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractWorldMap implements WorldMap<WorldElement, Vector2d> {
    protected final Map<Vector2d, Animal> animals = new HashMap<>();
    protected List<MapChangeListener> observers = new ArrayList<>();

    @Override
    public boolean canMoveTo(Vector2d position) {
        Optional<WorldElement> elementOptional = objectAt(position);
        return elementOptional.map(element -> !(element instanceof Animal)).orElse(true);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position).isPresent();
    }

    @Override
    public boolean place(WorldElement object) {
        try {
            if (canMoveTo(object.getPosition())) {
                if (object instanceof Animal) {
                    animals.put(object.getPosition(), (Animal) object);
                    notifyObservers("Dodano zwierze na pozycji " + object.getPosition());
                }
                return true;
            }
            else {
                throw new PositionAlreadyOccupiedException(object.getPosition());
            }

        } catch (PositionAlreadyOccupiedException e) {
            System.err.println("Błąd: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Optional<WorldElement> objectAt(Vector2d position) {
        return Optional.ofNullable(animals.get(position));
    }

    @Override
    public void move(WorldElement object, MoveDirection direction) {
        Vector2d oldPosition = object.getPosition();
        if (object instanceof Animal) {
            MapDirection oldOrientation =  ((Animal) object).getOrientation();
            ((Animal) object).move(this, direction);
            Vector2d newPosition = object.getPosition();
            if (oldPosition != newPosition) {
                animals.remove(oldPosition);
                animals.put(newPosition, (Animal) object);
                notifyObservers("Zwierze przemiescilo sie z " + oldPosition + " na " + newPosition);
            }
            else if (oldOrientation != ((Animal) object).getOrientation()) {
                notifyObservers("Zwierze zmienilo orientacje z " + oldOrientation + " na " + ((Animal) object).getOrientation() + " na pozycji " + object.getPosition());
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

    public String getId() {
        return Integer.toString(this.hashCode());
    }

    public List<WorldElement> getOrderedAnimals() {
        return animals.values().stream()
                .sorted(Comparator
                        .comparingInt((WorldElement a) -> a.getPosition().getX())
                        .thenComparingInt(a -> a.getPosition().getY()))
                .collect(Collectors.toList());
    }
}
