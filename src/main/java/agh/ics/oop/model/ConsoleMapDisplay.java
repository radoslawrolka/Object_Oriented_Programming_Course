package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

public class ConsoleMapDisplay implements MapChangeListener {
    private int update = 0;

    @Override
    public void mapChanged(WorldMap worldmap, String message) {
        System.out.println(message);
        Boundary bounds = worldmap.getCurrentBounds();
        System.out.println(new MapVisualizer(worldmap).draw(bounds.lowerLeft(), bounds.upperRight()));
        update++;
        System.out.println("To był: "+update+" update");
    }
}
