package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.List;

public class TextMap<T, P extends Number> implements WorldNumberPositionMap<T, P> {

    private final List<T> objectList;

    public TextMap() {
        this.objectList = new ArrayList<>();
    }


    @Override
    public boolean canMoveTo(P position) {
        int pos = position.intValue();
        return pos >= 0 && pos < objectList.size();
    }

    @Override
    public void move(T object, MoveDirection direction) {
        int currentPosition = objectList.indexOf(object);

        if (direction == MoveDirection.FORWARD || direction == MoveDirection.RIGHT) {
            if (canMoveTo(convertToP(currentPosition+1))) {
                objectList.remove(object);
                objectList.add(currentPosition + 1, object);
            }

        }
        if (direction == MoveDirection.BACKWARD || direction == MoveDirection.LEFT) {
            if (canMoveTo(convertToP(currentPosition - 1))) {
               objectList.remove(object);
                objectList.add(currentPosition - 1, object);
            }

        }
    }

    //metoda canMoveTo(P position) wymagała wartości P a nie int, jaką otrzymywałem
    //po znalezieniu indeksu, na którym znajduje się obecnie obiekt T. Musiałem więc zamienić inta na P.
    private P convertToP(int value) {
        if (Number.class.isAssignableFrom(Integer.class)) {
            return (P) Integer.valueOf(value);
        } else if (Number.class.isAssignableFrom(Double.class)) {
            return (P) Double.valueOf(value);
        } else if (Number.class.isAssignableFrom(Long.class)) {
            return (P) Long.valueOf(value);
        }
        throw new UnsupportedOperationException("Unsupported number type");
    }





    @Override
    public boolean isOccupied(P position) {
        int pos = position.intValue();
        return pos >= 0 && pos < objectList.size();
    }

    @Override
    public boolean place(T newObject) {
        objectList.add(newObject);
        return true;
    }

    @Override
    public T objectAt(P position) {
        int pos = position.intValue();
        if (pos < 0 || pos >= objectList.size()) {
            return null;
        }
        return objectList.get(pos);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < objectList.size(); i++) {
            stringBuilder.append("\"").append(objectList.get(i)).append("\"");
            if (i < objectList.size() - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
