package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap {
    private final int grassNumber;
    private final Map<Vector2d, Grass> grasses = new HashMap<>();

    public GrassField(int grassNumber) {
        super();
        this.grassNumber = grassNumber;

        randomPositionGenerator generator = new randomPositionGenerator(grassNumber, new Vector2d(0, 0), new Vector2d((int)sqrt(grassNumber*10), (int)sqrt(grassNumber*10)));
        for(Vector2d grassPosition : generator) {
            grasses.put(grassPosition, new Grass(grassPosition));
        }
    }

    @Override
    public Optional<WorldElement> objectAt(Vector2d position) {
        WorldElement animal = animals.get(position);
        if (animal != null) {
            return Optional.of(animal);
        }

        WorldElement grass = grasses.get(position);
        return Optional.ofNullable(grass);
    }

    @Override
    public List<WorldElement> getElements() {
        return Stream.concat(animals.values().stream(), grasses.values().stream())
                .collect(Collectors.toList());
    }

    @Override
    public Boundary getCurrentBounds() {
        Vector2d ll = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Vector2d ur = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        for (Vector2d position : animals.keySet()) {
            ll = ll.lowerLeft(position);
            ur = ur.upperRight(position);
        }
        return new Boundary(ll, ur);
    }
}
