package agh.ics.oop;


import agh.ics.oop.model.*;

import java.util.List;


public class Word {
         public static void main (String[] args){

             List<MoveDirection> directions = OptionsParser.parse(args);
             List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
             //AbstractWorldMap map = new GrassField(8);
             AbstractWorldMap map = new RectangularMap(5,5);
             map.register(new ConsoleMapDisplay());
             Simulation simulation = new Simulation(positions, directions, map);
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


