package de.rewex.bungeebase;

import de.rewex.bungeebase.commands.*;
import de.rewex.bungeebase.listeners.ConnectListeners;
import de.rewex.bungeebase.listeners.Fallback;
import de.rewex.bungeebase.listeners.MotdChanger;
import de.rewex.bungeebase.manager.ConfigManager;
import de.rewex.bungeebase.mysql.MySQL;
import de.rewex.bungeebase.mysql.stats.Spielzeit;
import de.rewex.bungeebase.servermanager.FindCmd;
import de.rewex.bungeebase.servermanager.WartungCmd;
import de.rewex.bungeebase.servermanager.ban.BanCmd;
import de.rewex.bungeebase.servermanager.ban.TempbanCmd;
import de.rewex.bungeebase.servermanager.ban.UnbanCmd;
import de.rewex.bungeebase.servermanager.kick.KickCmd;
import de.rewex.bungeebase.servermanager.mute.MuteCmd;
import de.rewex.bungeebase.servermanager.mute.UnmuteCmd;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class Bungeebase extends Plugin {

    public static String prefix = "§b•§9● Server §7| ";
    public static String cloudpr = "§2•§a● Cloud §7| ";
    public static String noperm = prefix + "§cDazu hast du keine Rechte§8!";
    public static String cloudnoperm = cloudpr + "§cDazu hast du keine Rechte§8!";
    public static String offplayer = prefix + "§cDieser Spieler ist offline§8!";
    public static String noplayer = "[Server] Nur ein Spieler kann diesen Befehl ausführen";

    private static Bungeebase instance;
    Spielzeit spielzeit = new Spielzeit();

    public static Bungeebase getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        registerCommands();
        registerListeners();

        MySQL.connect();
        MySQL.createPlayersTable();

        ConfigManager.loadConfiguration();
        spielzeit.startCounter();

        ProxyServer.getInstance().getConsole().sendMessage(new TextComponent("\n" +
                "  ____                              ____                 \n" +
                " |  _ \\                            |  _ \\                \n" +
                " | |_) |_   _ _ __   __ _  ___  ___| |_) | __ _ ___  ___ \n" +
                " |  _ <| | | | '_ \\ / _` |/ _ \\/ _ \\  _ < / _` / __|/ _ \\\n" +
                " | |_) | |_| | | | | (_| |  __/  __/ |_) | (_| \\__ \\  __/\n" +
                " |____/ \\__,_|_| |_|\\__, |\\___|\\___|____/ \\__,_|___/\\___|\n" +
                "                     __/ |                               \n" +
                "                    |___/                                \n" +
                "» BungeeBase - " + getDescription().getVersion() + " aktiviert"));
    }

    @Override
    public void onDisable() {
        ProxyServer.getInstance().getConsole().sendMessage(new TextComponent("» BungeeBase - " + getDescription().getVersion() +
                " deaktiviert"));
    }

    private void registerCommands() {
        PluginManager pm = ProxyServer.getInstance().getPluginManager();

        // de.rewex.bungeebase
        // servermanager
        pm.registerCommand(this, new CoinsCmd("coins"));
        pm.registerCommand(this, new ConnectCmd("dfngz4ierzt7ndrhnfkegg")); //connect
        pm.registerCommand(this, new GamepassCmd("gamepass"));
        pm.registerCommand(this, new HubCmd("hub"));
        pm.registerCommand(this, new JoinmeCmd("joinme"));
        pm.registerCommand(this, new PingCmd("ping"));
        pm.registerCommand(this, new TokensCmd("tokens"));

        pm.registerCommand(this, new BanCmd("ban"));
        pm.registerCommand(this, new TempbanCmd("tempban"));
        pm.registerCommand(this, new UnbanCmd("unban"));
        pm.registerCommand(this, new KickCmd("kick"));
        pm.registerCommand(this, new MuteCmd("mute"));
        pm.registerCommand(this, new UnmuteCmd("unmute"));
        pm.registerCommand(this, new FindCmd("FindCmd"));
        pm.registerCommand(this, new WartungCmd("wartung"));

    }

    private void registerListeners() {
        PluginManager pm = ProxyServer.getInstance().getPluginManager();

        pm.registerListener(this, new ConnectListeners());
        pm.registerListener(this, new Fallback());
        pm.registerListener(this, new MotdChanger());

    }


}
