package de.rewex.chatmodule.chat.automessages;

import de.rewex.bungeebase.Bungeebase;
import de.rewex.chatmodule.Chatmodule;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.scheduler.ScheduledTask;
import java.util.concurrent.TimeUnit;

public class AutoMessages {

    private static ScheduledTask task;
    private static int number;

    public static void registerTask() {
        number = 0;
        task = ProxyServer.getInstance().getScheduler().schedule(Chatmodule.getInstance(), new Runnable() {

            @Override
            public void run() {
                if(number == 3) {
                    number = 0;
                }
                number++;
                switch(number) {
                    case 1:
                        ProxyServer.getInstance().broadcast(new TextComponent(Bungeebase.prefix + "§7Verwende /discord um auf unseren " +
                                "Discord Server zu gelangen"));
                        break;
                    case 2:
                        ProxyServer.getInstance().broadcast(new TextComponent(Bungeebase.prefix + "§7Verdiene mehr Coins mit dem §6Gamepass"));
                        break;
                    case 3:
                        ProxyServer.getInstance().broadcast(new TextComponent(Bungeebase.prefix + "§eBetaphase §8- §7Bugreports bitte über Discord einreichen"));
                        break;
                }

            }
        }, 1, 20, TimeUnit.MINUTES);
    }

    public static void unregisterTask() {
        if(task != null) {
            ProxyServer.getInstance().getScheduler().cancel(task);
        }
    }

}
