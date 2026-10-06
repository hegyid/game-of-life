import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import gameoflife.controller.GameController;
import gameoflife.ui.GameSettingsPanel;

class GameControllerTest {

    @Test
    void testValidateInputValid() {
        GameSettingsPanel gameSettingsPanel = new GameSettingsPanel();
        gameSettingsPanel.setGridWidth("10");
        gameSettingsPanel.setGridHeight("10");
        gameSettingsPanel.setSimulationSpeed("5");
        gameSettingsPanel.setCellSize("5");
        gameSettingsPanel.setSurvivalRules("23");
        gameSettingsPanel.setBirthRules("3");

        GameController controller = new GameController(gameSettingsPanel);
        assertDoesNotThrow(controller::validateInput);
    }

    @Test
    void testValidateInputInvalidGridSize() {
        GameSettingsPanel gameSettingsPanel = new GameSettingsPanel();
        gameSettingsPanel.setGridWidth("0");
        gameSettingsPanel.setGridHeight("10");
        gameSettingsPanel.setSimulationSpeed("5");
        gameSettingsPanel.setCellSize("5");
        gameSettingsPanel.setSurvivalRules("23");
        gameSettingsPanel.setBirthRules("3");

        GameController controller = new GameController(gameSettingsPanel);
        Exception exception = assertThrows(IllegalArgumentException.class, controller::validateInput);
        assertEquals("A rács szélessége és magassága pozitív szám kell legyen.", exception.getMessage());
    }

    @Test
    void testValidateInputInvalidRules() {
        GameSettingsPanel gameSettingsPanel = new GameSettingsPanel();
        gameSettingsPanel.setGridWidth("10");
        gameSettingsPanel.setGridHeight("10");
        gameSettingsPanel.setSimulationSpeed("5");
        gameSettingsPanel.setCellSize("5");
        gameSettingsPanel.setSurvivalRules("9");
        gameSettingsPanel.setBirthRules("9");

        GameController controller = new GameController(gameSettingsPanel);
        Exception exception = assertThrows(IllegalArgumentException.class, controller::validateInput);
        assertEquals("A túlélési és születési szabályoknak a B.../S... formátumot kell követniük.", exception.getMessage());
    }
}

