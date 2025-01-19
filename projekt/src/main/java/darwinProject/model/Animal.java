package darwinProject.model;

import darwinProject.enums.MapDirection;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Animal implements WorldElement {
    private MapDirection direction;
    private Vector2d position;
    private int energy;
    private final int maxGene = 7;
    private final ArrayList<Integer> genome = new ArrayList<>();
    private int currentGene;
    Random rand = new Random();


    public Animal(Vector2d position, Integer numberOfGenes, Integer startingEnergy){
        this.position = position;
        this.energy = startingEnergy;
        for (int i = 0; i < numberOfGenes; i++) {
            genome.add(rand.nextInt(maxGene + 1)); //może po prostu zwiększyć wyżej o 1 w atrybutach
        }
        this.currentGene = rand.nextInt(numberOfGenes);
        this.direction = MapDirection.values()[rand.nextInt(MapDirection.values().length)];
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
            this.turn(genome.get(currentGene));
            currentGene++;
        }
        //TODO zmień ilość żeby genom nie powiększał się do nieskończoności tylko do tego N - liczby genomów
    }
    public void turn(Integer turnCount){
        this.direction = this.direction.turn(turnCount);
    }

    public void addEnergy(Integer energy){
        this.energy += energy;
    }
    public void reduceEnergy(Integer energy){
        this.energy -= energy;
    }
    public Vector2d getPosition(){
        return this.position;
    }
    public MapDirection getDirection() {
        return this.direction;
    }

    public final List<Integer> getGenome() {
        return new ArrayList<>(this.genome);
    }
    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

}