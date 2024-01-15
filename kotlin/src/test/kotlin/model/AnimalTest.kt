package model

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class AnimalTest {
    @Test
    fun `toString should return the string representation of the animal's orientation`() {
        val animal1 = Animal()
        val animal2 = Animal(MapDirection.EAST)
        val animal3 = Animal(position = Vector2d(2, 2))

        assertEquals("N", animal1.toString())
        assertEquals("E", animal2.toString())
        assertEquals("N", animal3.toString())
    }

    @Test
    fun `isAt should return true when the animal is at the specified position and false when not`() {
        val animal1 = Animal(position = Vector2d(4, 4))
        val animal2 = Animal()

        assertEquals(true, animal1.isAt(Vector2d(4, 4)))
        assertEquals(true, animal2.isAt(Vector2d(2, 2)))
        assertEquals(false, animal1.isAt(Vector2d(3, 3)))
        assertEquals(false, animal2.isAt(Vector2d(3, 3)))
    }

    @Test
    fun `getPosition should return the current position of the animal`() {
        val animal1 = Animal(position = Vector2d(7, 7))
        val animal2 = Animal()

        assertEquals(Vector2d(7, 7), animal1.getPosition())
        assertEquals(Vector2d(2, 2), animal2.getPosition())
    }

    @Test
    fun `move should update the animal's orientation and position based on the given direction`() {
        val animal1 = Animal()
        val animal2 = Animal(MapDirection.SOUTH, Vector2d(4, 4))

        animal1.move(MoveDirection.RIGHT)
        assertEquals(MapDirection.EAST.toString(), animal1.toString())

        animal1.move(MoveDirection.LEFT)
        assertEquals(MapDirection.NORTH.toString(), animal1.toString())

        animal1.move(MoveDirection.FORWARD)
        assertEquals(Vector2d(2, 3), animal1.getPosition())

        animal1.move(MoveDirection.BACKWARD)
        assertEquals(Vector2d(2, 2), animal1.getPosition())

        animal1.move(MoveDirection.LEFT)
        assertEquals(MapDirection.WEST.toString(), animal1.toString())

        animal1.move(MoveDirection.FORWARD)
        assertEquals(Vector2d(1, 2), animal1.getPosition())

        animal1.move(MoveDirection.BACKWARD)
        assertEquals(Vector2d(2, 2), animal1.getPosition())



        animal2.move(MoveDirection.RIGHT)
        assertEquals(MapDirection.WEST.toString(), animal2.toString())

        animal2.move(MoveDirection.FORWARD)
        assertEquals(Vector2d(3, 4), animal2.getPosition())

        animal2.move(MoveDirection.BACKWARD)
        assertEquals(Vector2d(4, 4), animal2.getPosition())

        animal2.move(MoveDirection.LEFT)
        assertEquals(MapDirection.SOUTH.toString(), animal2.toString())

        animal2.move(MoveDirection.FORWARD)
        assertEquals(Vector2d(4, 3), animal2.getPosition())

        animal2.move(MoveDirection.BACKWARD)
        assertEquals(Vector2d(4, 4), animal2.getPosition())
    }
}