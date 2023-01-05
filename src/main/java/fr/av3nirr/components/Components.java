package fr.av3nirr.components;

import fr.av3nirr.UltimateSkyblock;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class Components {
    UltimateSkyblock main = UltimateSkyblock.getInstance();
    private static final Properties config;
    public File propertiesFile;
    private static final Properties defaults;
    public void load() throws IOException {
        if (this.propertiesFile == null) {
            this.propertiesFile = new File(main.getDataFolder(), "strings.properties");
        }
        if (!this.propertiesFile.exists()) {
            main.saveResource("strings.properties", false);
        }
        this.config.load(new InputStreamReader(new FileInputStream(this.propertiesFile), "UTF-8"));
        this.defaults.load(new InputStreamReader(main.getResource("strings.properties"), "UTF-8"));
    }
    public String getString(final String key) {
        String value = this.config.getProperty(key);
        if (value != null) {
            return value.replace('&', '§');
        }

        // si value != de null ce code ne sera jamais exécuté car la méthode aura déjà return a la ligne 360 et n'exécutera pas la suite
        value = this.defaults.getProperty(key);
        if (value != null) {
            return value.replace('&', '§');
        }
        return "§cAucun texte trouvé: '" + key + "'";
    }
    static {
        config = new Properties();
        defaults = new Properties();
    }
}
