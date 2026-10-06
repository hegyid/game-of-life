/**
 * A GameSettingsPanel osztály biztosítja a szimuláció beállítására szolgáló grafikus felhasználói felületet.
 * Tartalmazza a születési és túlélési szabályok, rácsméretek, színek, szimuláció sebessége,
 * és a kezdőállapot beállításait.
 */

package gameoflife.ui;

import gameoflife.util.ColorUtil;
import gameoflife.util.RuleUtil;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;

public class GameSettingsPanel extends JPanel {
    private final JTextField survivalRulesField;
    private final JTextField birthRulesField;
    private final JComboBox<String> rulesComboBox;
    private final JTextField gridWidthField;
    private final JTextField gridHeightField;
    private final JTextField simulationSpeedField;
    private final JRadioButton randomInitialStateButton;
    private final JComboBox<String> deadColorComboBox;
    private final JComboBox<String> aliveColorComboBox;
    private final JCheckBox showGridCheckBox;
    private final JTextField cellSizeField;
    private final JSlider randomProbabilitySlider;
    private final JLabel probabilityLabel;

    public GameSettingsPanel() {
        setLayout(new GridLayout(11, 2, 10, 8));

        // születési szabályok
        add(new JLabel("Születési szabályok:"));
        birthRulesField = new JTextField("3");
        add(birthRulesField);

        // túlélési szabályok
        add(new JLabel("Túlélési szabályok:"));
        survivalRulesField = new JTextField("23");
        add(survivalRulesField);

        // szabály ötletek
        add(new JLabel("Szabály ötletek:"));
        rulesComboBox = new JComboBox<>(RuleUtil.getPredefinedRules().keySet().toArray(new String[0]));
        add(rulesComboBox);

        rulesComboBox.addActionListener(_ -> {
            String selectedRule = (String) rulesComboBox.getSelectedItem();
            if (selectedRule != null) {
                String[] rules = RuleUtil.getRule(selectedRule).split("/");
                birthRulesField.setText(rules[0].substring(1)); // "B" utáni rész
                survivalRulesField.setText(rules[1].substring(1)); // "S" utáni rész
            }
        });


        // rács méretének beállításai
        add(new JLabel("Rács mérete (max 1400x700):"));
        JPanel setGridPanel = new JPanel(new GridLayout(1, 4));
        setGridPanel.add(new JLabel("Szélesség:"));
        gridWidthField = new JTextField("100");
        setGridPanel.add(gridWidthField);

        setGridPanel.add(new JLabel("Magasság:"));
        gridHeightField = new JTextField("100");
        setGridPanel.add(gridHeightField);
        add(setGridPanel);

        // cellák mérete
        add(new JLabel("Cellák mérete (1-15):"));
        cellSizeField = new JTextField("7");
        add(cellSizeField);

        // szimuláció sebessége
        add(new JLabel("Szimuláció sebessége (lépés/sec):"));
        simulationSpeedField = new JTextField("10");
        add(simulationSpeedField);

        // kezdőállapot
        add(new JLabel("Kezdőállapot:"));
        JPanel seedPanel = new JPanel(new GridLayout(2, 1));
        ButtonGroup buttonGroup = new ButtonGroup();

        JRadioButton customInitialStateButton = new JRadioButton("Manuális");
        randomInitialStateButton = new JRadioButton("Random", true);

        seedPanel.add(customInitialStateButton);
        seedPanel.add(randomInitialStateButton);
        buttonGroup.add(customInitialStateButton);
        buttonGroup.add(randomInitialStateButton);
        add(seedPanel);

        // csúszka a random valószínűség beállításához
        probabilityLabel = new JLabel("Random valószínűség: 50%");
        randomProbabilitySlider = new JSlider(0, 100, 50);

        randomProbabilitySlider.setMajorTickSpacing(10);
        randomProbabilitySlider.setMinorTickSpacing(1);
        randomProbabilitySlider.setPaintTicks(true);
        randomProbabilitySlider.setPaintLabels(true);

        randomProbabilitySlider.addChangeListener((ChangeEvent _) -> {
            int value = randomProbabilitySlider.getValue();
            probabilityLabel.setText("Random valószínűség: " + value + "%");
        });

        add(probabilityLabel);
        add(randomProbabilitySlider);

        // csúszka megjelenítése
        toggleProbabilitySlider(randomInitialStateButton.isSelected());

        customInitialStateButton.addActionListener(_ -> toggleProbabilitySlider(false));
        randomInitialStateButton.addActionListener(_ -> toggleProbabilitySlider(true));

        // elo es halott sejtek szine
        String[] colors = {"Fehér", "Fekete", "Piros", "Zöld", "Kék", "Sárga", "Narancs", "Lila"};
        add(new JLabel("Halott cellák színe:"));
        deadColorComboBox = new JComboBox<>(colors);
        add(deadColorComboBox);

        add(new JLabel("Élő cellák színe:"));
        aliveColorComboBox = new JComboBox<>(colors);
        add(aliveColorComboBox);

        deadColorComboBox.setSelectedIndex(1);
        aliveColorComboBox.setSelectedIndex(4);

        // racs megjelenitese
        showGridCheckBox = new JCheckBox("Rácsvonalak megjelenítése", false);
        add(showGridCheckBox);
    }

    /**
     * A random valószínűség csúszka láthatóságát állítja.
     * @param isRandom true, ha a random kezdőállapot van kiválasztva.
     */
    private void toggleProbabilitySlider(boolean isRandom) {
        probabilityLabel.setVisible(isRandom);
        randomProbabilitySlider.setVisible(isRandom);
        revalidate();
        repaint();
    }

    // getter és setter metódusok

    public void setSurvivalRules(String SurvivalRules) {
        survivalRulesField.setText(SurvivalRules);
    }

    public String getSurvivalRules() {
        return survivalRulesField.getText();
    }

    public void setBirthRules(String BirthRules) {
        birthRulesField.setText(BirthRules);
    }

    public String getBirthRules() {
        return birthRulesField.getText();
    }

    public void setGridWidth(String GridWidth) {
        gridWidthField.setText(GridWidth);
    }

    public int getGridWidth() {
        return Integer.parseInt(gridWidthField.getText());
    }

    public void setGridHeight(String GridHeight) {
        gridHeightField.setText(GridHeight);
    }

    public int getGridHeight() {
        return Integer.parseInt(gridHeightField.getText());
    }

    public void setSimulationSpeed(String SimulationSpeed) {
        simulationSpeedField.setText(SimulationSpeed);
    }

    public int getSimulationSpeed() {
        return Integer.parseInt(simulationSpeedField.getText());
    }

    public void setCellSize(String CellSize) {
        cellSizeField.setText(CellSize);
    }

    public int getCellSize() {
        return Integer.parseInt(cellSizeField.getText());
    }

    public int getRandomProbability() {
        return randomProbabilitySlider.getValue(); // Return the slider value
    }

    public String getDeadColorName() {
        return (String) deadColorComboBox.getSelectedItem(); // Default to white
    }

    public Color getDeadColor() {
        return ColorUtil.getColorFromName(getDeadColorName());
    }

    public String getAliveColorName() {
        return (String) aliveColorComboBox.getSelectedItem();
    }

    public Color getAliveColor() {
        return ColorUtil.getColorFromName(getAliveColorName());
    }

    public boolean isShowGridLines() {
        return showGridCheckBox.isSelected();
    }

    public boolean isRandomInitialState() {
        return randomInitialStateButton.isSelected();
    }
}
