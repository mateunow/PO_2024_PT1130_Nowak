package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {


    @Test
    public void testRunWithValidMoveDirection () {
        //given
        List<MoveDirection> directions = new ArrayList<>();
        WorldMap map = new RectangularMap(5,5);
        // W tym teście użyłem innego sposobu zapisu kierunków poruszania się, moim zdaniem jest on mniej czytelny, ponieważ
        // zajmuje zbyt wiele miejsca (w porównaniu do jednej linijki w innych testach), więc w następnych przykładach
        // nie używałem go. Proszę o komentarz czy decyzja była słuszna, czy trzeba sprawdzać również w ten sposób.
        directions.add(MoveDirection.RIGHT);
        directions.add(MoveDirection.LEFT);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.RIGHT);
        directions.add(MoveDirection.BACKWARD);
        directions.add(MoveDirection.BACKWARD);
        directions.add(MoveDirection.BACKWARD);
        List<Vector2d> positions =List.of(new Vector2d(2,2));


        //when
        Simulation simulation = new Simulation(positions, directions,map);
        simulation.run();
        List<Animal> animals = simulation.getAnimals();
        Animal simulatedAnimal = animals.get(0);

        //then
        assertEquals(1, animals.size());
        assertTrue(simulatedAnimal.getPosition().precedes(new Vector2d(4, 4)) && simulatedAnimal.getPosition().follows(new Vector2d(0, 0)));
        assertEquals(MapDirection.EAST, simulatedAnimal.getDirection());


    }

    @Test
    public void testRunWithInvalidMoveDirection () {
        //given
        WorldMap map = new RectangularMap(5,5);
        String[] directionsArray = "m a v x c h m p".split(" ");
        List<MoveDirection> directions = OptionsParser.parse(directionsArray);
        List<Vector2d> positions =List.of(new Vector2d(2,2));

        //when
        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
        List<Animal> animals = simulation.getAnimals();
        Animal simulatedAnimal = animals.get(0);

        //then
        assertEquals(MapDirection.NORTH, simulatedAnimal.getDirection());
        assertEquals(new Vector2d(2,2), simulatedAnimal.getPosition());
        }

    @Test
    public void runWithValidAndInvalidMoveDirection () {
        //given
        WorldMap map = new RectangularMap(5,5);
        String[] directionsArray = "m f f l l f b r f v x c h m pf f f f f f".split(" ");
        List<MoveDirection> directions = OptionsParser.parse(directionsArray);
        List<Vector2d> positions =List.of(new Vector2d(2,2));

        //when
        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
        List<Animal> animals = simulation.getAnimals();
        Animal simulatedAnimal = animals.get(0);

        //then
        assertEquals(MapDirection.WEST, simulatedAnimal.getDirection());
        assertTrue(simulatedAnimal.isAt(new Vector2d(0,4)));
        assertTrue(simulatedAnimal.getPosition().follows(new Vector2d(0, 0)) && simulatedAnimal.getPosition().precedes(new Vector2d(4, 4)));

    }

    @Test
    public void testSimulationWithTwoAnimalsCorrectAndIncorrectDirectionsAndCrossingEachOther () {
        //given
        WorldMap map = new RectangularMap(5,5);
        String[] directionsArray = "r l f f x f f c r a b f b f r f f r f f v m f f b f f j f f l l k f f f f".split(" ");
        List<MoveDirection> directions = OptionsParser.parse(directionsArray);
        List<Vector2d> positions =List.of(new Vector2d(0,4), new Vector2d(2,2));

        //when
        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
        List<Animal> animals = simulation.getAnimals();
        Animal simulatedAnimal0 = animals.get(0);
        Animal simulatedAnimal1 = animals.get(1);

        //then
        assertEquals(2, animals.size());
        assertTrue(simulatedAnimal0.getPosition().precedes(new Vector2d(4, 4)) && simulatedAnimal0.getPosition().follows(new Vector2d(0, 0)));
        assertTrue(simulatedAnimal1.getPosition().precedes(new Vector2d(4,4)) && simulatedAnimal1.getPosition().follows(new Vector2d (0,0)));
        assertEquals(MapDirection.SOUTH, simulatedAnimal0.getDirection());
        assertEquals(MapDirection.WEST, simulatedAnimal1.getDirection());
        assertTrue(simulatedAnimal0.isAt(new Vector2d (0,1)));
        assertTrue(simulatedAnimal1.isAt(new Vector2d (0, 4)));


    }

    @Test
    public void testStartAnimalsOneCorrectOneWithWrongStartingPositionSet () {
        String[] directionsArray = "m f f ".split(" ");
        WorldMap map = new RectangularMap(5,5);
        List<MoveDirection> directions = OptionsParser.parse(directionsArray);
        List<Vector2d> positions =List.of(new Vector2d(2,2), new Vector2d(-1,30));


        Exception exception = null;
        try {
            Simulation simulation = new Simulation(positions, directions, map);
            simulation.run();
        } catch (IllegalArgumentException e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals("Invalid starting position for an animal", exception.getMessage());
    }
}