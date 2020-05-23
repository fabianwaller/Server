package de.rewex.bungeebase.servermanager.kick;

import de.rewex.bungeebase.Bungeebase;
import de.rewex.bungeebase.manager.RangManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class KickCmd extends Command {

    public KickCmd(String string) {
        super("kick");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (Bungeebase.getInstance().getProxy().getPlayers().size() == 0) return;
        if (!sender.hasPermission("server.kick")) {
            sender.sendMessage(new TextComponent(Bungeebase.noperm));
            return;
        }
        for (ProxiedPlayer player : Bungeebase.getInstance().getProxy().getPlayers()) {

            if (args.length > 1) {
                if (args[0].equalsIgnoreCase(player.getName())) {

                    args[0] = "";
                    StringBuilder stringbuilder = new StringBuilder();
                    for (String arg : args) {
                        stringbuilder.append(arg).append(" ");
                    }
                    String reason = stringbuilder.toString();
                    String kicker = "";
                    if (sender instanceof ProxiedPlayer) {

                        ProxiedPlayer p = (ProxiedPlayer) sender;

						/*if(player.getName().equalsIgnoreCase(p.getName())) {
							p.sendMessage(new TextComponent(Main.prefix + "§cDu kannst dich nicht selbst vom Netzwerk kicken"));
							return;
						}*/

                        if (player.hasPermission("server.antikick")) {
                            sender.sendMessage(new TextComponent(Bungeebase.prefix + "§cDu kannst diesen Spieler nicht vom Netzwerk kicken"));
                            return;
                        }

                        kicker = RangManager.getName(p);

                    } else {
                        kicker = "§cSystem";
                    }

                    kickPlayer(player, reason, kicker);

                    sender.sendMessage(new TextComponent(Bungeebase.prefix + "Du hast " + RangManager.getName(player) + " §7vom Netzwerk wegen §c" + reason + " §7gegkickt"));

                } else {
                    sender.sendMessage(new TextComponent(Bungeebase.offplayer));
                }


            } else {
                sender.sendMessage(new TextComponent(Bungeebase.prefix + "§c/kick <Spieler> <Grund>"));
            }


        }

    }

    public static void kickPlayer(ProxiedPlayer player, String reason, String kicker) {
        player.disconnect(new TextComponent("§7Du wurdest von §9§lRewex.de§r §7gekickt!"
                + "\n "
                + "\n§7Grund:§c" + reason
                + "\n§7Gekickt von: " + kicker
                + "\n "));
    }

}