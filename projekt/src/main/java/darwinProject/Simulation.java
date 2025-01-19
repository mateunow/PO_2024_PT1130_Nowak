package darwinProject;

import darwinProject.model.maps.AbstractWorldMap;
import darwinProject.model.Animal;
import darwinProject.model.Vector2d;
import darwinProject.enums.MoveDirection;
import darwinProject.model.exceptions.IncorrectPositionException;

import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable
{
    private final List<Animal> animals;
    private final List<MoveDirection> moves;
    private final AbstractWorldMap world;
    private final Integer numberOfGenes;
    private final Integer startingEnergy;


    public Simulation(List<Vector2d> positions, List<MoveDirection> moves, AbstractWorldMap world, Integer numberOfGenes, Integer startingEnergy) {
        this.moves = moves;
        this.world = world;
        this.numberOfGenes = numberOfGenes;
        this.startingEnergy = startingEnergy;
        this.animals = new ArrayList<>();

        for (Vector2d position : positions) {
            try {
                Animal animal = new Animal(position, numberOfGenes, startingEnergy);
                world.place(animal);
                this.animals.add(animal);
            }
            catch (IncorrectPositionException e) {
                System.out.println("Incorrect position " + e.getMessage());
            }
        }

}

    public void run(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            //try i catch w celu poczekania na wyświetlenie początkowej mapy zanim
//        pierwsze zwierze zrobi ruch

        int animalsCount = animals.size();
        for (int i = 0; i < moves.size(); i++) {
            world.move(animals.get(i % animalsCount), moves.get(i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Animal> getAnimals() {
        return List.copyOf(animals);
    }
}
