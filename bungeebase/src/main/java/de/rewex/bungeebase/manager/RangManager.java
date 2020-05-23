package de.rewex.bungeebase.manager;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public class RangManager {

    public static String getName(ProxiedPlayer p) {
        return getColor(p) + p.getName();
    }

    public static String getColor(ProxiedPlayer p) {
        if(p.hasPermission("team.admin")) {
            return "§4";

        } else if(p.hasPermission("team.mod")) {
            return "§9";

        } else if(p.hasPermission("team.sup")) {
            return "§b";

        } else if(p.hasPermission("team.dev")) {
            return "§d";

        } else if(p.hasPermission("team.builder")) {
            return "§a";

        } else if(p.hasPermission("team.content")) {
            return "§3";

        } else if(p.hasPermission("server.yt")) {
            return "§5";

        } else if(p.hasPermission("server.titan")) {
            return "§e";

        } else if(p.hasPermission("server.champ")) {
            return "§c";

        } else if(p.hasPermission("server.prime")) {
            return "§6";

        } else {
            return "§7";
        }
    }

    public static String getRang(ProxiedPlayer p) {
        if(p.hasPermission("team.admin")) {
            return "§4Admin";

        } else if(p.hasPermission("team.mod")) {
            return "§9Moderator";

        } else if(p.hasPermission("team.sup")) {
            return "§bSupporter";

        } else if(p.hasPermission("team.dev")) {
            return "§dDeveloper";

        } else if(p.hasPermission("team.builder")) {
            return "§aBuilder";

        } else if(p.hasPermission("team.content")) {
            return "§3Content";

        } else if(p.hasPermission("server.yt")) {
            return "§5Youtuber";

        } else if(p.hasPermission("server.titan")) {
            return "§eTitan";

        } else if(p.hasPermission("server.champ")) {
            return "§cChamp";

        } else if(p.hasPermission("server.prime")) {
            return "§6Prime";

        } else {
            return "§7Spieler";
        }
    }

    public static String getSecondColor(ProxiedPlayer p) {
        if(p.hasPermission("team.admin")) {
            return "§c";

        } else if(p.hasPermission("team.mod")) {
            return "§b";

        } else if(p.hasPermission("team.sup")) {
            return "§1";

        } else if(p.hasPermission("team.dev")) {
            return "§5";

        } else if(p.hasPermission("team.builder")) {
            return "§2";

        } else if(p.hasPermission("team.content")) {
            return "§1";

        } else if(p.hasPermission("server.yt")) {
            return "§d";

        } else if(p.hasPermission("server.titan")) {
            return "§6";

        } else if(p.hasPermission("server.champ")) {
            return "§4";

        } else if(p.hasPermission("server.prime")) {
            return "§e";

        } else {
            return "§8";
        }
    }

}
