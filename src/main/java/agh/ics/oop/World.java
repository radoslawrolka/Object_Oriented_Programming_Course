package agh.ics.oop;

import agh.ics.oop.model.*;

import java.sql.SQLOutput;

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
        MoveDirection[] moves = OptionParser.parse(arg);
        run(moves);
        System.out.println("system zakonczyl dzialanie");
    }

    public static void run(MoveDirection[] moves) {

        for (MoveDirection m : moves) {
            switch (m) {
                case FORWARD-> System.out.println("do przodu");
                case BACKWARD-> System.out.println("do tylu");
                case RIGHT-> System.out.println("w prawo");
                case LEFT-> System.out.println("w lewo");
            }
        }
    }
}
