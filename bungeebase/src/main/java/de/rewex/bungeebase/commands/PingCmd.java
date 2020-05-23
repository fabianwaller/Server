package de.rewex.bungeebase.commands;

import de.rewex.bungeebase.Bungeebase;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class PingCmd extends Command {

    public PingCmd(String string) {
        super("ping");
    }

    public static String prefix = "§6•§e● Ping §7| ";

    @Override
    public void execute(CommandSender sender, String[] args) {
        if((sender instanceof ProxiedPlayer)) {
            ProxiedPlayer p = (ProxiedPlayer) sender;

            p.sendMessage(new TextComponent(prefix + "Dein Ping §e" + p.getPing() + "ms"));

        } else {
            sender.sendMessage(new TextComponent(Bungeebase.noplayer));
        }

    }
}
