/**
 * A ColorUtil osztály a színnevek és a Java Color objektumok közötti átváltást biztosítja.
 */

package gameoflife.util;

import java.awt.Color;

public class ColorUtil {

    /**
     * Visszaadja a megfelelő Java Color objektumot egy adott színnév alapján.
     *
     * @param colorName   A szín neve
     * @return            A megfelelő Color objektum, vagy Color.WHITE alapértelmezésként, ha a név ismeretlen.
     */
    public static Color getColorFromName(String colorName) {
        switch (colorName.trim().toLowerCase()) {
            case "fehér": return Color.WHITE;
            case "fekete": return Color.BLACK;
            case "piros": return Color.RED;
            case "zöld": return Color.GREEN;
            case "kék": return Color.BLUE;
            case "sárga": return Color.YELLOW;
            case "narancs": return Color.ORANGE;
            case "lila": return Color.MAGENTA;
            default: return Color.WHITE;
        }
    }
}