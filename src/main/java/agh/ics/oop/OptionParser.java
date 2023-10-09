package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class OptionParser {
    public static MoveDirection[] parse(String[] args) {
        int size = 0;
        for (String s : args) {
            if (s.equals("f") || s.equals("b") || s.equals("r") || s.equals("l")) {
                size++;
            }
        }
        MoveDirection[] directions = new MoveDirection[size];
        int i = 0;
        for (String s : args) {
            switch (s) {
                case "f" -> directions[i++] = MoveDirection.FORWARD;
                case "b" -> directions[i++] = MoveDirection.BACKWARD;
                case "r" -> directions[i++] = MoveDirection.RIGHT;
                case "l" -> directions[i++] = MoveDirection.LEFT;
            }
        }
        return directions;
    }
}
