package de.rewex.bungeebase.commands;

import de.rewex.bungeebase.Bungeebase;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class HubCmd extends Command {

    public HubCmd(String string) {
        super("hub", null, "lobby", "leave", "quit", "l");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if((sender instanceof ProxiedPlayer)) {
            ProxiedPlayer player = (ProxiedPlayer) sender;

            if(!player.getServer().getInfo().getName().equalsIgnoreCase("Lobby-1")) {
                ServerInfo target = ProxyServer.getInstance().getServerInfo("Lobby-1");
                player.connect(target);
            } else {
                player.sendMessage(new TextComponent(Bungeebase.prefix + "§cDu bist bereits auf einer Lobby§8!"));
            }

        } else {
            sender.sendMessage(new TextComponent(Bungeebase.noplayer));
        }

    }

}