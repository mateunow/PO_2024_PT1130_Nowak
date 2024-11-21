package agh.ics.oop.model;

import agh.ics.oop.model.util.RandomPositionGenerator;

import java.util.*;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap {
    private int grassCount;
    private final Map<Vector2d, Grass> grassMap = new HashMap<>();
    private int maxWidth;
    private int maxHeight;
    public Vector2d lowerLeft = new Vector2d(0,0);
    public Vector2d upperRight = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);

    public GrassField(int grassCount) {
        this.grassCount = grassCount;
        this.maxWidth = (int) sqrt(grassCount * 10);
        this.maxHeight = (int) sqrt(grassCount * 10);


        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(maxWidth, maxHeight, grassCount);
        for(Vector2d grassPosition : randomPositionGenerator) {
            grassMap.put(grassPosition, new Grass(grassPosition));
        }
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        WorldElement object = super.objectAt(position);
        if(object != null) {return object;}
        return grassMap.get(position);

    };



    @Override
    public String toString() {
        Vector2d bottomLeft = upperRight;
        Vector2d topRight = lowerLeft;
        List<WorldElement> elements = getElements();
        for (WorldElement element: elements) {
            Vector2d position = element.getPosition();
            bottomLeft = bottomLeft.lowerLeft(position);
            topRight = topRight.upperRight(position);
        }
        return mapVisualizer.draw(bottomLeft, topRight);
    }

    public List<WorldElement> getElements(){
        List<WorldElement> worldElements = super.getElements();
        worldElements.addAll(grassMap.values());
        return worldElements;
    }


    protected Vector2d getUpperRight() {
        return upperRight;
    }


    protected Vector2d getLowerLeft() {
        return lowerLeft;
    }
}

