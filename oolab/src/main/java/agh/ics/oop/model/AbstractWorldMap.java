package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap {
    protected Vector2d upperRight = new Vector2d(0,0);
    protected Vector2d lowerLeft = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
    public final MapVisualizer mapVisualizer = new MapVisualizer(this);
    private final Map<Vector2d, Animal> animals = new HashMap<>();

    protected abstract Vector2d getUpperRight();
    protected abstract Vector2d getLowerLeft();


    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d currentPosition = animal.getPosition();
        animal.move(direction, this);
        animals.remove(currentPosition);
        animals.put(animal.getPosition(), animal);
    }


    // Z tą metodą miałem problem odnośnie upperRight i lowerLeft. Nie znalazłem innego sposobu niż uzyskiwanie ich getterami
    // z RectangularMap i GrassField. Jeśli istaniało lepsze rozwiązanie proszę o komentarz.
    @Override
    public boolean canMoveTo(Vector2d position) {
        Vector2d upperRight = getUpperRight();
        Vector2d lowerLeft = getLowerLeft();
        return position.precedes(upperRight) && position.follows(lowerLeft) && !(objectAt(position) instanceof Animal);
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
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    public List<WorldElement> getElements(){
        List<WorldElement> worldElements = new ArrayList<>();
        worldElements.addAll(animals.values());
        return worldElements;
    }



}
