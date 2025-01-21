package darwinProject.model.maps;

import darwinProject.model.Vector2d;
import darwinProject.model.util.Boundary;


public class EarthMap extends AbstractWorldMap {
    //TODO zoptymalizuj tą klasę bo pewnie da się lepiej
    private final Vector2d upperRight;
    private final Vector2d lowerLeft = new Vector2d(0,0);
    private final Boundary finalBoundary;

    public EarthMap(int height, int width) {
        this.upperRight = new Vector2d(width - 1,height - 1);
        this.finalBoundary = new Boundary(lowerLeft, upperRight);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.precedes(upperRight) && position.follows(lowerLeft);
        //TODO change this from precedes to other method that does not check both values in Vector2d
    }

    @Override
    public Boundary getCurrentBounds() {
        return finalBoundary;
    }
}