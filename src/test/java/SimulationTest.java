import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import gameoflife.grid.Grid;
import gameoflife.simulation.Simulation;
import gameoflife.ui.GridPanel;
import java.awt.Color;

public class SimulationTest {

    @Test
    void testRulesInitialization() {
        Grid grid = new Grid(10, 10, false, 0.0);
        GridPanel gridPanel = new GridPanel(grid, Color.GREEN, Color.BLACK, true, 10, false);
        Simulation simulation = new Simulation(grid, gridPanel, 5, "23", "3");

        assertEquals("3", simulation.getBirthRules());
        assertEquals("23", simulation.getSurvivalRules());
    }


    @Test
    void testSpeedInitialization() {
        Grid grid = new Grid(10, 10, false, 0.0);
        GridPanel gridPanel = new GridPanel(grid, Color.GREEN, Color.BLACK, true, 10, false);
        Simulation simulation = new Simulation(grid, gridPanel, 10, "23", "3");

        assertEquals(10, simulation.getSpeed());
    }

    @Test
    void testStartAndPause() {
        Grid grid = new Grid(10, 10, false, 0.0);
        GridPanel gridPanel = new GridPanel(grid, Color.GREEN, Color.BLACK, true, 10, false);
        Simulation simulation = new Simulation(grid, gridPanel, 10, "23", "3");

        assertFalse(simulation.getTimer().isRunning());

        simulation.start();
        assertTrue(simulation.getTimer().isRunning());

        simulation.pause();
        assertFalse(simulation.getTimer().isRunning());
    }


}
