package model

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class Vector2dTest {
    @Test
    fun `follows should return true when vector follows the other vector and false when not`() {
        val vector1 = Vector2d(3, 5)
        val vector2 = Vector2d(2, 4)
        val vector3 = Vector2d(3, 5)
        val vector4 = Vector2d(2, 6)

        assertEquals(true, vector1 follows vector2)
        assertEquals(true, vector1 follows vector3)
        assertEquals(false, vector2 follows vector1)
        assertEquals(false, vector3 follows vector4)
    }

    @Test
    fun `precedes should return true when vector precedes the other vector and false when not`() {
        val vector1 = Vector2d(3, 5)
        val vector2 = Vector2d(4, 6)
        val vector3 = Vector2d(3, 5)
        val vector4 = Vector2d(2, 6)

        assertEquals(true, vector1 precedes vector2)
        assertEquals(true, vector1 precedes vector3)
        assertEquals(false, vector2 precedes vector1)
        assertEquals(false, vector2 precedes vector4)
    }

    @Test
    fun `upperRight should return the upper right vector`() {
        val vector1 = Vector2d(3, 5)
        val vector2 = Vector2d(2, 8)
        val vector3 = Vector2d(4, 6)

        assertEquals(Vector2d(3, 8), vector1.upperRight(vector2))
        assertEquals(Vector2d(4, 6), vector1.upperRight(vector3))
    }

    @Test
    fun `lowerLeft should return the lower left vector`() {
        val vector1 = Vector2d(3, 5)
        val vector2 = Vector2d(2, 4)
        val vector3 = Vector2d(4, 6)

        assertEquals(Vector2d(2, 4), vector1.lowerLeft(vector2))
        assertEquals(Vector2d(3, 5), vector1.lowerLeft(vector3))
    }

    @Test
    fun `opposite should return the vector with swapped coordinates`() {
        val vector = Vector2d(3, 5)

        assertEquals(Vector2d(5, 3), vector.opposite())
    }

    @Test
    fun `toString should return the string representation of the vector`() {
        val vector = Vector2d(3, 5)

        assertEquals("(3,5)", vector.toString())
    }
    @Test
    fun `add should return the sum of two vectors`() {
        val vector1 = Vector2d(3, 5)
        val vector2 = Vector2d(2, 4)

        assertEquals(Vector2d(5, 9), vector1 + vector2)
    }
    @Test
    fun `subtract should return the difference of two vectors`() {
        val vector1 = Vector2d(3, 5)
        val vector2 = Vector2d(2, 4)

        assertEquals(Vector2d(1, 1), vector1 - vector2)
    }
    @Test
    fun `equaling should return if two vectors are equal`() {
        val vector1 = Vector2d(3, 5)
        val vector2 = Vector2d(2, 4)
        val vector3 = Vector2d(3, 5)

        assertEquals(false, vector1 == vector2)
        assertEquals(true, vector1 == vector3)
    }
    @Test
    fun `toUnitVector should return the correct unit vector`() {
        assertEquals(Vector2d(0, 1), MapDirection.NORTH.toUnitVector())
        assertEquals(Vector2d(-1, 0), MapDirection.WEST.toUnitVector())
        assertEquals(Vector2d(0, -1), MapDirection.SOUTH.toUnitVector())
        assertEquals(Vector2d(1, 0), MapDirection.EAST.toUnitVector())
    }
}