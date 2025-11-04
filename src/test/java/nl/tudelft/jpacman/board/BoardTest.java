package nl.tudelft.jpacman.board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Board class.
 */
public class BoardTest {

    @Test
    void testValidBoard() {
        Square[][] grid = new Square[][] {
            { new BasicSquare() }
        };
        Board board = new Board(grid);
        assertNotNull(board.squareAt(0, 0), "Square at (0,0) should not be null");
    }

    @Test
    void testInvalidBoard() {
        Square[][] grid = new Square[][] {
            { null }
        };
        // The Board constructor uses assertions to check invariants.
        assertThrows(AssertionError.class, () -> new Board(grid), "Board with null square should fail invariant check");
    }
}
