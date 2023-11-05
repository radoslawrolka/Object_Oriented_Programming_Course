package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap {
    private final int grassNumber;
    private final Map<Vector2d, Grass> grasses = new HashMap<>();

    public GrassField(int grassNumber) {
        super();
        this.grassNumber = grassNumber;

        randomPositionGenerator generator = new randomPositionGenerator(grassNumber, new Vector2d(0, 0), new Vector2d((int)sqrt(grassNumber*10), (int)sqrt(grassNumber*10)));
        for(Vector2d grassPosition : generator) {
            System.out.println(grassPosition);
            grasses.put(grassPosition, new Grass(grassPosition));
        }
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if(animals.get(position) != null) {
            return animals.get(position);
        }
        return grasses.get(position);
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
        if (!(other instanceof GrassField that))
            return false;
        return this.grassNumber == that.grassNumber && this.grasses.equals(that.grasses);
    }

    @Override
    public List<WorldElement> getElements() {
        List<WorldElement> elements = new ArrayList<>(animals.values());
        elements.addAll(grasses.values());
        return elements;
    }
}
