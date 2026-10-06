import org.junit.jupiter.api.Test;
import gameoflife.grid.Grid;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {

    @Test
    void testGridDefaultState() {
        Grid grid = new Grid(5, 5, false, 0); // Üres grid
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                assertFalse(grid.getCellState(r, c));
            }
        }
    }


    @Test
    void testIsInBounds() {
        Grid grid = new Grid(5, 5, false, 0);
        assertTrue(grid.isInBounds(0, 0));
        assertTrue(grid.isInBounds(4, 4));
        assertFalse(grid.isInBounds(5, 5));
        assertFalse(grid.isInBounds(-1, 0));
    }

    @Test
    void testGetCellState() {
        Grid grid = new Grid(3, 3, false, 0);
        assertFalse(grid.getCellState(1, 1));
        grid.toggleCellState(1, 1);
        assertTrue(grid.getCellState(1, 1));
    }

    @Test
    void testToggleCellState() {
        Grid grid = new Grid(3, 3, false, 0);
        grid.toggleCellState(1, 1);
        assertTrue(grid.getCellState(1, 1));
        grid.toggleCellState(1, 1);
        assertFalse(grid.getCellState(1, 1));
    }

    @Test
    void testToggleAliveCellState() {
        Grid grid = new Grid(3, 3, false, 0);
        grid.toggleCellState(1, 1);
        grid.toggleAliveCellState(1, 1);
        assertFalse(grid.getCellState(1, 1));
        grid.toggleAliveCellState(1, 1);
        assertFalse(grid.getCellState(1, 1));
    }

    @Test
    void testClearGrid() {
        Grid grid = new Grid(3, 3, false, 0);
        grid.toggleCellState(0, 0);
        grid.clearGrid();
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                assertFalse(grid.getCellState(r, c));
            }
        }
    }

    @Test
    void testRandomize() {
        Grid grid = new Grid(10, 10, false, 0);
        grid.randomize(0.0);
        int aliveCount = 0;
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                if (grid.getCellState(r, c)) aliveCount++;
            }
        }
        assertEquals(0, aliveCount);
    }

}
