package agh.ics.oop.model;

import agh.ics.oop.model.util.Boundary;
import agh.ics.oop.exeptions.IncorrectPositionException;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {
    protected final MapVisualizer mapVisualizer = new MapVisualizer(this);
    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final HashSet<MapChangeListener> observers = new HashSet<>();

    public void register(MapChangeListener observer) {
        observers.add(observer);
    }
    public void unregister(MapChangeListener observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (MapChangeListener observer : observers) {
            observer.mapChanged(this, message);
        }
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d currentPosition = animal.getPosition();
        MapDirection currentDirection = animal.getDirection();
        animal.move(direction, this);
        animals.remove(currentPosition);
        animals.put(animal.getPosition(), animal);

        if (!animal.getPosition().equals(currentPosition)) {
            notifyObservers("Animal moved from " + currentPosition + " to " + animal.getPosition());
        }
        if (!animal.getDirection().equals(currentDirection)) {
            notifyObservers("Animal turned from " + currentDirection + " to " + animal.getDirection());
        }
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public boolean place(Animal animal) throws IncorrectPositionException{
        Vector2d position = animal.getPosition();
        if (canMoveTo(position)) {
            animals.put(position, animal);
            notifyObservers("Animal placed at " + animal.getPosition() );
            return true;
        }
        throw new IncorrectPositionException(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    public List<WorldElement> getElements(){
        List<WorldElement> worldElements = new ArrayList<>(animals.values());
        return worldElements;
    }


    public abstract Boundary getCurrentBounds();

    public String toString() {
        return mapVisualizer.draw(getCurrentBounds().lowerLeft(), getCurrentBounds().upperRight());
    }

}
