package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.PacmanConfigurationException;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.npc.ghost.Blinky;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * This is a test class for MapParser.
 */
@ExtendWith(MockitoExtension.class)
public class MapParserTest {

    private static final int EXPECTED_WALL_COUNT = 26;
    private static final int EXPECTED_GROUND_COUNT = 10;

    @Mock
    private BoardFactory boardFactory;
    @Mock
    private LevelFactory levelFactory;
    @Mock
    private Blinky blinky;

    /**
     * Test for the parseMap method (good map).
     */
    @Test
    public void testParseMapGood() {
        assertNotNull(boardFactory);
        assertNotNull(levelFactory);

        // Stubbing: When createGhost() is called, return the mocked blinky object.
        Mockito.when(levelFactory.createGhost()).thenReturn(blinky);

        MapParser mapParser = new MapParser(levelFactory, boardFactory);
        ArrayList<String> map = new ArrayList<>();
        map.add("############");
        map.add("#P        G#");
        map.add("############");

        mapParser.parseMap(map);

        Mockito.verify(levelFactory, Mockito.times(1)).createGhost();
        Mockito.verify(boardFactory, Mockito.times(EXPECTED_WALL_COUNT)).createWall();
        Mockito.verify(boardFactory, Mockito.times(EXPECTED_GROUND_COUNT)).createGround();
        Mockito.verify(boardFactory, Mockito.times(1)).createBoard(Mockito.any());
        Mockito.verify(levelFactory, Mockito.times(1)).createLevel(
            Mockito.any(),
            Mockito.any(),
            Mockito.any()
        );
    }

    /**
     * Test for the parseMap method (bad map).
     * Should throw PacmanConfigurationException when map is malformed (inconsistent row width).
     */
    @Test
    public void testParseMapWrong1() {
        PacmanConfigurationException thrown =
            Assertions.assertThrows(PacmanConfigurationException.class, () -> {
                assertNotNull(boardFactory);
                assertNotNull(levelFactory);

                MapParser mapParser = new MapParser(levelFactory, boardFactory);
                ArrayList<String> map = new ArrayList<>();

                // Map with unequal width (11, 8, 11)
                map.add("###########");
                map.add("#P  @ G#");
                map.add("###########");

                mapParser.parseMap(map);
            });

        Assertions.assertEquals("Input text lines are not of equal width.", thrown.getMessage());
    }
}
