package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;

public class World {
    public static void main(String[] arg) {
        System.out.println("system wystartowal");
        for (String s : arg) {
            System.out.print(s+" ");
        }
        System.out.println();
        //List<MoveDirection> moves = OptionParser.parse(arg);
        String[] arg1 = {"f", "f", "r", "f", "f", "f", "f", "f", "f", "f", "f", "f"};
        String[] arg2 = {"f", "b", "r", "f", "f", "l", "f", "r", "l", "f", "f", "f"};
        List<MoveDirection> moves1 = OptionParser.parse(arg1);
        List<MoveDirection> moves2 = OptionParser.parse(arg2);
        List<Vector2d> positions1 = List.of(new Vector2d(2,2), new Vector2d(8,8), new Vector2d(2,2));
        List<Vector2d> positions2 = List.of(new Vector2d(3,3), new Vector2d(1,4), new Vector2d(2,2));
        WorldMap<WorldElement, Vector2d> map1 = new GrassField(10);
        WorldMap<WorldElement, Vector2d> map2 = new RectangularMap(10, 5);
        ConsoleMapDisplay display1 = new ConsoleMapDisplay();
        ConsoleMapDisplay display2 = new ConsoleMapDisplay();
        map1.addObserver(display1);
        map2.addObserver(display2);
        Simulation simulation1 = new Simulation(moves1, positions1, map1);
        Simulation simulation2 = new Simulation(moves2, positions2, map2);
        SimulationEngine engine = new SimulationEngine(List.of(simulation1, simulation2));
        engine.runAsync();
        System.out.println("system zakonczyl dzialanie");
    }
}
