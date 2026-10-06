/**
 * A SimulationSave osztály a szimulációk állapotának mentéséért és visszatöltéséért felelős.
 * Ez az osztály tartalmazza a szimuláció összes fontos paraméterét, beleértve a rács állapotát,
 * szabályokat, színeket, sebességet és megjelenítési beállításokat.
 */

package gameoflife.save;

import gameoflife.grid.Grid;

import java.awt.*;
import java.io.Serializable;

public class SimulationSave implements Serializable {
    private final Grid grid; // A szimuláció rácsa
    private final int rows; // A rács sorainak száma
    private final int cols; // A rács oszlopainak száma
    private final int cellSize; // A cellák mérete
    private final String survivalRules; // Túlélési szabályok
    private final String birthRules; // Születési szabályok
    private final int simulationSpeed; // A szimuláció sebessége
    private final Color aliveColor; // Élő cellák színe
    private final Color deadColor; // Halott cellák színe
    private final boolean isShowGridLines; // Megjelenjenek-e a rácsvonalak

    /**
     * Konstruktor a SimulationSave objektum létrehozásához.
     *
     * @param grid             a rács objektum, amely a szimuláció állapotát tartalmazza
     * @param rows             a rács sorainak száma
     * @param cols             a rács oszlopainak száma
     * @param survivalRules    a túlélési szabályokat tartalmazó string
     * @param birthRules       a születési szabályokat tartalmazó string
     * @param simulationSpeed  a szimuláció sebessége
     * @param aliveColor       az élő cellák színe
     * @param deadColor        a halott cellák színe
     * @param cellSize         a cellák mérete
     * @param isShowGridLines  rácsvonalak megjelenítése
     */
    public SimulationSave(Grid grid, int rows, int cols, String survivalRules, String birthRules, int simulationSpeed, Color aliveColor, Color deadColor, int cellSize, boolean isShowGridLines) {
        this.grid = grid;
        this.rows = rows;
        this.cols = cols;
        this.survivalRules = survivalRules;
        this.birthRules = birthRules;
        this.simulationSpeed = simulationSpeed;
        this.aliveColor = aliveColor;
        this.deadColor = deadColor;
        this.cellSize = cellSize;
        this.isShowGridLines = isShowGridLines;
    }

    /**
     * Visszaadja a rács sorainak számát.
     *
     * @return A rács sorainak száma.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Visszaadja a rács oszlopainak számát.
     *
     * @return A rács oszlopainak száma.
     */
    public int getCols() {
        return cols;
    }

    /**
     * Visszaadja a túlélési szabályokat.
     *
     * @return A túlélési szabályokat tartalmazó szöveg.
     */
    public String getSurvivalRules() {
        return survivalRules;
    }

    /**
     * Visszaadja a születési szabályokat.
     *
     * @return A születési szabályokat tartalmazó szöveg.
     */
    public String getBirthRules() {
        return birthRules;
    }

    /**
     * Visszaadja a szimuláció sebességét.
     *
     * @return A szimuláció sebessége.
     */
    public int getSimulationSpeed() {
        return simulationSpeed;
    }

    /**
     * Visszaadja az élő cellák színét.
     *
     * @return Az élő cellák színét tartalmazó Color objektum.
     */
    public Color getAliveColor() {
        return aliveColor;
    }

    /**
     * Visszaadja a halott cellák színét.
     *
     * @return A halott cellák színét tartalmazó Color objektum.
     */
    public Color getDeadColor() {
        return deadColor;
    }

    /**
     * Visszaadja a cellák méretét.
     *
     * @return A cellák mérete.
     */
    public int getCellSize() {
        return cellSize;
    }

    /**
     * Visszaadja, hogy megjelenjenek-e a rácsvonalak.
     *
     * @return true, ha a rácsvonalak megjelennek, különben false.
     */
    public boolean isShowGridLines() {
        return isShowGridLines;
    }

    /**
     * Visszaadja a rács objektumot, amely tartalmazza a szimuláció aktuális állapotát.
     *
     * @return A rácsot reprezentáló Grid objektum.
     */
    public Grid getGrid() {
        return grid;
    }
}
