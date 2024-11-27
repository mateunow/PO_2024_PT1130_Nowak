package agh.ics.oop.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TextMapTest {
    private TextMap<String, Integer> textMap;


    @Test
    public void testPlace() {
        textMap = new TextMap<>();


        assertTrue(textMap.place("Słowo0"));
        assertTrue(textMap.place("Słowo1"));
        assertEquals("Słowo0", textMap.objectAt(0));
        assertEquals("Słowo1", textMap.objectAt(1));
    }

    @Test
    public void testMoves() {
        textMap = new TextMap<>();
        textMap.place("Słowo0");
        textMap.place("Słowo1");
        textMap.place("Słowo2");


        textMap.move("Słowo0", MoveDirection.FORWARD);
        assertEquals("Słowo1", textMap.objectAt(0));
        assertEquals("Słowo0", textMap.objectAt(1));
        assertEquals("Słowo2", textMap.objectAt(2));
        textMap.move("Słowo0", MoveDirection.FORWARD);
        assertEquals("Słowo1", textMap.objectAt(0));
        assertEquals("Słowo0", textMap.objectAt(2));
        assertEquals("Słowo2", textMap.objectAt(1));
        textMap.move("Słowo0", MoveDirection.FORWARD);
        assertEquals("Słowo1", textMap.objectAt(0));
        assertEquals("Słowo0", textMap.objectAt(2));
        assertEquals("Słowo2", textMap.objectAt(1));
        textMap.move("Słowo1", MoveDirection.LEFT);
        assertEquals("Słowo1", textMap.objectAt(0));
        assertEquals("Słowo0", textMap.objectAt(2));
        assertEquals("Słowo2", textMap.objectAt(1));
        textMap.move("Słowo2", MoveDirection.RIGHT);
        assertEquals("Słowo1", textMap.objectAt(0));
        assertEquals("Słowo0", textMap.objectAt(1));
        assertEquals("Słowo2", textMap.objectAt(2));

    }

    @Test
    public void testIsOccupied() {
        textMap = new TextMap<>();
        textMap.place("1");
        textMap.place("2");


        assertTrue(textMap.isOccupied(0));
        assertTrue(textMap.isOccupied(1));
        assertFalse(textMap.isOccupied(2));
    }

    @Test
    public void testObjectAt() {
        textMap = new TextMap<>();
        textMap.place("Słowo0");
        textMap.place("Słowo1");

        assertEquals("Słowo0", textMap.objectAt(0));
        assertEquals("Słowo1", textMap.objectAt(1));
        assertNull(textMap.objectAt(2)); }

    @Test
    public void testCanMoveTo() {
        textMap = new TextMap<>();
        textMap.place("Słowo0");
        textMap.place("Słowo1");

        assertTrue(textMap.canMoveTo(0));
        assertTrue(textMap.canMoveTo(1));
        assertFalse(textMap.canMoveTo(2));
        assertFalse(textMap.canMoveTo(-1));
    }
}
