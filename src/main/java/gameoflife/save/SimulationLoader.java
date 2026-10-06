/**
 * A SimulationLoader osztály felelős a szimulációs fájlok kiválasztásáért és betöltéséért.
 */

package gameoflife.save;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class SimulationLoader {

    /**
     * Megjeleníti a fájlválasztó ablakot, hogy a felhasználó kiválaszthasson egy szimulációs fájlt.
     * Az alapértelmezett könyvtár a "saves" mappa.
     *
     * @return A kiválasztott fájl objektuma, vagy null, ha a felhasználó nem választott fájlt.
     */
    public static File chooseSimulationFile() {
        JFileChooser fileChooser = new JFileChooser(new File("src/main/resources/saves")); // Alapértelmezett könyvtár
        fileChooser.setDialogTitle("Szimuláció betöltése fájlból");

        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }

        return null;
    }

    /**
     * Betölt egy szimulációs fájlt.
     * A fájlt deszerializálja, és visszaadja a szimuláció adatait egy SimulationSave objektumban.
     *
     * @param file A betöltendő szimulációs fájl File objektuma.
     * @return A betöltött szimuláció adatai SimulationSave objektumként, vagy null, ha hiba történt.
     * @throws IllegalArgumentException Ha a fájl null.
     */
    public static SimulationSave loadSimulation(File file) {
        if (file == null) {
            JOptionPane.showMessageDialog(null, "Nem lett kiválasztva fájl.");
            return null;
        }

        try (FileInputStream fileIn = new FileInputStream(file);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (SimulationSave) in.readObject();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hiba történt a betöltés során: " + e.getMessage());
            return null;
        }
    }


}
