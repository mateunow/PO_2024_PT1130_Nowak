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