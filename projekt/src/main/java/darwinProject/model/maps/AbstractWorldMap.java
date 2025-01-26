package darwinProject.model.maps;

import darwinProject.model.*;
import darwinProject.model.util.Boundary;
import darwinProject.exceptions.IncorrectPositionException;
import darwinProject.model.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {
    protected final MapVisualizer mapVisualizer = new MapVisualizer(this);
    protected final Map<Vector2d, Animal> animals = new HashMap<>();


    private final HashSet<MapChangeListener> observers = new HashSet<>();
    protected final int id = this.hashCode();
    protected final Set<Animal> deadAnimals = new HashSet<>();



    public final void registerObservers(MapChangeListener observer) {
        observers.add(observer);
    }
    public final void unregisterObservers(MapChangeListener observer) {
        observers.remove(observer);
    }

    protected void notifyObservers(String message) {
        for (MapChangeListener observer : observers) {
            observer.mapChanged(this, message);
        }
    }

    protected Map<Vector2d, List<Animal>> groupAnimalsByPosition() {
        Map<Vector2d, List<Animal>> grouped = new HashMap<>();

        for (Map.Entry<Vector2d, Animal> entry : animals.entrySet()) {
            Vector2d position = entry.getKey(); // Position is the key in the map
            Animal animal = entry.getValue();  // Animal is the value

            // Add the animal to the list for this position
            grouped.computeIfAbsent(position, k -> new ArrayList<>()).add(animal);
        }

        return grouped;
    }

    Comparator<Animal> animalPriorityComparator = (a1, a2) -> {
        if (a1.getEnergy() != a2.getEnergy()) {
            return Integer.compare(a2.getEnergy(), a1.getEnergy());
        } else if (a1.getAge() != a2.getAge()) {
            return Integer.compare(a2.getAge(), a1.getAge());
        } else if (a1.getChildrenCount() != a2.getChildrenCount()) {
            return Integer.compare(a2.getChildrenCount(), a1.getChildrenCount());
        } else {
            return new Random().nextInt(2) * 2 - 1; // Randomly return -1 or 1
        }
    };


    public abstract void move(Animal animal) ;



    @Override
    public abstract boolean canMoveTo(Vector2d position);

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public void place(Animal animal) throws IncorrectPositionException {
        Vector2d position = animal.getPosition();
        if (canMoveTo(position)) {
            animals.put(position, animal);
            notifyObservers("Animal placed at " + animal.getPosition() );
        }
        else {
        throw new IncorrectPositionException(position);
    }}
    public abstract void eatPlants();
    public Set<Vector2d> findFieldsWithoutGrass() {
        Set<Vector2d> positions = new HashSet<>();
        Boundary boundary = getCurrentBounds();
        int width = boundary.upperRight().getY();
        int height = boundary.upperRight().getX();

        for (int i = 0; i < width; i++ ) {
            for (int j = 0; j < height; j++) {
                positions.add(new Vector2d(i, j));
            }
        }
        return positions;
    }

    public abstract void generateNewGrassPositions();

    @Override
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    public List<WorldElement> getElements() {
        List<WorldElement> elements = new ArrayList<>();
        // Iterate over each entry in the animals map
        for (Animal animal : animals.values()) {
            // Add all animals from the SortedSet to the list
            elements.add(animal);
        }
        return elements;
    }


    public abstract Boundary getCurrentBounds();

    public String toString() {
        Boundary currentBounds = getCurrentBounds();
        return mapVisualizer.draw(currentBounds.lowerLeft(), currentBounds.upperRight());
    }

    @Override
    public int getId() {
        return id;
    }
}
