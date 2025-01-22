package darwinProject;

import darwinProject.model.maps.AbstractWorldMap;
import darwinProject.model.Animal;
import darwinProject.model.Vector2d;
import darwinProject.exceptions.IncorrectPositionException;

import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable
{
    private final List<Animal> animals;
    private final List<Vector2d> animalsPositions;
    private final AbstractWorldMap world;
    private final Integer numberOfGenes;
    private final Integer startingEnergy;


    public Simulation(List<Vector2d> positions, AbstractWorldMap world, Integer numberOfGenes, Integer startingEnergy) {
        //TODO delete positions list, replace with number of initial animals
        this.world = world;
        this.numberOfGenes = numberOfGenes;
        this.startingEnergy = startingEnergy;
        this.animals = new ArrayList<>();
        this.animalsPositions = new ArrayList<>();


        for (Vector2d position : positions) {
            try {
                Animal animal = new Animal(position, numberOfGenes, startingEnergy);
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
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        try i catch w celu poczekania na wyświetlenie początkowej mapy zanim
//        pierwsze zwierze zrobi ruch

        int animalsCount = animals.size();
        boolean running = true;
        while (running) {

            for (int i = 0; i < animalsCount; i++) {
                //TODO zmień warunek zakończenia tej pętli i thread do move animala
                world.move(animals.get(i));
            for (Vector2d position : animalsPositions) {

                //TODO eat
            }
            for (Animal animal: animals) {

            }
            world.generateNewGrassPositions(5);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
            System.out.println(world);
        }
    }

    public List<Animal> getAnimals() {
        return List.copyOf(animals);
    }
}
