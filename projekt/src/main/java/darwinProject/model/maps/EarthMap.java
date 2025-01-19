package darwinProject.model.maps;

import darwinProject.model.Vector2d;
import darwinProject.model.util.Boundary;

import java.util.AbstractMap;

public class EarthMap extends AbstractWorldMap {
    //TODO zoptymalizuj tą klasę bo pewnie da się lepiej
    private Vector2d upperRight;
    private final Vector2d lowerLeft = new Vector2d(0,0);

    public EarthMap(Vector2d upperRight) {
        this.upperRight = upperRight;
    }

    @Override
    public Boundary getCurrentBounds() {
        return new Boundary(lowerLeft, upperRight);
    }
}
