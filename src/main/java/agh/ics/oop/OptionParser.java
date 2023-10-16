package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.Arrays;

public class OptionParser {
    public static MoveDirection[] parse(String[] args) {
        MoveDirection[] directions = new MoveDirection[args.length];
        int i = 0;
        for (String s : args) {
            switch (s) {
                case "f" -> directions[i++] = MoveDirection.FORWARD;
                case "b" -> directions[i++] = MoveDirection.BACKWARD;
                case "r" -> directions[i++] = MoveDirection.RIGHT;
                case "l" -> directions[i++] = MoveDirection.LEFT;
            }
        }
        return Arrays.copyOfRange(directions, 0, i);
    }
}
