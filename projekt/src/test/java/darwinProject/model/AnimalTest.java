package darwinProject.model;

import darwinProject.enums.MapDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    public void startingAnimal() {
        Integer genomeSize = 20;
        Vector2d position = new Vector2d(0, 0);
        Animal animal = new Animal(position, genomeSize, 50);

        System.out.println(animal.getGenome());
        assertEquals(genomeSize, animal.getGenome().size());
    }

    @Test
    public void testTurn() {
        Animal animal = new Animal(new Vector2d(2,2), 7, 50);
        MapDirection direction = animal.getDirection();
        System.out.println(animal + " " + direction);
        animal.turn(4);
        System.out.println(animal + " " + direction);
        assertEquals(direction.turn(4), animal.getDirection());
        direction = direction.turn(4);
        animal.turn(3);
        System.out.println(animal + " " + direction);
        assertEquals(direction.turn(3), animal.getDirection());


    }

    @Test
    public void testReproduction() {
        Animal animal = new Animal(new Vector2d(2,2), 7, 70);
        Animal animal2 = new Animal(new Vector2d(2,2), 7, 50);
        System.out.println("First animal genome: " + animal.getGenome());
        System.out.println("Second animal genome: " + animal2.getGenome());

        Animal animal3 = animal.reproduceWithOtherAnimal(animal2, 20);
        assertEquals(50, animal.getEnergy());
        assertEquals(30, animal2.getEnergy());
        assertEquals(40, animal3.getEnergy());
        assertEquals(0, animal3.getChildrenCount());
        assertEquals(1, animal2.getChildrenCount());
        assertEquals(1, animal.getChildrenCount());
        System.out.println("Child genome: " + animal3.getGenome());
    }
    @Test
    void setPosition() {
    }

    @Test
    void testToString() {
    }

    @Test
    void move() {
    }

    @Test
    void getPosition() {
    }

    @Test
    void getDirection() {
    }

    @Test
    void isAt() {
    }
}