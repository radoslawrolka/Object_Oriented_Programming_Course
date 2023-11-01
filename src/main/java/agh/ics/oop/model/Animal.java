package agh.ics.oop.model;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;

    public Animal(Vector2d initialPosition) {
        this.orientation = MapDirection.NORTH;
        this.position = initialPosition;
    }

    public Animal() {
        this (new Vector2d(2, 2));
    }

    public MapDirection getOrientation() {
        return this.orientation;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public String toString() {
        return this.position.toString() + " - " + this.orientation.toString();
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveDirection direction) {
        Vector2d toprightBound = new Vector2d(4, 4);
        Vector2d bottomleftBound = new Vector2d(0, 0);
        switch (direction) {
            case FORWARD, BACKWARD -> {
                Vector2d unitVector = this.orientation.toUnitVector();
                if (direction == MoveDirection.BACKWARD) {
                    unitVector = unitVector.opposite();
                }
                Vector2d newPosition = this.position.add(unitVector);
                if (newPosition.precedes(toprightBound) && newPosition.follows(bottomleftBound)) {
                    this.position = newPosition;
                }
            }
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
        }
    }
}
