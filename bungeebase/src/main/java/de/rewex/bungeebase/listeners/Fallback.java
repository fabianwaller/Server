package de.rewex.bungeebase.listeners;

import de.rewex.bungeebase.Bungeebase;
import net.md_5.bungee.api.AbstractReconnectHandler;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Fallback implements Listener {

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onServerKickEvent(ServerKickEvent e) {
        ProxiedPlayer p = e.getPlayer();
        ServerInfo kickedFrom = null;

        if(e.getPlayer().getServer() != null) {
            kickedFrom = p.getServer().getInfo();

        } else if(Bungeebase.getInstance().getProxy().getReconnectHandler().getServer(p) != null) {
            kickedFrom = Bungeebase.getInstance().getProxy().getReconnectHandler().getServer(p);

        } else {
            kickedFrom = AbstractReconnectHandler.getForcedHost(e.getPlayer().getPendingConnection());
            if(kickedFrom != null) {
                kickedFrom = ProxyServer.getInstance().getServerInfo(p.getPendingConnection().getListener().getDefaultServer());
            }
        }

        ServerInfo kickTo = Bungeebase.getInstance().getProxy().getServerInfo("Lobby-1");
        if((kickedFrom != null) && (kickedFrom.equals(kickTo))) {
            return;
        }
        e.setCancelled(true);
        e.setCancelServer(kickTo);
    }

}