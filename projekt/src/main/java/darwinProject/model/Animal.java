package darwinProject.model;

import darwinProject.enums.MapDirection;
import darwinProject.model.maps.WorldMap;
import darwinProject.model.util.Boundary;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Animal implements WorldElement {
    protected MapDirection direction;
    private Vector2d position;
    private int energy;
    protected final int maxGene = 7;
    private final ArrayList<Integer> genome;
    protected int currentGene;
    private int daysLived = 0;
    private int plantsEaten;
    private int dayOfDeath;
    private final Animal firstParent;
    private final Animal secondParent;
    private int offspringCount = 0;
    private int childrenCount = 0;
    Random rand = new Random();


    public Animal(Vector2d position, Integer numberOfGenes, Integer startingEnergy){
        this.position = position;
        this.energy = startingEnergy;
        this.genome = new ArrayList<>();
        for (int i = 0; i < numberOfGenes; i++) {
            genome.add(rand.nextInt(maxGene + 1));
        }
        this.currentGene = rand.nextInt(numberOfGenes);
        this.direction = MapDirection.values()[rand.nextInt(MapDirection.values().length)];
        this.firstParent = null;
        this.secondParent = null;
    }

    public Animal(Vector2d position, ArrayList<Integer> genome,  Integer energy, Animal firstParent, Animal secondParent){
        this.position = position;
        this.energy = energy;
        this.genome = genome;
        this.currentGene = rand.nextInt(genome.size());
        this.firstParent = firstParent;
        this.secondParent = secondParent;
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

    public Animal reproduceWithOtherAnimal(Animal animal, Integer reproductionEnergy) {
        ArrayList<Integer> childGenome = createChildGenome(this, animal);
        this.childrenCount++;
        this.energy -= reproductionEnergy;
        animal.childrenCount++;
        animal.energy -= reproductionEnergy;
        return new Animal(this.getPosition(), childGenome, 2 * reproductionEnergy, this, animal);
    }

    public ArrayList<Integer> createChildGenome(Animal animal1, Animal animal2) {
        int sumOfEnergy = animal1.energy + animal2.energy;


        Animal strongerParent = animal1.energy > animal2.energy ? animal1 : animal2;
        Animal weakerParent = animal2.energy > animal1.energy ? animal2 : animal1;

        double strongerParentEnergyRatio = (double) strongerParent.energy / sumOfEnergy;

        int genesFromStrongerParent = (int) (strongerParentEnergyRatio * strongerParent.genome.size());
        int genesFromWeakerParent = strongerParent.genome.size() - genesFromStrongerParent;

        Random rand = new Random();
        int randomSide = rand.nextInt(2);

        ArrayList<Integer> newGenome = new ArrayList<>();

        if (randomSide == 0) {
            // Inherit the left side from the stronger parent and the right side from the weaker parent
            newGenome.addAll(strongerParent.genome.subList(0, genesFromStrongerParent));
            newGenome.addAll(weakerParent.genome.subList(genesFromStrongerParent, weakerParent.genome.size()));
        } else {
            // Inherit the right side from the stronger parent and the left side from the weaker parent
            newGenome.addAll(weakerParent.genome.subList(0, genesFromWeakerParent));
            newGenome.addAll(strongerParent.genome.subList(genesFromWeakerParent, strongerParent.genome.size()));
        }

        // Apply mutations to some genes in the new genome
        int genomeSize = newGenome.size();
        int numMutations = rand.nextInt(genomeSize + 1);
        for (int i = 0; i < numMutations; i++) {
            int geneIndex = rand.nextInt(genomeSize);
            int newGeneValue = rand.nextInt(maxGene + 1);
            newGenome.set(geneIndex, newGeneValue);
        }

        return newGenome;
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

    public void die() {
        this.dayOfDeath = this.daysLived;
    }

    public final List<Integer> getGenome() {
        return new ArrayList<>(this.genome);
    }
    public int getEnergy(){
        return this.energy;
    }
    public int getChildrenCount(){
        return this.childrenCount;
    }
    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }
}