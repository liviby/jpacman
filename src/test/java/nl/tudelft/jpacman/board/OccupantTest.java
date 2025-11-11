package nl.tudelft.jpacman.board;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test suite to confirm that {@link Unit}s correctly (de)occupy squares.
 *
 * @author Jeroen Roosen 
 *
 */
class OccupantTest {

    /**
     * The unit under test.
     */
    private Unit unit;

    /**
     * Resets the unit under test.
     */
    @BeforeEach
    void setUp() {
        unit = new BasicUnit();
    }

    /**
     * Asserts that a unit has no square to start with.
     */
    @Test
    void noStartSquare() {
        Unit unit = new BasicUnit();
        assertFalse(unit.hasSquare());
    }

    /**
     * Tests that the unit indeed has the target square as its base after
     * occupation.
     */
    @Test
    void testOccupy() {
        Square square = new BasicSquare();
        Unit unit = new BasicUnit();
        unit.occupy(square);
        assertEquals(square, unit.getSquare());
        assertTrue(square.getOccupants().contains(unit));
    }

    /**
     * Test that the unit indeed has the target square as its base after
     * double occupation.
     */
    @Test
    void testReoccupy() {
        Square first = new BasicSquare();
        Square second = new BasicSquare();
        Unit unit = new BasicUnit();
        unit.occupy(first);
        unit.occupy(second);
        assertEquals(second, unit.getSquare());
        assertFalse(first.getOccupants().contains(unit));
    }
}
