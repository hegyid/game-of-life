/**
 * A GameController osztály felelős a felhasználói beállítások alapján
 * történő szimuláció indításának kezeléséért.
 * Feladatai közé tartozik a bemenet validálása, valamint a mentett szimulációk betöltése.
 */

package gameoflife.controller;

import gameoflife.grid.Grid;
import gameoflife.simulation.Simulation;
import gameoflife.ui.GameSettingsPanel;
import gameoflife.ui.GridPanel;
import gameoflife.ui.SimulationFrame;
import gameoflife.ui.GridFrame;
import gameoflife.save.SimulationSave;

import javax.swing.*;
import java.awt.*;

public class GameController {
    private final GameSettingsPanel gameSettingsPanel;

    /**
     * Létrehozza a GameController-t a megadott beállítási panellel.
     *
     * @param gameSettingsPanel a játékbeállításokat kezelő panel
     */
    public GameController(GameSettingsPanel gameSettingsPanel) {
        this.gameSettingsPanel = gameSettingsPanel;
    }


    /**
     * Elindítja a szimulációt a felhasználó által megadott beállítások alapján.
     * Ha a beállítások érvénytelenek, hibaüzenetet jelenít meg.
     */
    public void startSimulation() {
        try {
            validateInput();

            String survivalRules = gameSettingsPanel.getSurvivalRules();
            String birthRules = gameSettingsPanel.getBirthRules();
            int gridWidth = gameSettingsPanel.getGridWidth();
            int gridHeight = gameSettingsPanel.getGridHeight();
            int simulationSpeed = gameSettingsPanel.getSimulationSpeed();
            int cellSize = gameSettingsPanel.getCellSize();
            boolean showGridLines = gameSettingsPanel.isShowGridLines();
            Color aliveColor = gameSettingsPanel.getAliveColor();
            Color deadColor = gameSettingsPanel.getDeadColor();
            boolean randomInitialState = gameSettingsPanel.isRandomInitialState();
            int randomProbability = gameSettingsPanel.getRandomProbability();

            if (randomInitialState) {
                Grid grid = new Grid(gridWidth, gridHeight, true, randomProbability / 100.0);
                GridPanel gridPanel = new GridPanel(grid, aliveColor, deadColor, showGridLines, cellSize, false);
                Simulation simulation = new Simulation(grid, gridPanel, simulationSpeed, survivalRules, birthRules);
                SimulationFrame simulationFrame = new SimulationFrame(gridPanel, simulation);
                simulationFrame.setVisible(true);
                simulation.start();
            } else {
                Grid grid = new Grid(gridWidth, gridHeight, false, 0.0);
                GridPanel gridPanel = new GridPanel(grid, aliveColor, deadColor, showGridLines, cellSize, false);
                GridFrame gridFrame = new GridFrame(grid, gridPanel, this, simulationSpeed, survivalRules, birthRules);
                gridFrame.setVisible(true);
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Hiba", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Elindítja a manuálisan konfigurált szimulációt egy megadott rács és panel alapján.
     *
     * @param grid             a szimuláció rácsa
     * @param gridPanel        a szimuláció grafikus panelje
     * @param simulationSpeed  a szimuláció sebessége
     * @param survivalRules    a túlélési szabályok
     * @param birthRules       a születési szabályok
     */
    public void startManualSimulation(Grid grid, GridPanel gridPanel, int simulationSpeed, String survivalRules, String birthRules) {
        Simulation simulation = new Simulation(grid, gridPanel, simulationSpeed, survivalRules, birthRules);
        SimulationFrame simulationFrame = new SimulationFrame(gridPanel, simulation);
        simulationFrame.setVisible(true);
        simulation.start();
    }

    /**
     * Visszaadja a beállítási panelt, amelyet a játékbeállítások megadásához használnak.
     *
     * @return a GameSettingsPanel objektum
     */
    public GameSettingsPanel getGameSettingsPanel() {
        return gameSettingsPanel;
    }

    /**
     * Betölti a mentett szimulációt és elindítja azt egy új ablakban.
     *
     * @param save a betöltött SimulationSave objektum
     */
    public void loadSavedSimulation(SimulationSave save) {
        Grid grid = save.getGrid();
        GridPanel gridPanel = new GridPanel(grid, save.getAliveColor(), save.getDeadColor(), save.isShowGridLines(), save.getCellSize(), false);
        Simulation simulation = new Simulation(grid, gridPanel, save.getSimulationSpeed(), save.getSurvivalRules(), save.getBirthRules());
        SimulationFrame simulationFrame = new SimulationFrame(gridPanel, simulation);
        simulationFrame.setVisible(true);
        simulation.start();
    }

    /**
     * Ellenőrzi a felhasználó által megadott értékeket
     *
     * @throws IllegalArgumentException ha a bemenetek bármelyike nem felel meg a követelményeknek
     */
    public void validateInput() throws IllegalArgumentException {
        int gridWidth = gameSettingsPanel.getGridWidth();
        int gridHeight = gameSettingsPanel.getGridHeight();
        int simulationSpeed = gameSettingsPanel.getSimulationSpeed();
        int cellSize = gameSettingsPanel.getCellSize();
        String survivalRules = gameSettingsPanel.getSurvivalRules();
        String birthRules = gameSettingsPanel.getBirthRules();
        Color aliveColor = gameSettingsPanel.getAliveColor();
        Color deadColor = gameSettingsPanel.getDeadColor();

        if(gridWidth > 1400 || gridHeight > 700) {
            throw new IllegalArgumentException("A rács max 1400 széles és 700 magas lehet");
        }

        if (gridWidth <= 0 || gridHeight <= 0) {
            throw new IllegalArgumentException("A rács szélessége és magassága pozitív szám kell legyen.");
        }

        if (cellSize <= 0 || cellSize > 15) {
            throw new IllegalArgumentException("A cellaméretnek 1 és 15 között kell lennie.");
        }

        if (simulationSpeed <= 0) {
            throw new IllegalArgumentException("A szimuláció sebességének pozitívnak kell lennie.");
        }

        if (!survivalRules.matches("[0-8]*") || !birthRules.matches("[0-8]*")) {
            throw new IllegalArgumentException("A túlélési és születési szabályoknak a B.../S... formátumot kell követniük.");
        }

        if (aliveColor.equals(deadColor)) {
            throw new IllegalArgumentException("Az élő és halott cellák színe nem lehet ugyanaz.");
        }
    }


}
