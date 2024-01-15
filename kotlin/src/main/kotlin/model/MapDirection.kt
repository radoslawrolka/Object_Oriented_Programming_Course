package model;

enum class MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST;
    override fun toString() = when(this) {
        NORTH -> ("N")
        SOUTH -> ("S")
        WEST -> ("W")
        EAST -> ("E")
    }
    fun next() = when(this) {
        NORTH -> EAST
        SOUTH -> WEST
        WEST -> NORTH
        EAST -> SOUTH
    }
    fun previous() = when(this) {
        NORTH -> WEST
        WEST -> SOUTH
        SOUTH -> EAST
        EAST -> NORTH
    }
}