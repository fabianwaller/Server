package de.rewex.bungeebase.servermanager.ban;

import de.rewex.bungeebase.Bungeebase;
import de.rewex.bungeebase.manager.RangManager;
import de.rewex.bungeebase.manager.utils.TimeUnit;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import java.util.List;

public class TempbanCmd extends Command {

    public TempbanCmd(String string) {
        super("tempban");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!sender.hasPermission("server.ban")) {
            sender.sendMessage(new TextComponent(Bungeebase.noperm));
            return;
        }

        if(args.length < 4) {
            sender.sendMessage(new TextComponent(Bungeebase.prefix + "§c/tempban <Spieler> <Zeit> <Einheit> <Grund>"));
            sender.sendMessage(new TextComponent(Bungeebase.prefix + "§7Einheiten §esec§7/§emin§7/§estunde§7/§etag§7/§ewoche§7/"));
            return;
        }

        long value = 0L;
        long seconds = 0L;
        try {
            value = Integer.valueOf(args[1]).intValue();
        } catch (NumberFormatException e) {
            sender.sendMessage(new TextComponent(Bungeebase.prefix + "§c<Zeit> muss eine Zahl sein"));
            return;
        }
        if(value >= 500L) {
            sender.sendMessage(new TextComponent(Bungeebase.prefix + "§c<Zeit> muss unter 500 liegen"));
            return;
        }
        String unitString = args[2];
        TimeUnit unit = null;
        List<String> unitList = TimeUnit.getUnitsAsString();
        if(unitList.contains(unitString.toLowerCase())) {
            unit = TimeUnit.getUnit(unitString);
            seconds = value * unit.getToSecond();
        }

        String playername = args[0];
        ProxiedPlayer b = ProxyServer.getInstance().getPlayer(playername);
        if(b == null) {
            sender.sendMessage(new TextComponent(Bungeebase.offplayer));
            return;
        }
        if(BanManager.isBanned(b.getUniqueId().toString())) {
            sender.sendMessage(new TextComponent(Bungeebase.prefix + "§cDieser Spieler ist bereits gebannt"));
            return;
        }

        String reason = "";
        for(int i = 3; i<args.length; i++) {
            reason = reason + args[i] + " ";
        }
        String bannedfrom = "";

        if(sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            if(b.hasPermission("server.antiban")) {
                sender.sendMessage(new TextComponent(Bungeebase.prefix + "§cDu kannst diesen Spieler nicht vom Netzwerk bannen"));
                return;
            }
            bannedfrom = RangManager.getName(p);

        } else {
            bannedfrom = "§cSystem";
        }

        BanManager.ban(b.getName(), b.getUniqueId().toString(), bannedfrom, reason, seconds);
        sender.sendMessage(new TextComponent(Bungeebase.prefix + "Du hast " + RangManager.getName(b) + " §7für §c" + value + " " + unit.getName() + " §7vom Netzwerk wegen §c" + reason + "§7gebannt"));

    }

}
