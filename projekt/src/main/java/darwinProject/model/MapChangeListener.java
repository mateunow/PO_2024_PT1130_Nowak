package darwinProject.model;

import darwinProject.model.maps.WorldMap;

public interface MapChangeListener {
    void mapChanged(WorldMap worldMap, String message);
}
