package de.rewex.bungeebase.servermanager.ban;

import de.rewex.bungeebase.Bungeebase;
import de.rewex.bungeebase.manager.RangManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class BanCmd extends Command {

    public BanCmd(String string) {
        super("ban");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!sender.hasPermission("server.ban")) {
            sender.sendMessage(new TextComponent(Bungeebase.noperm));
            return;
        }

        if(args.length < 2) {
            sender.sendMessage(new TextComponent(Bungeebase.prefix + "§c/ban <Spieler> <Grund>"));
            return;
        }

        String playername = args[0];
        ProxiedPlayer b = ProxyServer.getInstance().getPlayer(playername);
        if(b == null) {
            sender.sendMessage(new TextComponent(Bungeebase.offplayer));
            return;
        }
        if(BanManager.isBanned(b.getUniqueId().toString())) {
            sender.sendMessage(new TextComponent(Bungeebase.prefix + "§cDieser Spieler ist bereits gebannt§8!"));
            return;
        }

        String reason = "";
        for(int i = 1; i<args.length; i++) {
            reason = reason + args[i] + " ";
        }
        String bannedfrom;

        if(sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
			/*if(b.hasPermission("server.antiban")) {
				sender.sendMessage(new TextComponent(Main.prefix + "§cDu kannst diesen Spieler nicht vom Netzwerk bannen"));
				return;
			}*/
            bannedfrom = RangManager.getName(p);

        } else {
            bannedfrom = "§cSystem";
        }

        BanManager.ban(b.getName(), b.getUniqueId().toString(), bannedfrom, reason, -1L);
        sender.sendMessage(new TextComponent(Bungeebase.prefix + "Du hast " + RangManager.getName(b) + " §cpermanent §7vom Netzwerk wegen §c" + reason + "§7gebannt"));

    }

}
