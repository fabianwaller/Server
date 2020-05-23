package de.rewex.chatmodule;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class Chatmodule extends Plugin {

    private static Chatmodule instance;

    public static Chatmodule getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        registerCommands();
        registerListeners();

        ProxyServer.getInstance().getConsole().sendMessage(new TextComponent("» ChatModule - " + getDescription().getVersion() + " " +
                "aktiviert"));
    }

    @Override
    public void onDisable() {
        ProxyServer.getInstance().getConsole().sendMessage(new TextComponent("» ChatModule - " + getDescription().getVersion() +
                " deaktiviert"));
    }

    private void registerCommands() {
        PluginManager pm = ProxyServer.getInstance().getPluginManager();

        //pm.registerCommand(this, new CoinsCmd("coins"));


    }

    private void registerListeners() {
        PluginManager pm = ProxyServer.getInstance().getPluginManager();

        //pm.registerListener(this, new ConnectListeners());


    }
}
