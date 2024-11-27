package agh.ics.oop.model;

import agh.ics.oop.exeptions.IncorrectPositionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class GrassFieldTest {


    @Test
    public void testCanMoveToWithoutAndWithAnimal() {
        //given
        GrassField grassField = new GrassField(10);
        Vector2d position = new Vector2d(2, 3);

        //then
        assertTrue(grassField.canMoveTo(position));

        //when
        Animal animal = new Animal();
        animal.setPosition(position);
        try {
            assertTrue(grassField.place(animal));
        } catch (IncorrectPositionException e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        //then
        assertFalse(grassField.canMoveTo(position));
        assertTrue(grassField.canMoveTo(new Vector2d(100, 100)));
    }

    @Test
    public void testPlaceAnimalOnCorrectAndIncorrectPosition() {
        //given
        GrassField grassField = new GrassField(10);
        Vector2d position = new Vector2d(1, 1);
        Animal animal0 = new Animal();
        animal0.setPosition(position);

        //then
        try {
            assertTrue(grassField.place(animal0));
        } catch (IncorrectPositionException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        assertEquals(animal0, grassField.objectAt(position));

        //when
        Animal animal1 = new Animal();
        animal1.setPosition(position);
        //then
        assertThrows(IncorrectPositionException.class, () -> grassField.place(animal1));

        //when
        Animal animal2 = new Animal(MapDirection.NORTH, new Vector2d(30,11));
        //then
        try {
            assertTrue(grassField.place(animal2));
        } catch (IncorrectPositionException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        assertEquals(animal2, grassField.objectAt(new Vector2d(30,11)));
    }

    @Test
    public void testIsOccupied() {
        //given
        GrassField grassField = new GrassField(10);
        // w testach użyłem tego kodu z internetu do znajdywania losowej trawy występującej na mapie
        Vector2d grassPosition = grassField.getElements().stream()
                .filter(element -> element instanceof Grass)
                .map(WorldElement::getPosition)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No grass found"));

        //when
        Vector2d emptyPosition = new Vector2d(5, 5);
        Vector2d animalPosition = new Vector2d(2, 2);
        Animal animal = new Animal();
        animal.setPosition(animalPosition);

        //then
        assertTrue(grassField.isOccupied(grassPosition));
        assertFalse(grassField.objectAt(emptyPosition) instanceof Animal);
        try {
            assertTrue(grassField.place(animal));
        } catch (IncorrectPositionException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        assertTrue(grassField.isOccupied(animalPosition));
    }

    @Test
    public void testObjectAt() {
        //given
        GrassField grassField = new GrassField(10);
        Vector2d grassPosition = grassField.getElements().stream()
                .filter(element -> element instanceof Grass)
                .map(WorldElement::getPosition)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No grass found"));

        //when
        Vector2d animalPosition = new Vector2d(3, 3);
        Animal animal = new Animal(MapDirection.NORTH, animalPosition);
        try {
            assertTrue(grassField.place(animal));
        } catch (IncorrectPositionException e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        //then
        boolean somethingAtPosition = ((grassField.objectAt(grassPosition) instanceof Animal) || (grassField.objectAt(grassPosition) instanceof Grass));
        assertTrue(somethingAtPosition);
        assertEquals(animal, grassField.objectAt(animalPosition));
        assertNull(grassField.objectAt(new Vector2d(10, 10)));
    }

    @Test
    public void testMove() {
        //given
        GrassField grassField = new GrassField(10);
        Vector2d startPosition = new Vector2d(1, 1);
        Animal animal = new Animal(MapDirection.NORTH, startPosition);

        //when
        try {
            assertTrue(grassField.place(animal));
        } catch (IncorrectPositionException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        grassField.move(animal, MoveDirection.RIGHT);
        grassField.move(animal, MoveDirection.FORWARD);

        //then
        assertEquals(new Vector2d(2, 1), animal.getPosition());
        System.out.println(grassField.toString());
        assertTrue(grassField.isOccupied(new Vector2d(2, 1)));
        assertEquals(animal, grassField.objectAt(new Vector2d(2,1)));

        //when
        Animal animal1 = new Animal(MapDirection.NORTH, startPosition);
        try {
            assertTrue(grassField.place(animal1));
        } catch (IncorrectPositionException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        grassField.move(animal1, MoveDirection.RIGHT);
        grassField.move(animal1, MoveDirection.FORWARD);

        //then
        assertEquals(new Vector2d(1, 1), animal1.getPosition());
        assertTrue(grassField.isOccupied(new Vector2d(1, 1)));
        boolean somethingAtPosition = ((grassField.objectAt(new Vector2d(1, 1)) instanceof Animal) || (grassField.objectAt(new Vector2d(1,1)) instanceof Grass));
        assertTrue(somethingAtPosition);
    }


    @Test
    public void testToString() {
        GrassField grassField = new GrassField(10);
        int grassUnderAnimal = 0;
        if (grassField.objectAt(new Vector2d(0,0)) instanceof Grass) {
            grassUnderAnimal = 1;
        }
        Animal animal = new Animal(MapDirection.NORTH, new Vector2d(0, 0));
        try {
            assertTrue(grassField.place(animal));
        } catch (IncorrectPositionException e) {
            fail("Unexpected exception: " + e.getMessage());
        }


        String mapRepresentation = grassField.toString();
        assertTrue(mapRepresentation.contains("^"));
        long grassCount = mapRepresentation.chars().filter(ch -> ch == '*').count();

        assertEquals(10, grassCount + grassUnderAnimal);

        Vector2d grassPosition = grassField.getElements().stream()
                .filter(element -> element instanceof Grass)
                .map(WorldElement::getPosition)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No grass found"));
        System.out.println(grassField.toString()+ grassPosition);
        Animal animalOnGrass = new Animal(MapDirection.NORTH, grassPosition);
        try {
            assertTrue(grassField.place(animalOnGrass));
        } catch (IncorrectPositionException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        String mapRepresentationAfterAddingAnimal = grassField.toString();
        long grassCountAfterAddingAnimal = mapRepresentationAfterAddingAnimal.chars().filter(ch -> ch == '*').count();
        assertEquals(9, grassCountAfterAddingAnimal + grassUnderAnimal);
    }

    @Test
    public void caseWhenGrassIsUnderAnimal(){
        GrassField grassField = new GrassField(10);
        Vector2d grassPosition = grassField.getElements().stream()
                .filter(element -> element instanceof Grass)
                .map(WorldElement::getPosition)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No grass found"));

        assertTrue(grassField.isOccupied(grassPosition));
        assertTrue(grassField.objectAt(grassPosition) instanceof Grass);
        System.out.println(grassField.toString() + grassPosition);
        Animal animal = new Animal(MapDirection.NORTH, grassPosition);
        try {
            assertTrue(grassField.place(animal));
        } catch (IncorrectPositionException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        assertTrue(grassField.isOccupied(grassPosition));
        assertTrue(grassField.objectAt(grassPosition) instanceof Animal);
        System.out.println(grassField.toString());



    }

    @Test
    public void animalTestToStringMovesOutOfGrassField(){
        GrassField grassField = new GrassField(10);
        grassField.register(new ConsoleMapDisplay());
        Animal animal = new Animal(MapDirection.NORTH, new Vector2d(10, 10));
        try {
            assertTrue(grassField.place(animal));
        } catch (IncorrectPositionException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        grassField.move(animal, MoveDirection.BACKWARD);
        grassField.move(animal, MoveDirection.BACKWARD);
        grassField.move(animal, MoveDirection.BACKWARD);
        grassField.move(animal, MoveDirection.RIGHT);
        grassField.move(animal, MoveDirection.FORWARD);
        grassField.move(animal, MoveDirection.FORWARD);



    }

}
