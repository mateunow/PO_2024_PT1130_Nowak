package agh.ics.oop;


import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.List;

public class Word {
         public static void main (String[] args){
             Animal animal1 = new Animal();
             System.out.println(animal1.toString()); //z wykorzystaniem funkcji podanej w poleceniu, która zwraca też orientacje
             System.out.println(animal1.getPosition()); //ze stworzeniem funckji get uzysukącej samą pozycje bez orientacji


             List<MoveDirection> directions = OptionsParser.parse(args);
             List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
             Simulation simulation = new Simulation(positions, directions);
             simulation.run();
         }

    public static void run(MoveDirection[] directions) {
        for (MoveDirection direction : directions) {
            switch (direction) {
                case FORWARD:
                    System.out.println("Zwierzak idzie do przodu");
                    break;
                case BACKWARD:
                    System.out.println("Zwierzak idzie do tyłu");
                    break;
                case RIGHT:
                    System.out.println("Zwierzak skręca w prawo");
                    break;
                case LEFT:
                    System.out.println("Zwierzak skręca w lewo");
                    break;
            }
        }
    }



}


