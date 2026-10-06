/**
 * A NewGameMenuPanel felelős az "Új játék" menü felületének megjelenítéséért.
 * Tartalmazza a játékkonfigurációs panelt valamint innen lehet a szimulációt indítani,
 * vagy a főmenübe visszalépni.
 */

package gameoflife.ui;

import gameoflife.controller.GameController;

import javax.swing.*;
import java.awt.*;

public class NewGameMenuPanel extends JPanel {

    /**
     * Létrehoz egy új NewGameMenuPanel-t.
     *
     * @param cardLayout  A CardLayout kezelő, amely a panelek közötti váltást kezeli.
     * @param mainPanel   A főpanel, amely tartalmazza az összes többi panelt.
     * @param gameController A GameController objektum, amely a játék logikáját kezeli.
     */
    public NewGameMenuPanel(CardLayout cardLayout, JPanel mainPanel, GameController gameController) {
        // A fő panel elrendezése
        setLayout(new BorderLayout(10, 10));

        // GameSettingsPanel hozzáadása a fő panelhez
        add(gameController.getGameSettingsPanel(), BorderLayout.CENTER);

        // Alsó gombpanel létrehozása
        JPanel bottomPanel = new JPanel(new GridLayout(2, 1, 10, 5));
        JButton startButton = new JButton("Start");
        JButton backButton = new JButton("Vissza");

        // Gombok eseménykezelői
        startButton.addActionListener(_ -> gameController.startSimulation()); // Játék indítása
        backButton.addActionListener(_ -> cardLayout.show(mainPanel, "MainMenu")); // Vissza a főmenübe

        // Gombok hozzáadása az alsó panelhez
        bottomPanel.add(startButton);
        bottomPanel.add(backButton);

        // Alsó panel hozzáadása az ablak aljához
        add(bottomPanel, BorderLayout.SOUTH);
    }
}

