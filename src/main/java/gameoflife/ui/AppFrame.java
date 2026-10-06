/**
 * Az AppFrame osztály az "Game of Life" alkalmazás fő kerete.
 * Ez az osztály kezeli a különböző képernyők (panelek) közötti navigációt,
 * például a főmenüt és az új játék beállításait.
 * Az alkalmazás a CardLayout elrendezést használja a különböző képernyők közötti váltáshoz.
 */

package gameoflife.ui;

import gameoflife.controller.GameController;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {

    /**
     * Az AppFrame konstruktora inicializálja a fő keretet, beállítja a különböző
     * képernyőket, és meghatározza azok elrendezését.
     * Létrehozza a következő paneleket:
     * - Főmenü (MainMenuPanel)
     * - Új játék menü (NewGameMenuPanel)
     * A főmenü jelenik meg először az alkalmazás elindítása után.
     */
    public AppFrame() {
        setTitle("Game of Life");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        GameSettingsPanel gameSettingsPanel = new GameSettingsPanel();
        GameController controller = new GameController(gameSettingsPanel);

        mainPanel.add(new MainMenuPanel(cardLayout, mainPanel, controller), "MainMenu");
        mainPanel.add(new NewGameMenuPanel(cardLayout, mainPanel, controller), "NewGameMenu");

        cardLayout.show(mainPanel, "MainMenu");

        add(mainPanel);
        setLocationRelativeTo(null);
    }

    /**
     * Az alkalmazás belépési pontja.
     * Létrehozza az AppFrame példányt, és láthatóvá teszi az alkalmazás fő keretét.
     */
    public static void main(String[] args) {
        AppFrame frame = new AppFrame();
        frame.setVisible(true);
    }
}
