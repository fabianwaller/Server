package de.rewex.bungeebase.servermanager;

import de.rewex.bungeebase.Bungeebase;
import de.rewex.bungeebase.manager.RangManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class FindCmd extends Command {

    public FindCmd(String string) {
        super("find");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if(!(sender.hasPermission("server.find"))) {
            sender.sendMessage(new TextComponent(Bungeebase.noperm));
            return;
        }

        if(args.length == 1) {

            ProxiedPlayer a = ProxyServer.getInstance().getPlayer(args[0]);
            ServerInfo info = a.getServer().getInfo();

            if(a== null || info == null) {
                sender.sendMessage(new TextComponent(Bungeebase.offplayer));

            } else {

                TextComponent tc = new TextComponent();

                tc.setText(Bungeebase.cloudpr + "Der Spieler " + RangManager.getName(a) + " §7befindet sich auf §a" + info.getName());
                tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/dfngz4ierzt7ndrhnfkegg " + a.getServer().getInfo().getName()));
                tc.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("§7JOIN §a" + a.getServer().getInfo().getName()).create()));
                sender.sendMessage(tc);
            }

        } else {
            sender.sendMessage(new TextComponent(Bungeebase.prefix + "§c/find <Spieler>"));
        }


    }

    public static String searchPlayer(String playername) {
        ProxiedPlayer a = ProxyServer.getInstance().getPlayer(playername);
        ServerInfo info = a.getServer().getInfo();


        if(a== null || info == null) {
            return Bungeebase.offplayer;
        } else {
            return info.getName();
        }
    }

}
