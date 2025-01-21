package darwinProject.model.maps;

import darwinProject.model.Animal;
import darwinProject.model.Vector2d;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EarthMapTest {

    @Test
    public void testStartingMap() {
        EarthMap map = new EarthMap(10, 10, 15);
        Animal animal = new Animal(new Vector2d(1,1), 7, 50);
        Animal animal2 = new Animal(new Vector2d(3,3), 7, 50);
        Animal animal3 = new Animal(new Vector2d(8,9), 7, 50);
        Animal animal4 = new Animal(new Vector2d(9,8), 7, 50);
        Animal animal5 = new Animal(new Vector2d(9,9), 7, 50);

        //when
        assertDoesNotThrow(() -> map.place(animal));
        assertDoesNotThrow(() -> map.place(animal2));
        assertDoesNotThrow(() -> map.place(animal3));
        assertDoesNotThrow(() -> map.place(animal4));
        assertDoesNotThrow(() -> map.place(animal5));
//        assertThrows(IncorrectPositionException.class, () -> {map.place(animal2);});
        System.out.println(map);

        for (int i= 0; i < 100; i++) {
            map.move(animal);
            map.move(animal2);
            map.move(animal3);
            map.move(animal4);
            map.move(animal5);
            System.out.println(map);
        }
    }
    @Test
    void canMoveTo() {
    }
}