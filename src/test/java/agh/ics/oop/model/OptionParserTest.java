package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class OptionParserTest {
    @Test
    public void testParse() {
        String[] args = {"f", "b", "r", "l", "x", "x", "r"};
        String[] args2 = {"f", "b", "r", "l", "x", "x", "r", "f", "b", "r", "l", "x", "x", "r"};

        List<MoveDirection> directions = List.of(MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.RIGHT);
        List<MoveDirection> directions2 = List.of(MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.RIGHT);

        assertEquals(directions, OptionParser.parse(args));
        assertEquals(directions2, OptionParser.parse(args2));
    }


}
