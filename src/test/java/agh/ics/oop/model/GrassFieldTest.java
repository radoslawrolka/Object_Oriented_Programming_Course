package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GrassFieldTest {
    Animal[] animals = {new Animal(new Vector2d(2, 2)),
            new Animal(new Vector2d(3, 4)),
            new Animal(new Vector2d(4, 4)),
            new Animal(new Vector2d(5, 5))};
    Animal[] animals2 = {new Animal(new Vector2d(8, 4)),
            new Animal(new Vector2d(9, 4)),
            new Animal(new Vector2d(4, 4))};
    Animal[] animals3 = {new Animal(new Vector2d(2, 2)),
                         new Animal(new Vector2d(2, 2))};

    @Test
    public void placeTest() {
        AbstractWorldMap map = new GrassField(10);
        for (Animal animal : animals) {
            map.place(animal);
        }
        assertEquals(map.objectAt(new Vector2d(2, 2)), animals[0]);
        assertEquals(map.objectAt(new Vector2d(3, 4)), animals[1]);
        assertEquals(map.objectAt(new Vector2d(4, 4)), animals[2]);
        assertEquals(map.objectAt(new Vector2d(5, 5)), animals[3]);
        assertFalse(map.place(animals3[0]));
    }

    @Test
    public void canMoveTo() {
        AbstractWorldMap map = new GrassField(15);
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
        AbstractWorldMap map = new GrassField(11);
        for (Animal animal : animals2) {
            map.place(animal);
        }
        assertTrue(map.isOccupied(new Vector2d(8, 4)));
        assertTrue(map.isOccupied(new Vector2d(9, 4)));
        assertTrue(map.isOccupied(new Vector2d(4, 4)));
    }

    @Test
    public void objectAtTest() {
        WorldMap<WorldElement, Vector2d> map = new GrassField(10);
        for (Animal animal : animals2) {
            map.place(animal);
        }
        assertEquals(map.objectAt(new Vector2d(8, 4)), animals2[0]);
        assertEquals(map.objectAt(new Vector2d(9, 4)), animals2[1]);
        assertEquals(map.objectAt(new Vector2d(4, 4)), animals2[2]);
    }

    @Test
    public void moveTest() {
        AbstractWorldMap map = new GrassField(0);
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

    @Test
    public void getOrderedAnimalsTest() {
        AbstractWorldMap map = new GrassField(0);
        for (Animal animal : animals) {
            map.place(animal);
        }
        assertEquals(map.getOrderedAnimals().get(0), animals[0]);
        assertEquals(map.getOrderedAnimals().get(1), animals[1]);
        assertEquals(map.getOrderedAnimals().get(2), animals[2]);
        assertEquals(map.getOrderedAnimals().get(3), animals[3]);

        animals[0].move(map, MoveDirection.RIGHT);
        animals[0].move(map, MoveDirection.FORWARD);
        animals[0].move(map, MoveDirection.FORWARD);

        assertEquals(map.getOrderedAnimals().get(0), animals[1]);
        assertEquals(map.getOrderedAnimals().get(1), animals[0]);
        assertEquals(map.getOrderedAnimals().get(2), animals[2]);
        assertEquals(map.getOrderedAnimals().get(3), animals[3]);
    }
}
