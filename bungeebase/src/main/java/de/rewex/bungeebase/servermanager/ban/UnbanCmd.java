package de.rewex.bungeebase.servermanager.ban;

import de.rewex.bungeebase.Bungeebase;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class UnbanCmd extends Command {

    public UnbanCmd(String string) {
        super("unban");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!sender.hasPermission("server.ban")) {
            sender.sendMessage(new TextComponent(Bungeebase.noperm));
            return;
        }

        if(args.length != 1) {
            sender.sendMessage(new TextComponent(Bungeebase.prefix + "§c/unban <Spieler>"));
            return;
        }

        String playername = args[0];
        if(BanManager.isBanned(BanManager.getUUID(playername))) {
            BanManager.unban(BanManager.getUUID(playername));
            sender.sendMessage(new TextComponent(Bungeebase.prefix + "Du hast " + playername + " §aerfolgreich §7vom Netzwerk entbannt"));
            return;
        }
        sender.sendMessage(new TextComponent(Bungeebase.prefix + "Der Spieler " + playername + " ist nicht vom Netzwerk gebannt"));

    }

}
