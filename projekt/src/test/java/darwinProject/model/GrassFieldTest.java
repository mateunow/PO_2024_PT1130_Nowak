package darwinProject.model;

import darwinProject.model.exceptions.IncorrectPositionException;
import darwinProject.model.maps.GrassField;
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
        Animal animal = new Animal(position, 7, 50);
        assertDoesNotThrow(() -> {grassField.place(animal);});


        //then
        assertFalse(grassField.canMoveTo(position));
        assertTrue(grassField.canMoveTo(new Vector2d(100, 100)));
    }

    @Test
    public void testPlaceAnimalOnCorrectAndIncorrectPosition() {
        //given
        GrassField grassField = new GrassField(10);
        Vector2d position = new Vector2d(1, 1);
        Animal animal0 = new Animal(position, 7, 50);

        //then
        assertDoesNotThrow(() -> {grassField.place(animal0);});


        assertEquals(animal0, grassField.objectAt(position));

        //when
        Animal animal1 = new Animal(position, 7, 50);
        //then
        assertThrows(IncorrectPositionException.class, () -> grassField.place(animal1));

        //when
        Animal animal2 = new Animal(new Vector2d(30,11), 7, 50);
        //then
        assertDoesNotThrow(() -> {grassField.place(animal2);});
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
        Animal animal = new Animal(animalPosition, 7, 50);

        //then
        assertTrue(grassField.isOccupied(grassPosition));
        assertFalse(grassField.objectAt(emptyPosition) instanceof Animal);
        assertDoesNotThrow(() -> {grassField.place(animal);});
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
        Animal animal = new Animal(animalPosition, 7, 50);
        assertDoesNotThrow(() -> {grassField.place(animal);});

        //then
        boolean somethingAtPosition = ((grassField.objectAt(grassPosition) instanceof Animal) || (grassField.objectAt(grassPosition) instanceof Grass));
        assertTrue(somethingAtPosition);
        assertEquals(animal, grassField.objectAt(animalPosition));
        assertNull(grassField.objectAt(new Vector2d(10, 10)));
    }

//    @Test
//    public void testMove() {
//        //given
//        GrassField grassField = new GrassField(10);
//        Vector2d startPosition = new Vector2d(1, 1);
//        Animal animal = new Animal(startPosition, 7, 50);
//
//        //when
//        assertDoesNotThrow(() -> {grassField.place(animal);});
//
//        grassField.move(animal, MoveDirection.RIGHT);
//        grassField.move(animal, MoveDirection.FORWARD);
//
//        //then
//        assertEquals(new Vector2d(2, 1), animal.getPosition());
//        System.out.println(grassField.toString());
//        assertTrue(grassField.isOccupied(new Vector2d(2, 1)));
//        assertEquals(animal, grassField.objectAt(new Vector2d(2,1)));
//
//        //when
//        Animal animal1 = new Animal(startPosition, 7, 50);
//        assertDoesNotThrow(() -> {grassField.place(animal1);});
//        grassField.move(animal1, MoveDirection.RIGHT);
//        grassField.move(animal1, MoveDirection.FORWARD);
//
//        //then
//        assertEquals(new Vector2d(1, 1), animal1.getPosition());
//        assertTrue(grassField.isOccupied(new Vector2d(1, 1)));
//        boolean somethingAtPosition = ((grassField.objectAt(new Vector2d(1, 1)) instanceof Animal) || (grassField.objectAt(new Vector2d(1,1)) instanceof Grass));
//        assertTrue(somethingAtPosition);
//    }


    @Test
    public void testToString() {
        GrassField grassField = new GrassField(10);
        int grassUnderAnimal = 0;
        if (grassField.objectAt(new Vector2d(0,0)) instanceof Grass) {
            grassUnderAnimal = 1;
        }
        Animal animal = new Animal(new Vector2d(0, 0), 7, 50);
        assertDoesNotThrow(() -> {grassField.place(animal);});



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
        Animal animalOnGrass = new Animal(grassPosition, 7, 50);
        assertDoesNotThrow(() -> {grassField.place(animalOnGrass);});
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
        assertInstanceOf(Grass.class, grassField.objectAt(grassPosition));
        System.out.println(grassField.toString() + grassPosition);
        Animal animal = new Animal(grassPosition, 7, 50);
        assertDoesNotThrow(() -> {grassField.place(animal);});
        assertTrue(grassField.isOccupied(grassPosition));
        assertTrue(grassField.objectAt(grassPosition) instanceof Animal);
        System.out.println(grassField.toString());



    }

    @Test
    public void animalTestToStringMovesOutOfGrassField(){
        GrassField grassField = new GrassField(10);
        grassField.registerObservers(new ConsoleMapDisplay());
        Animal animal = new Animal(new Vector2d(10, 10), 7, 50);
        assertDoesNotThrow(() -> {grassField.place(animal);});
        //TODO CHECK IF CAN MOVE OUT OF GRASSFIELD

    }

}
