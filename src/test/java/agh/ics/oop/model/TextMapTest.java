package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TextMapTest {
    String[] strings = {"Ala", "ma", "sowoniedźwiedzia"};
    String[] strings2 = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

    @Test
    public void placeTest() {
        TextMap map = new TextMap();
        for (String s : strings2) {
            map.place(s);
        }
        assertEquals(map.objectAt(0), strings2[0]);
        assertEquals(map.objectAt(1), strings2[1]);
        assertEquals(map.objectAt(2), strings2[2]);
        assertEquals(map.objectAt(3), strings2[3]);
        assertEquals(map.objectAt(4), strings2[4]);
        assertEquals(map.objectAt(5), strings2[5]);
        assertEquals(map.objectAt(6), strings2[6]);
        assertEquals(map.objectAt(7), strings2[7]);
        assertEquals(map.objectAt(8), strings2[8]);
        assertEquals(map.objectAt(9), strings2[9]);
        assertNull(map.objectAt(10));
    }

    @Test
    public void canMoveTo() {
        TextMap map = new TextMap();
        for (String s : strings2) {
            map.place(s);
        }
        assertTrue(map.canMoveTo(0));
        assertTrue(map.canMoveTo(1));
        assertTrue(map.canMoveTo(2));
        assertTrue(map.canMoveTo(9));
        assertFalse(map.canMoveTo(10));
        assertFalse(map.canMoveTo(-1));
    }

    @Test
    public void isOccupied() {
        TextMap map = new TextMap();
        for (String s : strings2) {
            map.place(s);
        }
        assertTrue(map.isOccupied(0));
        assertTrue(map.isOccupied(1));
        assertTrue(map.isOccupied(2));
        assertTrue(map.isOccupied(9));
        assertFalse(map.isOccupied(10));
        assertFalse(map.isOccupied(-1));
    }

    @Test
    public void objectAtTest() {
        TextMap map = new TextMap();
        for (String s : strings) {
            map.place(s);
        }
        assertEquals(map.objectAt(0), strings[0]);
        assertEquals(map.objectAt(1), strings[1]);
        assertEquals(map.objectAt(2), strings[2]);
        assertNull(map.objectAt(3));
    }

    @Test
    public void moveTest() {
        TextMap map = new TextMap();
        for (String s : strings) {
            map.place(s);
        }
        map.move("ma", MoveDirection.FORWARD);
        assertEquals(map.objectAt(0), strings[0]);
        assertEquals(map.objectAt(1), strings[2]);
        assertEquals(map.objectAt(2), strings[1]);
        map.move("ma", MoveDirection.FORWARD);
        assertEquals(map.objectAt(0), strings[0]);
        assertEquals(map.objectAt(1), strings[2]);
        assertEquals(map.objectAt(2), strings[1]);
    }
}
