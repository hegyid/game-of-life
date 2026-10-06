/**
 * A SimulationSaver osztály felelős a szimuláció állapotának fájlba mentéséért.
 * A felhasználótól kéri a fájlnév megadását, és létrehozza a mentési fájlt a projekt
 * "saves" könyvtárában.
 */

package gameoflife.save;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SimulationSaver {

    /**
     * A szimuláció aktuális állapotát fájlba menti.
     *
     * @param save A szimuláció állapotát reprezentáló SimulationSave objektum.
     */
    public static void saveSimulation(SimulationSave save) {
        String fileName = JOptionPane.showInputDialog(null, "Adja meg a fájl nevét:", "Mentés", JOptionPane.PLAIN_MESSAGE);

        if (fileName == null || fileName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mentés megszakítva: nem adott meg fájlnevet.");
            return;
        }

        if (!fileName.endsWith(".txt")) {
            fileName += ".txt";
        }

        File saveDirectory = new File("src/main/resources/saves");
        if (!saveDirectory.exists()) {
            saveDirectory.mkdir();
        }

        File saveFile = new File(saveDirectory, fileName);

        try (FileOutputStream fileOut = new FileOutputStream(saveFile);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

            out.writeObject(save);
            JOptionPane.showMessageDialog(null, "Szimuláció sikeresen mentve");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hiba történt a mentés során: " + e.getMessage());
        }
    }
}
