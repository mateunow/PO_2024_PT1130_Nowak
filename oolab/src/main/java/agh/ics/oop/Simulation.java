package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation<T,P>
{
    private final List<T> animals;
    private final List<MoveDirection> moves;
    private final WorldMap<T,P> world;


    public Simulation(List<T> animals, List<MoveDirection> moves, WorldMap<T,P> world) {
        this.moves = moves;
        this.world = world;
        this.animals = animals;

        for (T animal : animals) {
            if (!world.place(animal)) {
                throw new IllegalArgumentException("Nie można umieścić zwierzęcia na mapie: " + animal);
            }
        }
}

    public void run(){
        int animalsCount = animals.size();
        for  (int i = 0; i < moves.size(); i++){
            world.move(animals.get(i % animalsCount), moves.get(i));
            System.out.println(world);
        }

    }

    public List<T> getAnimals() {
        return List.copyOf(animals);
    }
}
