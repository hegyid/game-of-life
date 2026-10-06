/**
 * A GridPanel osztály a rács grafikus megjelenítését kezeli.
 * Felelős a cellák állapotának kirajzolásáért, a rácsvonalak, valamint a szimmetriatengelyek megjelenítéséért.
 */

package gameoflife.ui;

import gameoflife.grid.Grid;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {
    public Grid grid; // A rács adatait tároló objektum
    private final Color aliveColor; // Az élő cellák színe
    private final Color deadColor; // A halott cellák színe
    private boolean showGridLines; // Meghatározza, hogy a rácsvonalak láthatók-e
    private final int cellSize; // Egy cella mérete
    boolean showSymmetryAxes; // Meghatározza, hogy a szimmetriatengelyek láthatók-e

    /**
     * Létrehoz egy új GridPanel objektumot.
     *
     * @param grid             a rács adatait tároló objektum
     * @param aliveColor       az élő cellák színe
     * @param deadColor        a halott cellák színe
     * @param showGridLines    a rácsvonalak megjelenítésének állapota
     * @param cellSize         a cellák mérete pixelben
     * @param showSymmetryAxes a szimmetriatengelyek megjelenítésének állapota
     */
    public GridPanel(Grid grid, Color aliveColor, Color deadColor, boolean showGridLines, int cellSize, boolean showSymmetryAxes) {
        this.grid = grid;
        this.aliveColor = aliveColor;
        this.deadColor = deadColor;
        this.showGridLines = showGridLines;
        this.cellSize = cellSize;
        this.showSymmetryAxes = showSymmetryAxes;
    }

    /**
     * Kirajzolja a rácsot, beleértve a cellák állapotát, a rácsvonalakat és a szimmetriatengelyeket.
     *
     * @param g a grafikus kontextus, amelyre rajzolni kell
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int rows = grid.rows;
        int cols = grid.cols;


        int xOffset = (getWidth() - (cellSize * cols)) / 2;
        int yOffset = (getHeight() - (cellSize * rows)) / 2;


        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                boolean isAlive = grid.getCellState(r, c);
                g2d.setColor(isAlive ? aliveColor : deadColor);
                g2d.fillRect(xOffset + c * cellSize, yOffset + r * cellSize, cellSize, cellSize);
            }
        }

        // Rácsvonalak kirajzolása
        if (showGridLines) {
            g2d.setColor(Color.GRAY);
            for (int r = 0; r <= rows; r++) {
                g2d.drawLine(xOffset, yOffset + r * cellSize, xOffset + cols * cellSize, yOffset + r * cellSize);
            }
            for (int c = 0; c <= cols; c++) {
                g2d.drawLine(xOffset + c * cellSize, yOffset, xOffset + c * cellSize, yOffset + rows * cellSize);
            }
        }

        // Szimmetriatengelyek kirajzolása
        if (showSymmetryAxes) {
            g2d.setColor(Color.RED);
            int centerX = xOffset + (cols * cellSize) / 2;
            int centerY = yOffset + (rows * cellSize) / 2;

            g2d.drawLine(xOffset, centerY, xOffset + cols * cellSize, centerY);

            g2d.drawLine(centerX, yOffset, centerX, yOffset + rows * cellSize);
        }

        // Rács keretének kirajzolása
        g2d.setColor(Color.GRAY);
        g2d.drawRect(xOffset, yOffset, cols * cellSize, rows * cellSize);
    }

    /**
     * A megadott koordináták alapján megváltoztatja a cellák állapotát, figyelembe véve a szimmetria és törlés beállításokat.
     *
     * @param mouseX    az egér x-koordinátája
     * @param mouseY    az egér y-koordinátája
     * @param erase     meghatározza, hogy halottra vagy élőre kell-e a cellákat változtatni
     * @param symmetry  meghatározza, hogy szimmetria alapján is változtasson-e
     */
    public void toggleCell(int mouseX, int mouseY, boolean erase, boolean symmetry) {
        int rows = grid.rows;
        int cols = grid.cols;

        int xOffset = (getWidth() - (cellSize * cols)) / 2;
        int yOffset = (getHeight() - (cellSize * rows)) / 2;

        int col = (mouseX - xOffset) / cellSize;
        int row = (mouseY - yOffset) / cellSize;

        if (grid.isInBounds(row, col)) {
            toggleCellState(row, col, erase);

            if (symmetry) {
                int centerX = cols / 2;
                int centerY = rows / 2;

                int symmetricRow = centerY + (centerY - row) - (rows % 2 == 0 ? 1 : 0);
                int symmetricCol = centerX + (centerX - col) - (cols % 2 == 0 ? 1 : 0);

                if (grid.isInBounds(symmetricRow, col)) {
                    toggleCellState(symmetricRow, col, erase);
                }

                if (grid.isInBounds(row, symmetricCol)) {
                    toggleCellState(row, symmetricCol, erase);
                }

                if (grid.isInBounds(symmetricRow, symmetricCol)) {
                    toggleCellState(symmetricRow, symmetricCol, erase);
                }
            }
            repaint();
        }
    }


    /**
     * Egy cella állapotát változtatja meg a megadott sorban és oszlopban.
     *
     * @param row   a cella sora
     * @param col   a cella oszlopa
     * @param erase meghatározza, hogy élőre vagy halottra állítás történik-e
     */
    private void toggleCellState(int row, int col, boolean erase) {
        if (erase) {
            grid.toggleAliveCellState(row, col);
        } else {
            grid.toggleDeadCellState(row, col);
        }
    }

    /**
     * Újrarajzolja a rácsot.
     */
    public void refresh() {
        repaint();
    }

    /**
     * Törli a rácsot, minden cellát halottra állít.
     */
    public void clearGrid(){
        grid.clearGrid();
        repaint();
    }

    /**
     * Ellenőrzi, hogy a rácsvonalak láthatók-e.
     *
     * @return true, ha a rácsvonalak láthatók, különben false
     */
    public boolean isShowGridLines(){
        return showGridLines;
    }

    /**
     * Beállítja a rácsvonalak megjelenítésének állapotát.
     *
     * @param showGridLines true, ha a rácsvonalak láthatók, különben false
     */
    public void setShowGridLines(boolean showGridLines) {
        this.showGridLines = showGridLines;
        repaint();
    }

    /**
     * Beállítja, hogy a szimmetriatengelyek láthatók legyenek.
     *
     * @param showSymmetryAxes true, ha a szimmetriatengelyek láthatók, különben false
     */
    public void setShowSymmetryAxes(boolean showSymmetryAxes) {
        this.showSymmetryAxes = showSymmetryAxes;
        repaint();
    }

    /**
     * Visszaadja az élő cellák színét.
     *
     * @return az élő cellák színe
     */
    public Color getAliveColor() {
        return aliveColor;
    }

    /**
     * Visszaadja a halott cellák színét.
     *
     * @return a halott cellák színe
     */
    public Color getDeadColor() {
        return deadColor;
    }

    /**
     * Visszaadja a cellák méretét pixelben.
     *
     * @return a cellák mérete
     */
    public int getCellSize() {
        return cellSize;
    }

}
