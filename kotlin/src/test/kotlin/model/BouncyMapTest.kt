package model

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class BouncyMapTest {
    @Test
    fun `canMoveTo should return true when position is within map boundaries`() {
        val map = BouncyMap(5, 5)
        val position1 = Vector2d(3, 3)
        val position2 = Vector2d(0, 0)
        val position3 = Vector2d(5, 5)

        assertTrue(map.canMoveTo(position1))
        assertTrue(map.canMoveTo(position2))
        assertTrue(map.canMoveTo(position3))
    }

    @Test
    fun `canMoveTo should return false when position is outside map boundaries`() {
        val map = BouncyMap(5, 5)
        val position1 = Vector2d(-1, 3)
        val position2 = Vector2d(3, -1)
        val position3 = Vector2d(6, 3)
        val position4 = Vector2d(3, 6)

        assertFalse(map.canMoveTo(position1))
        assertFalse(map.canMoveTo(position2))
        assertFalse(map.canMoveTo(position3))
        assertFalse(map.canMoveTo(position4))
    }

    @Test
    fun `isOccupied should return true when position is occupied by an animal`() {
        val map = BouncyMap(5, 5)
        val animal = Animal(position = Vector2d(3, 3))
        map.place(animal)
        val position = Vector2d(3, 3)

        assertTrue(map.isOccupied(position))
    }

    @Test
    fun `isOccupied should return false when position is not occupied by any animal`() {
        val map = BouncyMap(5, 5)
        val position = Vector2d(3, 3)

        assertFalse(map.isOccupied(position))
    }

    @Test
    fun `objectAt should return the animal at the given position`() {
        val map = BouncyMap(5, 5)
        val animal = Animal(position = Vector2d(3, 3))
        map.place(animal)
        val position = Vector2d(3, 3)

        assertEquals(animal, map.objectAt(position))
    }

    @Test
    fun `objectAt should return null when there is no animal at the given position`() {
        val map = BouncyMap(5, 5)
        val position = Vector2d(3, 3)

        assertNull(map.objectAt(position))
    }

    @Test
    fun `move should update the animal's position and map correctly`() {
        val map = BouncyMap(5, 5)
        val animal = Animal(position = Vector2d(3, 3))
        map.place(animal)

        map.move(animal, MoveDirection.RIGHT)

        assertEquals(Vector2d(3, 3), animal.getPosition())
        assertNull(map.objectAt(Vector2d(4, 3)))
        assertEquals(animal, map.objectAt(Vector2d(3, 3)))

        map.move(animal, MoveDirection.FORWARD)
        assertEquals(Vector2d(4, 3), animal.getPosition())
        assertNull(map.objectAt(Vector2d(3, 3)))
        assertEquals(animal, map.objectAt(Vector2d(4, 3)))
    }

    @Test
    fun `place should add the animal to the map at the given position`() {
        val map = BouncyMap(5, 5)
        val animal = Animal(position = Vector2d(3, 3))

        map.place(animal)

        assertEquals(animal, map.objectAt(Vector2d(3, 3)))

        animal.setPosition(Vector2d(4, 3))
        map.place(animal)

        assertEquals(animal, map.objectAt(Vector2d(4, 3)))
        assertNotEquals(animal, map.objectAt(Vector2d(3, 3)))
    }
}