/**
 * A MainMenuPanel a főmenü panelt reprezentálja.
 * Tartalmazza az Új játék, Játék betöltése és Kilépés gombokat.
 */

package gameoflife.ui;

import gameoflife.save.SimulationSave;
import gameoflife.save.SimulationLoader;
import gameoflife.controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainMenuPanel extends JPanel {

    /**
     * Létrehozza a főmenü panelt.
     *
     * @param cardLayout  A CardLayout kezelő, amely a panelek közötti váltást kezeli.
     * @param mainPanel   A főpanel, amely tartalmazza az összes többi panelt.
     * @param gameController A GameController objektum, amely a játék logikáját kezeli.
     */
    public MainMenuPanel(CardLayout cardLayout, JPanel mainPanel, GameController gameController) {

        // Panel elrendezése: 3 sor, 1 oszlop, 10-es távolság a gombok között
        setLayout(new GridLayout(3, 1, 10, 10));

        // Gombok létrehozása
        JButton newGameButton = new JButton("Új szimuláció");
        JButton loadGameButton = new JButton("Szimuláció betöltése");
        JButton exitGameButton = new JButton("Kilépés");

        // Gombok hozzáadása a panelhez
        add(newGameButton);
        add(loadGameButton);
        add(exitGameButton);

        // Új játék gomb eseménykezelője
        newGameButton.addActionListener(_ -> cardLayout.show(mainPanel, "NewGameMenu"));

        // Játék betöltése gomb eseménykezelője
        loadGameButton.addActionListener(_ -> {
            // Fájl kiválasztása
            File selectedFile = SimulationLoader.chooseSimulationFile();
            if (selectedFile != null) {
                // Szimuláció betöltése
                SimulationSave save = SimulationLoader.loadSimulation(selectedFile);
                if (save != null) {
                    gameController.loadSavedSimulation(save);
                } else {
                    JOptionPane.showMessageDialog(this, "Hiba történt a betöltés során.", "Betöltés hiba", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Kilépés gomb eseménykezelője
        exitGameButton.addActionListener(_ -> System.exit(0));
    }
}
