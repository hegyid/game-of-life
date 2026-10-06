/**
 *A Grid osztály a szimuláció rácsának állapotát és működését kezeli.
 * Tartalmazza a cellák aktuális állapotát, és a születési valamint
 * túlélési szabályok alapján kezeli azok frissítését.
 * Az osztály támogatja a cellák állapotának megváltoztatását, a rács tisztítását,
 * és a cellák véletlenszerű inicializálását.
 */

package gameoflife.grid;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Random;

public class Grid implements Serializable {
    public int cols; // oszlopok száma
    public int rows; // sorok száma
    public boolean[][] grid; // cellák állapotát tároló kétdimenziós tömb, false = halott, true = élő cella

    /**
     * Létrehoz egy új Grid objektumot
     *
     * @param cols          a rács oszlopainak a száma
     * @param rows          a rács sorainak a száma
     * @param random        ha igaz az értéke, a rács véletlenszerűen inicializálódik
     * @param probability   az élő cellák valószínűsége
     */
    public Grid(int cols, int rows, boolean random, double probability) {
        this.cols = cols;
        this.rows = rows;
        this.grid = new boolean[rows][cols];

        if (random) {
            randomize(probability);
        } else {
            clearGrid();
        }
    }

    /**
     * Frissíti a rácsot a túlélési és születési szabályok alapján.
     *
     * @param survivalRules túlélési szabályokat leíró sztring
     * @param birthRules    születési szabályokat leíró sztring
     */
    public void update(String survivalRules, String birthRules) {
        HashSet<Integer> survivalSet = parseRules(survivalRules);
        HashSet<Integer> birthSet = parseRules(birthRules);

        boolean[][] newGrid = new boolean[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int aliveNeighbors = countAliveNeighbors(r, c);
                if (grid[r][c]) {
                    newGrid[r][c] = survivalSet.contains(aliveNeighbors);
                } else {
                    newGrid[r][c] = birthSet.contains(aliveNeighbors);
                }
            }
        }

        grid = newGrid;
    }

    /**
     * Visszaadja, hogy egy cellának hány élő szomszédja vaná
     *
     * @param row       a cella sorszáma
     * @param col       a cella oszlopszáma
     * @return count    az élő szomszédok száma (0-8)
     */
    private int countAliveNeighbors(int row, int col) {
        int count = 0;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue; // saját magát nem számolja
                if (getCellState(row + dr, col + dc)) count++;
            }
        }
        return count;
    }

    /**
     * A megadott szabályláncot számértékekké alakítja.
     *
     * @param rules     a szabálylánc, amely számjegyeket tartalmaz
     * @return ruleSet  egy HashSet, amely a szabályhoz tartozó számokat tartalmazza
     */
    private HashSet<Integer> parseRules(String rules) {
        HashSet<Integer> ruleSet = new HashSet<>();
        for (char c : rules.toCharArray()) {
            if (Character.isDigit(c)) {
                ruleSet.add(Character.getNumericValue(c));
            }
        }
        return ruleSet;
    }

    /**
     * Visszaadja egy cella állapotát, azaz hogy élő vagy halott-e vagyis true vagy false az értéke.
     *
     * @param row       a cella sorszáma
     * @param col       a cella oszlopszáma
     * @return true ha a cella élő, false ha halott vagy a koordináták érvénytelenek
     */
    public boolean getCellState(int row, int col) {
        if (isInBounds(row, col)) {
            return grid[row][col];
        }
        return false;
    }

    /**
     * Megváltoztatja a cella állapotát a megadott koordinátákon.
     * Ha a cella élő volt, halottá válik, ha halott volt, élővé válik.
     *
     * @param row   a cella sorszáma
     * @param col   a cella oszlopszáma
     */
    public void toggleCellState(int row, int col) {
        grid[row][col] = !grid[row][col];
    }


    /**
     * Csak az élő cellák állapotát változtatja meg.
     *
     * @param row   a cella sorszáma
     * @param col   a cella oszlopszáma
     */
    public void toggleAliveCellState(int row, int col) {
        if (grid[row][col]) {
            toggleCellState(row, col);
        }
    }

    /**
     * Csak a halott cellák állapotát változtatja meg.
     *
     * @param row   a cella sorszáma
     * @param col   a cella oszlopszáma
     */
    public void toggleDeadCellState(int row, int col) {
        if (!grid[row][col]) {
            toggleCellState(row, col);
        }
    }

    /**
     * A rács összes celláját halottra állítja.
     */
    public void clearGrid() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = false;
            }
        }
    }

    /**
     * Ellenőrzi, hogy egy adott cella koordinátái a rács érvényes tartományán belül vannak-e.
     *
     * @param row   a cella sorszáma
     * @param col   a cella oszlopszáma
     * @return true ha határokon belül van, különben false
     */
    public boolean isInBounds(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    /**
     * Véletlenszerűen inicializálja a rács celláit a megadott valószínűséggel.
     *
     * @param aliveProbability  annak a valószínűsége, hogy egy cella élő lesz
     */
    public void randomize(double aliveProbability) {
        Random random = new Random();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = random.nextDouble() < aliveProbability;
            }
        }
    }
}
