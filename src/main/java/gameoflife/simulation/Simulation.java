/**
 * A Simulation osztály a szimuláció futtatásáért felel.
 * A szimuláció egy rácson alapul, amelyet adott szabályok szerint frissít.
 * A szimuláció futtatását egy javax.swing.Timer végzi, amely rendszeresen meghívja
 * a rács frissítéséért felelős metódust.
 */

package gameoflife.simulation;

import gameoflife.grid.Grid;
import gameoflife.ui.GridPanel;

import javax.swing.*;

public class Simulation {
    private final Timer timer; // A szimuláció időzítője, amely meghatározott időközönként frissíti a rácsot
    private final int speed; // A szimuláció sebessége, iterációk másodpercenként
    private final String birthRules; // A születési szabályok
    private final String survivalRules; // A túlélési szabályok

    /**
     * Konstruktor, amely inicializálja a Simulation objektumot.
     *
     * @param grid          a rács, amelyen a szimuláció fut
     * @param gridPanel     a GridPanel, amely megjeleníti a rácsot
     * @param speed         a szimuláció sebessége (iterációk másodpercenként)
     * @param survivalRules a túlélési szabályok
     * @param birthRules    a születési szabályok
     */
    public Simulation(Grid grid, GridPanel gridPanel, int speed, String survivalRules, String birthRules) {
        this.speed = speed;
        this.birthRules = birthRules;
        this.survivalRules = survivalRules;


        this.timer = new Timer(1000 / speed, _ -> {
            grid.update(survivalRules, birthRules);
            gridPanel.refresh();
        });
    }

    /**
     * A szimuláció elindítása.
     * Az időzítő elkezdi rendszeresen hívni a rács frissítését.
     */
    public void start() {
        timer.start();
    }

    /**
     * A szimuláció szüneteltetése.
     * Az időzítő megállítja a rács frissítését.
     */
    public void pause() {
        timer.stop();
    }

    /**
     * Visszaadja az időzítőt (Timer), amely a szimuláció működéséért felel.
     *
     * @return A Timer objektum.
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * Visszaadja a szimuláció sebességét (iterációk másodpercenként).
     *
     * @return A szimuláció sebessége.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Visszaadja a születési szabályokat.
     *
     * @return A születési szabályok sztringként.
     */
    public String getBirthRules() {
        return birthRules;
    }

    /**
     * Visszaadja a túlélési szabályokat.
     *
     * @return A túlélési szabályok sztringként.
     */
    public String getSurvivalRules() {
        return survivalRules;
    }
}
