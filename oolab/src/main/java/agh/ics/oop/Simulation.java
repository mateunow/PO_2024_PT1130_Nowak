package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation
{
    private final List<Animal> animals = new ArrayList<>();
    private final List<MoveDirection> moves;


    public Simulation(List<Vector2d> positions, List<MoveDirection> moves) {
        this.moves = moves;

        for (Vector2d position : positions) {
            Animal animal = new Animal();
            animal.setPosition(position);
            animals.add(animal);
        }

}

    public void run(){
        int animalsCount = animals.size();
        for  (int i = 0; i < moves.size(); i++){
            Animal currentAnimal = animals.get(i % animalsCount);
            currentAnimal.move(moves.get(i));
            System.out.println( "Zwierzę" + (i%animalsCount) + ":" + currentAnimal.toString());
        }

    }

    public List<Animal> getAnimals() {
        return animals;
    }
}
