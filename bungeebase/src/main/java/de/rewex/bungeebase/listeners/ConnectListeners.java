package de.rewex.bungeebase.listeners;

import de.rewex.bungeebase.manager.ConfigManager;
import de.rewex.bungeebase.mysql.stats.PlayersAPI;
import de.rewex.bungeebase.servermanager.ban.BanManager;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ConnectListeners implements Listener {

    @EventHandler
    public void onPostLogin(PostLoginEvent e) {
        ProxiedPlayer p = e.getPlayer();

        //ChatclearCmd.clearChat(p);
        //p.sendMessage(new TextComponent(Main.prefix + "Willkommen auf dem §9§lRewex.de §r§7Minigames Netzwerk §7(§eBetaphase§7)"));

        PlayersAPI.createPlayer(p.getUniqueId().toString());

    }

    @EventHandler
    public void onPlayerJoin(ServerConnectEvent e) {
        ProxiedPlayer p = e.getPlayer();

        if(BanManager.isBanned(p.getUniqueId().toString()) ) {
            long current = System.currentTimeMillis();
            long end = BanManager.getEnd(p.getUniqueId().toString());
            if(((current < end ? 1 : 0) | (end == -1L ? 1 : 0)) != 0) {
                p.disconnect(new TextComponent("§7Du wurdest von §9§lRewex.de§r §7gebannt!"
                        + "\n "
                        + "\n§7Grund: §c" + BanManager.getReason(p.getUniqueId().toString())
                        + "\n§7Verbleibende Zeit: §c" + BanManager.getRemainingTime(p.getUniqueId().toString())
                        + "\n§7Gebannt von: " + BanManager.getBannedFrom(p.getUniqueId().toString())
                        + "\n"
                        + "\n§7Du kannst auf unserm §9Discord §7einen Entbannungsantrag stellen."
                        + "\n "));
            } else {
                BanManager.unban(p.getUniqueId().toString());
            }
        }

        if(ConfigManager.getWartung()) {

            int progress = ConfigManager.getWartungFortschritt();
            String fortschritt = "";
            String complete = "▮▮▮▮▮▮▮▮▮▮▮▮▮▮▮▮▮▮▮▮";
            while(progress >= 5) {
                fortschritt = fortschritt + "▮";
                complete.substring(1);
                progress = progress-5;
            }

            if(p.getServer() == null) {
                if(!p.hasPermission("server.wartung.join")) {
                    p.disconnect(new TextComponent("  §9§lRewex.de §7x §cWartungen"
                            + "\n"
                            + "\n§7Grund x " + ConfigManager.getWartungGrund()
                            + "\n§7Dauer x §9Unbekannt"
                            + "\n"
                            + "\n§7Fortschritt x §a" + fortschritt + "§c" + complete + "§7 (§a" + ConfigManager.getWartungFortschritt() + "%§7)\n"
                            + ""));

                    e.setCancelled(true);
                }
            }

        }
    }

}
