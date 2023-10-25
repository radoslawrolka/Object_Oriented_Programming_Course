package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OptionParserTest {
    @Test
    public void testParse() {
        String[] args = {"f", "b", "r", "l", "x", "x", "r"};
        String[] args2 = {"f", "b", "r", "l", "x", "x", "r", "f", "b", "r", "l", "x", "x", "r"};

        MoveDirection[] directions = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.RIGHT};
        MoveDirection[] directions2 = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.RIGHT};

        assertArrayEquals(OptionParser.parse(args), directions);
        assertArrayEquals(OptionParser.parse(args2), directions2);
    }


}
