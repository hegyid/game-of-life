/**
 * A GridFrame osztály a manuális rácsbeállítást megjelenítő ablakot kezeli.
 * Lehetővé teszi a felhasználó számára, hogy beállítsa a rács kezdőállapotát.
 */

package gameoflife.ui;

import gameoflife.controller.GameController;
import gameoflife.grid.Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GridFrame extends JFrame {
    private boolean erase = false; // cellák élőre vagy halottra állítása
    private boolean symmetry = false; // szimmetria funkció
    private boolean showGridLines; // rácsvonalak megjelenítése

    /**
     * Létrehoz egy új GridFrame ablakot a manuális rácsbeállításokhoz.
     *
     * @param grid             a szerkesztendő rács objektum
     * @param gridPanel        a rácsot megjelenítő grafikus panel
     * @param controller       a játék logikáját vezérlő kontroller
     * @param simulationSpeed  a szimuláció sebessége
     * @param survivalRules    a túlélési szabályok
     * @param birthRules       a születési szabályok
     */
    public GridFrame(Grid grid, GridPanel gridPanel, GameController controller, int simulationSpeed, String survivalRules, String birthRules) {
        setTitle("Manual Grid Setup");

        this.showGridLines = gridPanel.isShowGridLines();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK);
        gridPanel.setBackground(Color.BLACK);
        add(gridPanel, BorderLayout.CENTER);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(Color.BLACK);
        JButton startButton = new JButton("Start");
        JButton toggleEraseModeButton = new JButton("Élőre állítás");
        JButton symmetryButton = new JButton("Szimmetria: Ki");
        JButton showGridLinesButton = new JButton("Rácsvonalak: " + (gridPanel.isShowGridLines() ? "Be" : "Ki"));
        JButton clearButton = new JButton("Tisztogatás");
        JButton cancelButton = new JButton("Vissza");

        // Start gomb: elindítja a szimulációt
        startButton.addActionListener(_ -> {
            controller.startManualSimulation(grid, gridPanel, simulationSpeed, survivalRules, birthRules);
            gridPanel.setShowSymmetryAxes(false);
            symmetry = false;
            erase = false;
            dispose();
        });

        // Élő/halott cellák állapotának váltása
        toggleEraseModeButton.addActionListener(_ -> {
            erase = !erase;
            toggleEraseModeButton.setText(erase ? "Halottra állítás" : "Élőre állítás");
        });

        // Szimmetria tengelyek megjelenítésének kezelése
        symmetryButton.addActionListener(_ -> {
            symmetry = !symmetry;
            symmetryButton.setText(symmetry ? "Szimmetria: Be" : "Szimmetria: Ki");
            gridPanel.setShowSymmetryAxes(symmetry);
        });

        // Rácsvonalak megjelenítésének váltása
        showGridLinesButton.addActionListener(_ -> {
            showGridLines = !showGridLines;
            showGridLinesButton.setText(showGridLines ? "Rácsvonalak: Be" : "Rácsvonalak: Ki");
            gridPanel.setShowGridLines(showGridLines);
        });

        // Rács törlése, vagyis az összes cella halottra állítása
        clearButton.addActionListener(_ -> gridPanel.clearGrid());

        // Visszalépés gomb: bezárja az ablakot
        cancelButton.addActionListener(_ -> dispose());

        // Gombok hozzáadása a vezérlőpanelhez
        controlPanel.add(startButton);
        controlPanel.add(toggleEraseModeButton);
        controlPanel.add(symmetryButton);
        controlPanel.add(showGridLinesButton);
        controlPanel.add(clearButton);
        controlPanel.add(cancelButton);

        // Vezérlőpanel hozzáadása az ablakhoz
        add(controlPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(null);


        // egérműveletek a cellák állapotának módosításához
        gridPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                gridPanel.toggleCell(e.getX(), e.getY(), erase, symmetry);
            }
        });

        gridPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                gridPanel.toggleCell(e.getX(), e.getY(), erase, symmetry);
            }
        });
    }
}
