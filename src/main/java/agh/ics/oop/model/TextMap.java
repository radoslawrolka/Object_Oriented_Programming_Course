package agh.ics.oop.model;

import java.util.Collections;
import java.util.List;

/*
Mapa nie ma górnej granicy - dokładanie nowego napisu zawsze wstawia go na koniec mapy.
Przemieszczanie napisu jest możliwe jedynie w obecnych granicach <0, N> (gdzie N - liczba elementów w mapie). Przesuwany napis zamienia się miejscami z sąsiadem - w przypadku ruchu "na wschód" z sąsiadem z prawej (o indeksie o 1 wyższym), a "na zachód" z lewej. Np. dla mapy ["Ala", "ma", "sowoniedźwiedzia"] przesunięcie napisu "ma" na wschód powinno dać efekt: ["Ala", "sowoniedźwiedzia", "ma"]. Dalsze przemieszczanie wyrazu "ma" w prawo nie jest już możliwe.
Napis może się przemieszczać do przodu i tyłu FORWARD/BACKWARD tylko w orientacjach EAST/WEST, w pozostałych przypadkach ruch jest ignorowany.
 */
public class TextMap implements WorldMap<String, Integer> {
    private List<String> map = new java.util.LinkedList<>();
    private int length = 0;

    @Override
    public boolean place(String object) {
        if (object == null) return false;
        map.add(object);
        length++;
        return true;
    }

    @Override
    public boolean isOccupied(Integer position) {
        return position >= 0 && position < length;
    }

    @Override
    public String objectAt(Integer position) {
        if (isOccupied(position)) return map.get(position);
        return null;
    }

    @Override
    public boolean canMoveTo(Integer position) {
        return position >= 0 && position < length;
    }

    @Override
    public void move(String object, MoveDirection direction) {
        if (object == null) return;
        int index = map.indexOf(object);
        if (index == -1) return;
        if (direction == MoveDirection.FORWARD) {
            if (index == length - 1) return;
            Collections.swap(map, index, index + 1);
        } else if (direction == MoveDirection.BACKWARD) {
            if (index == 0) return;
            Collections.swap(map, index, index - 1);
        }
    }
}
