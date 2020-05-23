package de.rewex.bungeebase.manager;

import de.rewex.bungeebase.Bungeebase;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class ConfigManager {

    public static File file = new File("plugins/bungeebase/config.yml");
    public static String path = "bungeebase.";

    public static void loadConfiguration() {
        if(!Bungeebase.getInstance().getDataFolder().exists()) {
            Bungeebase.getInstance().getDataFolder().mkdir();
        }
        if(!file.exists()) {
            try {
                file.createNewFile();

                set("wartung", Boolean.valueOf(true));
                set("grund", "Wartungsmodus");
                set("fortschritt", Integer.valueOf(0));

                set("status", "&8&l+ &7Statusmeldung");

            } catch(IOException ex) {
                Logger.getLogger("Loading Error: ConfigManager.java");
            }
        }
        reloadConfiguration(false);

    }

    @SuppressWarnings("unused")
    public static void reloadConfiguration(boolean done) {
        try {
            if(!done) {
                Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
                return;
            }
            loadConfiguration();
        } catch (IOException ex) {
            Logger.getLogger("Reload Error: ConfigManager.java");
        }
    }


    public static void set(String newPath, Object value) {
        try {
            Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
            config.set(path + newPath, value);
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, file);
        } catch (IOException ex) {
            Logger.getLogger("setMethod: ConfigManager.java");
        }
    }

    public static boolean getBoolean(String newPath) throws IOException {
        Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        return config.getBoolean(path + newPath);
    }

    public static String getString(String newPath) throws IOException {
        Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        return config.getString(path + newPath);
    }

    public static int getInt(String newPath) throws IOException {
        Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        return config.getInt(path + newPath);
    }

    public static List<String> getStringList(String newPath) throws IOException {
        Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        return config.getStringList(path + newPath);
    }

    ///////////////////////////////////////////////

    public static boolean getWartung() {
        try {
            return getBoolean("wartung");
        } catch (IOException ex) {
            Logger.getLogger("getWartung: ConfigManager.java");
        }
        return true;
    }

    public static String getWartungGrund() {
        try {
            return getString("grund");
        } catch (IOException ex) {
            Logger.getLogger("getWartung: ConfigManager.java");
        }
        return "Wartungsmodus";
    }

    public static int getWartungFortschritt() {
        try {
            return getInt("fortschritt");
        } catch (IOException ex) {
            Logger.getLogger("getWartungFortschritt: ConfigManager.java");
        }
        return 0;
    }

    public static void setWartung(boolean status) {
        set("wartung", status);
    }

    public static String getStatus() {
        try {
            return getString("status");
        }
        catch (IOException ex) {
            Logger.getLogger("getStatus: ConfigManager.java");
        }
        return "Wartungsmodus";
    }

    public static List<String> getBlockedCommands() {
        try {
            return getStringList("commands_blocked");
        } catch (IOException ex) {
            Logger.getLogger("Commands Blocked: ConfigManager.java");
        }
        return null;
    }

    public static List<String> getZensurListe() {
        try {
            return getStringList("zensur");
        } catch (IOException ex) {
            Logger.getLogger("Commands Blocked: ConfigManager.java");
        }
        return null;
    }

}
