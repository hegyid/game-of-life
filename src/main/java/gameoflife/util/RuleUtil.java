/**
 * A RuleUtil osztály előre definiált szabályokat tartalmaz.
 * Az osztály lehetővé teszi a szabályok lekérését.
 */

package gameoflife.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class RuleUtil {
    private static final Map<String, String> predefinedRules = new LinkedHashMap<>(); // LinkedHashMap a sorrend megőrzéséhez

    static {
        predefinedRules.put("Conway's Life (B3/S23)", "B3/S23");
        predefinedRules.put("Day and Night (B3678/S34678)", "B3678/S34678");
        predefinedRules.put("HighLife (B36/S23)", "B36/S23");
        predefinedRules.put("Seeds (B2/S)", "B2/S");
        predefinedRules.put("Life without Death (B3/S012345678)", "B3/S012345678");
        predefinedRules.put("Gnarl (B1/S1)", "B1/S1");
        predefinedRules.put("Replicator (B1357/S1357)", "B1357/S1357");
        predefinedRules.put("Anneal (B4678/S35678)", "B4678/S35678");
        predefinedRules.put("Serviettes (B234/S)", "B234/S");
        predefinedRules.put("Maze (B3/S12345)", "B3/S12345");
        predefinedRules.put("Caves (B678/S345678)", "B678/S345678");
    }

    /**
     * Visszaadja az előre definiált szabályok térképét.
     *
     * @return A szabályok neveit és azok kulcsait tartalmazó Map.
     */
    public static Map<String, String> getPredefinedRules() {
        return predefinedRules;
    }

    /**
     * Egy adott szabály kódját adja vissza a szabály nevének megadása alapján.
     *
     * @param ruleName A szabály neve.
     * @return A szabály kódja.
     */
    public static String getRule(String ruleName) {
        return predefinedRules.getOrDefault(ruleName, "");
    }
}
