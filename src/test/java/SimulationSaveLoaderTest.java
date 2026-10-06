

import org.junit.jupiter.api.Test;
import gameoflife.save.SimulationSave;
import gameoflife.save.SimulationLoader;


import java.awt.*;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class SimulationSaveLoaderTest {

    @Test
    void testLoadSimulation() {
        File testFile = new File("src/main/resources/saves/test_junit.txt");
        SimulationSave save = SimulationLoader.loadSimulation(testFile);

        assertNotNull(save, "A betöltött mentés nem lehet null.");
        assertEquals(10, save.getRows(), "A sorok száma nem egyezik.");
        assertEquals(10, save.getCols(), "Az oszlopok száma nem egyezik.");
        assertEquals("23", save.getSurvivalRules(), "A túlélési szabályok nem egyeznek.");
        assertEquals("3", save.getBirthRules(), "A születési szabályok nem egyeznek.");
        assertEquals(Color.BLUE, save.getAliveColor(), "Az élő cellák színe nem egyezik.");
        assertEquals(Color.BLACK, save.getDeadColor(), "A halott cellák színe nem egyezik.");
    }
}
