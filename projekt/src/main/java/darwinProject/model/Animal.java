package darwinProject.model;

import darwinProject.enums.MapDirection;
import darwinProject.model.maps.WorldMap;
import darwinProject.model.util.Boundary;

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
    private int daysLived = 1;
    private int plantsEaten;
    private int dayOfDeath;
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


    public void move(WorldMap map) {
        Vector2d potentialNewPosition = this.position.add(this.getDirection().toUnitVector());

        Boundary boundary = map.getCurrentBounds();
        int yPosition = potentialNewPosition.getY();
        int mapWidth = boundary.upperRight().getX();
        int potentialX = potentialNewPosition.getX();
        if ( (yPosition == -1) || (yPosition > boundary.upperRight().getY())) {
            this.turn(4);
        }
        else {
            if (potentialX > mapWidth) {
                this.position = new Vector2d(0, yPosition);
                this.turn(genome.get(currentGene));
            }
            else if(potentialX < 0) {
                this.position = new Vector2d(mapWidth, yPosition);
                this.turn(genome.get(currentGene));
            }
            else {
                this.position = potentialNewPosition;
                this.turn(genome.get(currentGene));
            }
        }
        daysLived +=1;
    }

    public void turn(Integer turnCount){
        this.direction = this.direction.turn(turnCount);
        currentGene++;
        currentGene%=maxGene;
    }

    public void addEnergy(int energy){
        this.energy += energy;
        plantsEaten += 1;
    }
    public void reduceEnergy(int energy){
        this.energy -= energy;
    }
    public Vector2d getPosition(){
        return this.position;
    }
    public MapDirection getDirection() {
        return this.direction;
    }

    public void die(int dayOfDeath) {
        this.dayOfDeath = dayOfDeath;
    }

    public final List<Integer> getGenome() {
        return new ArrayList<>(this.genome);
    }
    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

}