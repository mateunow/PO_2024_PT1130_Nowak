package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void correctDirectionsInMoveDirection(){
        //when
        String[] input = {"f", "b", "r", "l"};
        MoveDirection[] expected = {
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.RIGHT,
                MoveDirection.LEFT
        };

        //then
        assertArrayEquals(expected, OptionsParser.parse(input));

    }
    @Test
    void mixValidAndInvalidDirections() {
        //when
        String[] input = {"f", "x", "a", "l", "b"};
        MoveDirection[] expected = {
                MoveDirection.FORWARD,
                MoveDirection.LEFT,
                MoveDirection.BACKWARD
        };

        //then
        assertArrayEquals(expected, OptionsParser.parse(input));
    }
    @Test
    void onlyInvalidDirections() {
        //when
        String[] input = {"y", "x", "a"};
        MoveDirection[] expected = {};

        //then
        assertArrayEquals(expected, OptionsParser.parse(input));
    }

    @Test
    void EmptyInput() {
        //when
        String[] input = {""};
        MoveDirection[] expected = {};

        //then
        assertArrayEquals(expected, OptionsParser.parse(input));
    }

    @Test
    void singleDirection () {
        //when
        String[] input = {"f"};
        MoveDirection[] expected = {MoveDirection.FORWARD};

        //then
        assertArrayEquals(expected, OptionsParser.parse(input));
    }
}