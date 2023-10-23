package agh.ics.oop.model;

public enum MapDirection {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public String toString() {
        return switch (this) {
            case NORTH -> "Północ";
            case SOUTH -> "Południe";
            case WEST -> "Zachód";
            case EAST -> "Wschód";
        };
    }

    public MapDirection next() {
        int nextOrdinal = (this.ordinal() + 1) % MapDirection.values().length;
        return MapDirection.values()[nextOrdinal];
    }

    public MapDirection previous() {
        int nextOrdinal = (this.ordinal() + 3) % MapDirection.values().length;
        return MapDirection.values()[nextOrdinal];
    }

    public Vector2d toUnitVector() {
        return switch (this) {
            case NORTH -> new Vector2d(0, 1);
            case SOUTH -> new Vector2d(0, -1);
            case WEST -> new Vector2d(-1, 0);
            case EAST -> new Vector2d(1, 0);
        };
    }
}
