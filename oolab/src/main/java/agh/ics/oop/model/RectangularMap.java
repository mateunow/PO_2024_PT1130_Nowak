package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap {
    private final Vector2d upperRight;
    private final Vector2d lowerLeft = new Vector2d(0, 0);


    public RectangularMap(int width, int height) {
        this.upperRight = new Vector2d(width - 1, height - 1);
    }
    public String toString() {
        return mapVisualizer.draw(lowerLeft, upperRight);
    }



    protected Vector2d getUpperRight() {
        return upperRight;
    }


    protected Vector2d getLowerLeft() {
        return lowerLeft;
    }
}




