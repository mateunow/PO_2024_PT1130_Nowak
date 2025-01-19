package darwinProject.model;

import darwinProject.enums.MapDirection;
import darwinProject.enums.MoveDirection;

import java.util.ArrayList;

public class Animal implements WorldElement {
    private MapDirection direction;
    private Vector2d position;
    private int energy;
    private final Vector2d maxPosition = new Vector2d(4,4);
    private final int minGene = 0;
    private final int maxGene = 7;
    private final Vector2d minPosition = new Vector2d(0,0);
    private final Vector2d startPosition = new Vector2d(2, 2);
    private final ArrayList<Integer> genome = new ArrayList<>();
    private int currentGene = 0;

    public Animal() {
        this.direction = MapDirection.NORTH;
        this.position = startPosition;
    }
    public Animal(MapDirection direction, Vector2d position){
        this.direction = direction;
        this.position = position;
    }
    public void setPosition(Vector2d newPosition) {
        if (newPosition.precedes(maxPosition) && newPosition.follows(minPosition)) {
            this.position = newPosition;
            this.direction = MapDirection.NORTH;
        }
        else {
        throw new IllegalArgumentException("Invalid starting position for an animal");
    }
    }

    public String toString() {
        return switch (this.direction) {
            //TODO change those directions strings to emojis/something other than NE
            case NORTH -> "^";
            case NORTHEAST -> "NE";
            case EAST -> ">";
            case SOUTHEAST -> "SE";
            case SOUTH -> "v";
            case SOUTHWEST -> "SW";
            case WEST -> "<";
            case NORTHWEST -> "NW";
        };
    }


    public void move(MoveValidator validator) {
        Vector2d potentialNewPosition;

        potentialNewPosition = this.position.add(this.getDirection().toUnitVector());
        if(validator.canMoveTo(potentialNewPosition)) {
            this.position = potentialNewPosition;
            this.direction.turn(genome.get(currentGene));
            currentGene++;
        }
        //TODO zmień ilość żeby genom nie powiększał się do nieskończoności tylko do tego N - liczby genomów
    }

    public Vector2d getPosition(){
        return this.position;
    }
    public MapDirection getDirection() {
        return this.direction;
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

}