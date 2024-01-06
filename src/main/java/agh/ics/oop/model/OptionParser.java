package agh.ics.oop.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionParser {
    public static List<MoveDirection> parse(String[] args) {
        return Stream.of(args)
                .map(arg -> {
                    return switch ( arg ) {
                        case "f" -> MoveDirection.FORWARD;
                        case "b" -> MoveDirection.BACKWARD;
                        case "r" -> MoveDirection.RIGHT;
                        case "l" -> MoveDirection.LEFT;
                        default -> throw new IllegalArgumentException(arg + " is not a legal move specification");
                    };
                })
                .collect(Collectors.toList());
    }
}
