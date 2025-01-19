package darwinProject.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    public void startingAnimal() {
        Integer genomeSize = 20;
        Vector2d position = new Vector2d(0, 0);
        Animal animal = new Animal(position, genomeSize);

        System.out.println(animal.getGenome());
        assertEquals(genomeSize, animal.getGenome().size());
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