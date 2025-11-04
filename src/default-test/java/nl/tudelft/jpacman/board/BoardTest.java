package nl.tudelft.jpacman.board;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Test various aspects of board.
 *
 * @author Jeroen Roosen 
 */
class BoardTest {

    private static final int MAX_WIDTH = 2;
    private static final int MAX_HEIGHT = 3;

    private final Square[][] grid = {
        { mock(Square.class), mock(Square.class), mock(Square.class) },
        { mock(Square.class), mock(Square.class), mock(Square.class) },
    };
    private final Board board = new Board(grid);

    /**
     * Verifies the board has the correct width.
     */
    @Test
    void verifyWidth() {
        assertThat(board.getWidth()).isEqualTo(MAX_WIDTH);
    }

    /**
     * Verifies the board has the correct height.
     */
    @Test
    void verifyHeight() {
        assertThat(board.getHeight()).isEqualTo(MAX_HEIGHT);
    }

    /**
     * Verify that squares at key positions are properly set.
     * @param x Horizontal coordinate of relevant cell.
     * @param y Vertical coordinate of relevant cell.
     */
    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "1, 2",
        "0, 1"
    })
    void testSquareAt(int x, int y) {
        assertThat(board.squareAt(x, y)).isEqualTo(grid[x][y]);
    }

    /**
     * Tests that a valid board (1x1 BasicSquare) passes invariant check.
     */
    @Test
    void testValidBoardInvariant() {
        Square[][] validGrid = new Square[][] {
            { new BasicSquare() }
        };
        Board validBoard = new Board(validGrid);

        // squareAt should return a non-null square
        assertThat(validBoard.squareAt(0, 0)).isNotNull();
    }

    /**
     * Tests that a board with a null square triggers an assertion error.
     */
    @Test
    void testInvalidBoardInvariant() {
        Square[][] invalidGrid = new Square[][] {
            { null }
        };

        // Expect AssertionError because of board invariant violation
        org.junit.jupiter.api.Assertions.assertThrows(AssertionError.class, () -> new Board(invalidGrid));
    }

}
