package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

public class MapDirectionTest {
    @Test
    public void testNext() {
        assert(MapDirection.NORTH.next() == MapDirection.EAST);
        assert(MapDirection.EAST.next() == MapDirection.SOUTH);
        assert(MapDirection.SOUTH.next() == MapDirection.WEST);
        assert(MapDirection.WEST.next() == MapDirection.NORTH);
    }

    @Test
    public void testPrevious() {
        assert(MapDirection.NORTH.previous() == MapDirection.WEST);
        assert(MapDirection.WEST.previous() == MapDirection.SOUTH);
        assert(MapDirection.SOUTH.previous() == MapDirection.EAST);
        assert(MapDirection.EAST.previous() == MapDirection.NORTH);
    }
}
