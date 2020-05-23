package de.rewex.bungeebase.servermanager;

import de.rewex.bungeebase.Bungeebase;
import de.rewex.bungeebase.manager.ConfigManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class WartungCmd extends Command {

    public WartungCmd(String string) {
        super("wartung");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!sender.hasPermission("server.wartung")) {
            sender.sendMessage(new TextComponent(Bungeebase.noperm));
            return;
        }
        // /wartung toggle true
        if(args.length==1 && args[0].equalsIgnoreCase("status")) {
            if(ConfigManager.getWartung()) {
                sender.sendMessage(new TextComponent(Bungeebase.prefix + "§7Wartungsmodus ist §ceingeschaltet"));
            } else {
                sender.sendMessage(new TextComponent(Bungeebase.prefix + "§7Wartungsmodus ist §aausgeschaltet"));
            }

        } else if(args.length>=1 && args[0].equalsIgnoreCase("toggle")) {

            if(ConfigManager.getWartung()) {

                ConfigManager.setWartung(false);
                sender.sendMessage(new TextComponent(Bungeebase.prefix + "§7Wartungsmodus wurde §aausgeschaltet"));

            } else {

                boolean kickplayers = false;
                if(args.length>1 && args[1].equalsIgnoreCase("true")) {
                    kickplayers = true;
                }

                ConfigManager.setWartung(true);
                sender.sendMessage(new TextComponent(Bungeebase.prefix + "§7Wartungsmodus wurde §ceingeschaltet"));

                if(kickplayers) {
                    for(ProxiedPlayer kick : ProxyServer.getInstance().getPlayers()) {
                        if(!kick.hasPermission("server.wartung.join")) {
                            kick.disconnect(new TextComponent("§eRewex.de §7x §c§lWartungen\n"
                                    + "\n"
                                    + "§7Grund x §cWartungsarbeiten"
                                    + "\n"));
                        }
                    }
                    sender.sendMessage(new TextComponent(Bungeebase.prefix + "§7Spieler wurden vom Netzwerk gekickt"));
                }

            }

        } else {
            sender.sendMessage(new TextComponent(Bungeebase.prefix + "§c/wartung status §7| §c/wartung toggle <kick true/false* (beim einschalten)>"));
        }


    }

}
