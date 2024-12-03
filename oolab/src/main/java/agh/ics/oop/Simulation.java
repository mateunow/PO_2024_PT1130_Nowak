package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.exeptions.IncorrectPositionException;

import java.util.ArrayList;
import java.util.List;

public class Simulation
{
    private final List<Animal> animals;
    private final List<MoveDirection> moves;
    private final WorldMap world;


    public Simulation(List<Vector2d> positions, List<MoveDirection> moves, WorldMap world) {
        this.moves = moves;
        this.world = world;
        this.animals = new ArrayList<>();

        for (Vector2d position : positions) {
            try {
                Animal animal = new Animal(MapDirection.NORTH, position);
                if (world.place(animal)) {
                    this.animals.add(animal);
                }
            }
            catch (IncorrectPositionException e) {
                System.out.println("Incorrect position " + e.getMessage());
            }
        }

}

    public void run(){
        int animalsCount = animals.size();
        for  (int i = 0; i < moves.size(); i++){
            world.move(animals.get(i % animalsCount), moves.get(i));
        }

    }

    public List<Animal> getAnimals() {
        return List.copyOf(animals);
    }
}
