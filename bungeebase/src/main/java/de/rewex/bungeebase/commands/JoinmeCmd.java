package de.rewex.bungeebase.commands;

import de.rewex.bungeebase.Bungeebase;
import de.rewex.bungeebase.manager.RangManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class JoinmeCmd extends Command {

    public JoinmeCmd(String string) {
        super("joinme", null, "jm");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if((sender instanceof ProxiedPlayer)) {
            ProxiedPlayer p = (ProxiedPlayer) sender;

            if(!p.hasPermission("server.joinme")) {
                p.sendMessage(new TextComponent(Bungeebase.cloudnoperm));
                return;
            }

            if(p.getServer().getInfo().getName().equalsIgnoreCase("Lobby-1")) {
                p.sendMessage(new TextComponent(Bungeebase.cloudpr + "§cDieser Befehl funktioniert nicht in der Lobby§8!"));
                return;
            }
            TextComponent tc = new TextComponent();

            tc.setText(Bungeebase.cloudpr + "Klicke " + RangManager.getSecondColor(p) + "§lHIER§r§8, §7um beizutreten" );

            tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/dfngz4ierzt7ndrhnfkegg " + p.getServer().getInfo().getName()));
            tc.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT,
                    new ComponentBuilder("§a" + p.getServer().getInfo().getName()).create()));

            ProxyServer.getInstance().getPlayers().forEach(all -> {
                if(all.getServer().getInfo().getName().equalsIgnoreCase("Lobby-1")) {
                    all.sendMessage(new TextComponent(Bungeebase.cloudpr));
                    all.sendMessage(new TextComponent(Bungeebase.cloudpr + RangManager.getName(p) + " §7spielt auf §a" + p.getServer().getInfo().getName()));
                    all.sendMessage(tc);
                    all.sendMessage(new TextComponent(Bungeebase.cloudpr));
                }
            });

            p.sendMessage(new TextComponent(Bungeebase.cloudpr));
            p.sendMessage(new TextComponent(Bungeebase.cloudpr + RangManager.getName(p) + " §7spielt auf §a" + p.getServer().getInfo().getName()));
            p.sendMessage(new TextComponent(tc));
            p.sendMessage(new TextComponent(Bungeebase.cloudpr));



        } else {
            sender.sendMessage(new TextComponent(Bungeebase.noplayer));
        }

    }

}
