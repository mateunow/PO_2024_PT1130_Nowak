package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation
{
    private final List<Animal> animals = new ArrayList<>();
    private final List<MoveDirection> moves;
    private final WorldMap world;


    public Simulation(List<Vector2d> positions, List<MoveDirection> moves, WorldMap world) {
        this.moves = moves;
        this.world = world;

        for (Vector2d position : positions) {
            Animal animal = new Animal();
            animal.setPosition(position);
            animals.add(animal);
        }

}

    public void run(){
        int animalsCount = animals.size();
        for  (int i = 0; i < moves.size(); i++){
            world.move(animals.get(i % animalsCount), moves.get(i));
            System.out.println(world);
        }

    }

    public List<Animal> getAnimals() {
        return List.copyOf(animals);
    }
}
