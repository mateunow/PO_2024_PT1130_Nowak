package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap{

    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final int height;
    private final int width;
    private final Vector2d upperRight;
    private final Vector2d lowerLeft = new Vector2d(0,0);
    private final MapVisualizer mapVisualizer  = new MapVisualizer(this);



    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        this.upperRight = new Vector2d(width-1,height-1);
    }


    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d currentPosition = animal.getPosition();
        animal.move(direction, this::canMoveTo);
        animals.remove(currentPosition);
        animals.put(animal.getPosition(), animal);
    }

    @Override
    public boolean canMoveTo (Vector2d position) {
        return position.precedes(upperRight) && position.follows(lowerLeft) && !isOccupied(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();
        if (canMoveTo(position)) {
            animals.put(position, animal);
            return true;
        }
        return false;
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }


    public String toString() {
        return mapVisualizer.draw(lowerLeft, upperRight);
    }
}
