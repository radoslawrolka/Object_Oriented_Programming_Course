package agh.ics.oop.model;

import java.util.List;

public class OptionParser {
    public static List<MoveDirection> parse(String[] args) {
        List<MoveDirection> directions = new java.util.ArrayList<>();
        for (String arg : args) {
            switch (arg) {
                case "f" -> directions.add(MoveDirection.FORWARD);
                case "b" -> directions.add(MoveDirection.BACKWARD);
                case "r" -> directions.add(MoveDirection.RIGHT);
                case "l" -> directions.add(MoveDirection.LEFT);
            }
        }
        return directions;
    }
}
