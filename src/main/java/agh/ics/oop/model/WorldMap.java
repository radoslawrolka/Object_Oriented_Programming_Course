package agh.ics.oop.model;

import java.util.List;

/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo, idzik
 */
public interface WorldMap<T, P> extends MoveValidator<P> {

    /**
     * Place a animal on the map.
     *
     * @param T The object to place on the map.
     * @return True if the animal was placed. The animal cannot be placed if the move is not valid.
     */
    boolean place(T object);

    /**
     * Moves an animal (if it is present on the map) according to specified direction.
     * If the move is not possible, this method has no effect.
     */
    void move(T object, MoveDirection direction);

    /**
     * Return true if given position on the map is occupied. Should not be
     * confused with canMove since there might be empty positions where the animal
     * cannot move.
     *
     * @param position Position to check.
     * @return True if the position is occupied.
     */
    boolean isOccupied(P position);

    /**
     * Return an animal at a given position.
     *
     * @param position The position of the animal.
     * @return animal or null if the position is not occupied.
     */
    T objectAt(P position);

    List<T> getElements();

    void addObserver(MapChangeListener observer);

    void removeObserver(MapChangeListener observer);

    void notifyObservers(String message);

    Boundary getCurrentBounds();

    String getId();
}
