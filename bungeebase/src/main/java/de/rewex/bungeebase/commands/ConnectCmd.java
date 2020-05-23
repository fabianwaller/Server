package de.rewex.bungeebase.commands;

import de.rewex.bungeebase.Bungeebase;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ConnectCmd extends Command {

    public ConnectCmd(String string) {
        super("dfngz4ierzt7ndrhnfkegg");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if ((sender instanceof ProxiedPlayer)) {
            ProxiedPlayer p = (ProxiedPlayer) sender;

			/*if(!p.hasPermission("server.connect")) {
				p.sendMessage(new TextComponent(Main.noperm));
				return;
			}*/

			/*if(args.length != 1) {
				p.sendMessage(new TextComponent(Main.prefix + "§c/connect <Server>"));
				return;
			}*/

            if (!p.getServer().getInfo().getName().equalsIgnoreCase(args[0])) {
                ServerInfo target = ProxyServer.getInstance().getServerInfo(args[0]);
                p.connect(target);
            } else {
                p.sendMessage(new TextComponent(Bungeebase.cloudpr + "§cDu bist bereits auf diesem Server"));
            }

        } else {
            sender.sendMessage(new TextComponent(Bungeebase.noplayer));
        }

    }

}