package agh.ics.oop.model;

@FunctionalInterface
public interface MapChangeListener {
    void mapChanged(WorldMap worldmap, String message);
}
