package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTest {
    private final int width = 10;
    private final int height = 10;
    Animal[] animals = {new Animal(new Vector2d(2, 2)),
                        new Animal(new Vector2d(3, 4)),
                        new Animal(new Vector2d(4, 4)),
                        new Animal(new Vector2d(5, 5))};
    Animal[] animals2 = {new Animal(new Vector2d(8, 4)),
                        new Animal(new Vector2d(9, 4)),
                        new Animal(new Vector2d(4, 4))};

    @Test
    public void placeTest() {
        RectangularMap map = new RectangularMap(width, height);
        for (Animal animal : animals) {
            map.place(animal);
        }
        assertEquals(map.objectAt(new Vector2d(2, 2)), animals[0]);
        assertEquals(map.objectAt(new Vector2d(3, 4)), animals[1]);
        assertEquals(map.objectAt(new Vector2d(4, 4)), animals[2]);
        assertEquals(map.objectAt(new Vector2d(5, 5)), animals[3]);
    }

    @Test
    public void canMoveTo() {
        RectangularMap map = new RectangularMap(width, height);
        for (Animal animal : animals) {
            map.place(animal);
        }
        assertFalse(map.canMoveTo(new Vector2d(2, 2)));
        assertFalse(map.canMoveTo(new Vector2d(3, 4)));
        assertFalse(map.canMoveTo(new Vector2d(4, 4)));
        assertFalse(map.canMoveTo(new Vector2d(5, 5)));
        assertTrue(map.canMoveTo(new Vector2d(6, 6)));
    }

    @Test
    public void isOccupiedTest() {
        RectangularMap map = new RectangularMap(width, height);
        for (Animal animal : animals2) {
            map.place(animal);
        }
        assertTrue(map.isOccupied(new Vector2d(8, 4)));
        assertTrue(map.isOccupied(new Vector2d(9, 4)));
        assertTrue(map.isOccupied(new Vector2d(4, 4)));
        assertFalse(map.isOccupied(new Vector2d(5, 5)));
    }

    @Test
    public void objectAtTest() {
        RectangularMap map = new RectangularMap(width, height);
        for (Animal animal : animals2) {
            map.place(animal);
        }
        assertEquals(map.objectAt(new Vector2d(8, 4)), animals2[0]);
        assertEquals(map.objectAt(new Vector2d(9, 4)), animals2[1]);
        assertEquals(map.objectAt(new Vector2d(4, 4)), animals2[2]);
        assertNull(map.objectAt(new Vector2d(5, 5)));
    }

    @Test
    public void moveTest() {
        RectangularMap map = new RectangularMap(width, height);
        for (Animal animal : animals) {
            map.place(animal);
        }
        map.move(animals[0], MoveDirection.FORWARD);
        assertEquals(map.objectAt(new Vector2d(2, 3)), animals[0]);
        map.move(animals[1], MoveDirection.FORWARD);
        assertEquals(map.objectAt(new Vector2d(3, 5)), animals[1]);
        map.move(animals[2], MoveDirection.FORWARD);
        assertEquals(map.objectAt(new Vector2d(4, 5)), animals[2]);
        map.move(animals[3], MoveDirection.FORWARD);
        assertEquals(map.objectAt(new Vector2d(5, 6)), animals[3]);
    }
}
