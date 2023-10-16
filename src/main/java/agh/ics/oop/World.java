package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class World {
    public static void main(String[] arg) {
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
