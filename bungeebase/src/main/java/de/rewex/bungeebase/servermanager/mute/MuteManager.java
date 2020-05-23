package de.rewex.bungeebase.servermanager.mute;

import de.rewex.bungeebase.Bungeebase;
import de.rewex.bungeebase.mysql.MySQL;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MuteManager {

    public static void mute(String playername, String uuid, String mutedfrom, String reason, long seconds) {
        long end = 0L;
        if (seconds == -1L) {
            end = -1L;
        } else {
            long current = System.currentTimeMillis();
            long millis = seconds * 1000L;
            end = current + millis;
        }
        MySQL.update("INSERT INTO MutedPlayers (UUID, Spielername, Mutedfrom, Ende, Grund) VALUES ('" + uuid + "','" + playername + "','" + mutedfrom + "','" + end + "','" + reason + "')");
        if (ProxyServer.getInstance().getPlayer(playername) != null) {
            ProxyServer.getInstance().getPlayer(playername).sendMessage(new TextComponent(Bungeebase.prefix + "§7Du wurdest von §9§lRewex" +
                    ".de§r §cgemuted§8!"));
            ProxyServer.getInstance().getPlayer(playername).sendMessage(new TextComponent(Bungeebase.prefix + "§7Grund: §c" + getReason(uuid)));
            ProxyServer.getInstance().getPlayer(playername).sendMessage(new TextComponent(Bungeebase.prefix + "§7Verbleibende Zeit: §c" + getRemainingTime(uuid)));
            ProxyServer.getInstance().getPlayer(playername).sendMessage(new TextComponent(Bungeebase.prefix + "§7Gemuted von: " + getMutedFrom(uuid)));
        }
    }

    public static void unmute(String uuid) {
        MySQL.update("DELETE FROM MutedPlayers WHERE UUID='" + uuid + "'");
    }

    public static boolean isMuted(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT Ende FROM MutedPlayers WHERE UUID='" + uuid + "'");
        try {
            return rs.next();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getUUID(String playername) {
        ResultSet rs = MySQL.getResult("SELECT * FROM MutedPlayers WHERE Spielername='" + playername + "'");
        try {
            if (rs.next()) {
                return rs.getString("UUID");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getReason(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT * FROM MutedPlayers WHERE UUID='" + uuid + "'");
        try {
            if (rs.next()) {
                return rs.getString("Grund");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Long getEnd(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT * FROM MutedPlayers WHERE UUID='" + uuid + "'");
        try {
            if (rs.next()) {
                return Long.valueOf(rs.getLong("Ende"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getMutedFrom(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT * FROM MutedPlayers WHERE UUID='" + uuid + "'");
        try {
            if (rs.next()) {
                return rs.getString("Mutedfrom");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List<String> getmutedPlayers() {
        List<String> list = new ArrayList();
        ResultSet rs = MySQL.getResult("SELECT * FROM MutedPlayers");
        try {
            while (rs.next()) {
                list.add(rs.getString("Spielername"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static String getRemainingTime(String uuid) {
        long current = System.currentTimeMillis();
        long end = getEnd(uuid).longValue();
        if (end == -1L) {
            return "�cPERMANENT";
        }
        long millis = end - current;

        long seconds = 0L;
        long minutes = 0L;
        long hours = 0L;
        long days = 0L;
        long weeks = 0L;
        while (millis > 1000L) {
            millis -= 1000L;
            seconds += 1L;
        }
        while (seconds > 60L) {
            seconds -= 60L;
            minutes += 1L;
        }
        while (minutes > 60L) {
            minutes -= 60L;
            hours += 1L;
        }
        while (hours > 24L) {
            hours -= 24L;
            days += 1L;
        }
        while (days > 7L)
        {
            days -= 7L;
            weeks += 1L;
        }
        return
                "§c" + weeks + "w " + days + "d " + hours + "h " + minutes + "m " + seconds + "s";
    }

}
