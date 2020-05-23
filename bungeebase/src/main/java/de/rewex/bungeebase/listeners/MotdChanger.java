package de.rewex.bungeebase.listeners;

import de.rewex.bungeebase.manager.ConfigManager;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class MotdChanger implements Listener {

    @EventHandler
    public void onPing(ProxyPingEvent e) {
        ServerPing con = e.getResponse();
        if(ConfigManager.getWartung()) {
            con.setDescriptionComponent(new TextComponent("§b•§9● Rewex.de §8● §r§7Minecraft Netzwerk §8➟ §91.8§7-§91.15.2§r\n"
                    + "§c§lWartungsarbeiten §8● §7" + ConfigManager.getWartungGrund()));
            con.setVersion(new ServerPing.Protocol("§8➜ §cWartungen", 2));
        } else {
            con.setDescriptionComponent(new TextComponent("§b•§9● Rewex.de §8● §r§7Minecraft Netzwerk §8➟ §91.8§7-§91.15.2§r\n"
                    + ConfigManager.getStatus().replace("&", "§")));
        }
        e.setResponse(con);

    }

}
