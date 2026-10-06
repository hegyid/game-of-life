/**
 * A SimulationFrame osztály egy JFrame, ami a szimuláció futtatására szolgál.
 */

package gameoflife.ui;

import gameoflife.simulation.Simulation;
import gameoflife.save.SimulationSave;
import gameoflife.save.SimulationSaver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SimulationFrame extends JFrame {
    private final JButton toggleSimulationButton; // A szimuláció indítására/megállítására szolgáló gomb
    private boolean simulationRunning = true; // A szimuláció futásának állapotát jelzi
    private boolean showGridLines; // A rácsvonalak megjelenítésének állapota

    /**
     * Konstruktor, amely inicializálja a SimulationFrame-et.
     *
     * @param gridPanel   A szimulációhoz használt GridPanel.
     * @param simulation  A Simulation objektum, amely a szimuláció működését irányítja.
     */
    public SimulationFrame(GridPanel gridPanel, Simulation simulation) {
        super("Game of Life - Simulation");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK);

        gridPanel.setBackground(Color.BLACK);
        add(gridPanel, BorderLayout.CENTER);

        setExtendedState(JFrame.MAXIMIZED_BOTH);


        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(Color.BLACK);
        toggleSimulationButton = new JButton("Stop");
        JButton showGridLinesButton = new JButton("Rácsvonalak: " + (gridPanel.isShowGridLines() ? "Be" : "Ki"));
        JButton saveButton = new JButton("Mentés");
        JButton closeButton = new JButton("Kilépés");

        toggleSimulationButton.addActionListener(_ -> toggleSimulation(simulation));

        showGridLinesButton.addActionListener(_ -> {
            showGridLines = !showGridLines;
            showGridLinesButton.setText(showGridLines ? "Rácsvonalak: Be" : "Rácsvonalak: Ki");
            gridPanel.setShowGridLines(showGridLines);
        });

        saveButton.addActionListener(_ -> {
            simulation.pause();
            SimulationSave save = new SimulationSave(
                    gridPanel.grid,
                    gridPanel.grid.rows,
                    gridPanel.grid.cols,
                    simulation.getSurvivalRules(),
                    simulation.getBirthRules(),
                    simulation.getSpeed(),
                    gridPanel.getAliveColor(),
                    gridPanel.getDeadColor(),
                    gridPanel.getCellSize(),
                    gridPanel.isShowGridLines()
            );
            SimulationSaver.saveSimulation(save);
            simulation.start();
        });
        controlPanel.add(saveButton);


        closeButton.addActionListener(_ -> dispose());


        controlPanel.add(toggleSimulationButton);
        controlPanel.add(showGridLinesButton);
        controlPanel.add(saveButton);
        controlPanel.add(closeButton);
        add(controlPanel, BorderLayout.SOUTH);

        gridPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                gridPanel.toggleCell(e.getX(), e.getY(), false, false);
            }
        });

        gridPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                gridPanel.toggleCell(e.getX(), e.getY(), false, false);
            }
        });

        setVisible(true);
    }

    /**
     * A szimuláció futásának indítása vagy megállítása.
     *
     * @param simulation A Simulation objektum, amely a szimuláció működését irányítja.
     */
    private void toggleSimulation(Simulation simulation) {
        if (simulationRunning) {
            simulation.pause();
            toggleSimulationButton.setText("Start");
        } else {
            simulation.start();
            toggleSimulationButton.setText("Stop");
        }
        simulationRunning = !simulationRunning;
    }
}
