package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;

public class World {
    public static void main(String[] arg) {

        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        MapDirection kierunek = MapDirection.NORTH;
        System.out.println(kierunek);
        System.out.println(kierunek.next());
        System.out.println(kierunek.previous());
        System.out.println(kierunek.toUnitVector());

        System.out.println(new Animal());

        System.out.println("system wystartowal");
        for (String s : arg) {
            System.out.print(s+" ");
        }
        System.out.println();
        List<MoveDirection> moves = OptionParser.parse(arg);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        int w = 5;
        int h = 4;
        WorldMap map = new RectangularMap(w, h);
        Simulation simulation = new Simulation(moves, positions, map);
        simulation.run();
        System.out.println("system zakonczyl dzialanie");
    }
}
