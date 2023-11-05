package agh.ics.oop.model;

import java.util.*;

public class randomPositionGenerator implements Iterable<Vector2d> {
    private final int grassNumber;
    private final Vector2d lowerLeft;
    private final Vector2d upperRight;
    private final Random random = new Random();

    public randomPositionGenerator (int grassNumber, Vector2d lowerLeft, Vector2d upperRight) {
        this.grassNumber = grassNumber;
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
    }

    @Override
    public Iterator<Vector2d> iterator () {
        return new Iterator<Vector2d>() {
            private int counter = 0;
            private List<Vector2d> availablePositions = generateAllPositions();

            @Override
            public boolean hasNext () {
                return counter < grassNumber;
            }

            @Override
            public Vector2d next () {
                if (!hasNext()) {
                    throw new UnsupportedOperationException("No more positions to generate.");
                }

                int index = random.nextInt(availablePositions.size());
                Vector2d position = availablePositions.remove(index);
                counter++;
                return position;
            }

            private List<Vector2d> generateAllPositions () {
                List<Vector2d> positions = new ArrayList<>();
                for (int x = lowerLeft.getX(); x <= upperRight.getX(); x++) {
                    for (int y = lowerLeft.getY(); y <= upperRight.getY(); y++) {
                        positions.add(new Vector2d(x, y));
                    }
                }
                Collections.shuffle(positions, random);
                return positions;
            }
        };
    }
}
