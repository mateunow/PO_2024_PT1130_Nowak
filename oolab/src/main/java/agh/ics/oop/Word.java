package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class Word {
         public static void main (String[] args){
            try {
                List<MoveDirection> directions = OptionsParser.parse(args);
                List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4), new Vector2d(1, 2), new Vector2d(2, 4));
//             GrassField grassMap = new GrassField(8);
//             RectangularMap map = new RectangularMap(5,5);
//             map.registerObservers(new ConsoleMapDisplay());
//             grassMap.registerObservers(new ConsoleMapDisplay());
//             Simulation simulation = new Simulation(positions, directions, map);
//             Simulation grassSimulation = new Simulation(positions, directions, grassMap);
//             SimulationEngine engine = new SimulationEngine(List.of(simulation2, simulation));
//             engine.runAsync();


                List<Simulation> simulations = new ArrayList<>();
                for (int i = 0; i < 1000; i++) {
                    RectangularMap rectangularMap = new RectangularMap(5, 5);
                    GrassField grassField = new GrassField(9);
                    rectangularMap.registerObservers(new ConsoleMapDisplay());
                    grassField.registerObservers(new ConsoleMapDisplay());
                    Simulation simulation = new Simulation(positions, directions, rectangularMap);
                    Simulation simulation1 = new Simulation(positions, directions, grassField);
                    simulations.add(simulation);
                    simulations.add(simulation1);
                }
                SimulationEngine engine = new SimulationEngine(simulations);
//             engine.runAsync();
                engine.runAsyncInThreadPool();
            }
            catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }

             System.out.println("System zakończył działanie");
//
         }
}