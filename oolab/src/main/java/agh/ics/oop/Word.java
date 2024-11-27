package agh.ics.oop;


import agh.ics.oop.model.*;

import java.util.List;

public class Word {
         public static void main (String[] args){

             List<MoveDirection> directions = OptionsParser.parse(args);
             Animal animal1 = new Animal();
             animal1.setPosition(new Vector2d(1,1));
             Animal animal2 = new Animal();
             List<Animal> positions = List.of(animal1, animal2);
             WorldMap<Animal,Vector2d> map = new RectangularMap(5,5);
             Simulation<Animal, Vector2d> simulation = new Simulation<Animal, Vector2d>(positions, directions, map);
             simulation.run();



         // EXTRA TASK FOR LAB04
             List<String> strings= List.of("Mateusz", "ma", "niedzwiedziosowe");
             WorldMap<String, Integer> textMap = new TextMap();
             for (String text : strings) {
                 textMap.place(text);
             }
             System.out.println(textMap);
             textMap.move("ma", MoveDirection.FORWARD);
             System.out.println(textMap);
             textMap.move("ma", MoveDirection.RIGHT);
             System.out.println(textMap);
             textMap.move("ma", MoveDirection.LEFT);
             System.out.println(textMap);



         }




}


