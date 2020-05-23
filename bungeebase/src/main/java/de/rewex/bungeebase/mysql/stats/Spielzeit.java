package de.rewex.bungeebase.mysql.stats;

import de.rewex.bungeebase.Bungeebase;
import net.md_5.bungee.api.ProxyServer;

import java.util.concurrent.TimeUnit;

public class Spielzeit {

    public void startCounter() {
        ProxyServer.getInstance().getScheduler().schedule(Bungeebase.getInstance(), new Runnable() {
            @Override
            public void run() {
                ProxyServer.getInstance().getPlayers().forEach(all -> {
                    PlayersAPI.addSpielzeit(all.getUniqueId().toString(), 1L);
                });
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    public static String getSpielzeit(String uuid) {
        long seconds = PlayersAPI.getSpielzeit(uuid);
        long minutes = 0L;
        long hours = 0L;

        while (seconds > 60L) {
            seconds -= 60L;
            minutes += 1L;
        }
        while (minutes > 60L) {
            minutes -= 60L;
            hours += 1L;
        }

        return "§b" + hours + "h " + minutes + "m ";
    }

}
