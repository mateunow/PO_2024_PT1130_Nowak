package darwinProject;

import darwinProject.model.maps.AbstractWorldMap;
import darwinProject.model.Animal;
import darwinProject.model.Vector2d;
import darwinProject.exceptions.IncorrectPositionException;
import darwinProject.model.maps.EarthMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation implements Runnable
{
    private final List<Animal> animals;
    private final List<Vector2d> animalsPositions;
    private final AbstractWorldMap world;
    private final Random random = new Random();
    private final Integer initialNumberOfAnimals;


    public Simulation(Integer mapHeight, Integer mapWidth, Integer startingGrassCount,
                      Integer energyFromEatingPlants, Integer numberOfPlantsGrownDaily, Integer initialNumberOfAnimals,
            Integer energyReadyToReproduce, Integer energyToReproduce, Integer minNumberOfMutations, Integer maxNumberOfMutations, Integer numberOfGenes, Integer startingEnergy) {
        //TODO add number of grasses to add each day
        //TODO add animal variant (crazy/normal animal)
        this.world = new EarthMap(mapHeight, mapWidth, startingGrassCount, numberOfPlantsGrownDaily, energyFromEatingPlants);
        this.animals = new ArrayList<>();
        this.animalsPositions = new ArrayList<>();
        this.initialNumberOfAnimals = initialNumberOfAnimals;

        for (int i=0; i<initialNumberOfAnimals; i++) {
            try {
                Vector2d position = new Vector2d(random.nextInt(mapWidth), random.nextInt(mapHeight));
                Animal animal = new Animal(position, numberOfGenes, startingEnergy, energyReadyToReproduce, energyToReproduce, minNumberOfMutations, maxNumberOfMutations);
                world.place(animal);
                this.animals.add(animal);
                this.animalsPositions.add(position);
            }
            catch (IncorrectPositionException e) {
                System.out.println("Incorrect position " + e.getMessage());
            }
        }

}

    public void run(){

        int animalsCount = initialNumberOfAnimals;
        boolean running = true;
        int i = 0;
        while (running) {
            System.out.println("day " + i);
            i++;
//TODO dodaj warunek zakończenia pętli
//            for (int i = 0; i < animalsCount; i++) {
//                world.move(animals.get(i));
//                System.out.println(animals.get(i).getEnergy());
//            world.moveAllAnimals();
            world.eatPlants();

            world.generateNewGrassPositions();
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }


            }
        }
    }
