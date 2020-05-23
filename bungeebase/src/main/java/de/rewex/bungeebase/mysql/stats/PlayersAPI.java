package de.rewex.bungeebase.mysql.stats;

import de.rewex.bungeebase.mysql.MySQL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayersAPI {

    public static String coinspr = "§1•§b● Coins §7| ";
    public static String tokenspr = "§2•§a● Tokens §7| ";

    public static boolean playerExists(String uuid) {
        try {
            ResultSet rs = MySQL.getResult("SELECT * FROM PLAYERS WHERE UUID='" + uuid + "'");
            if (rs.next()) {
                return rs.getString("UUID") != null;
            }
            return false;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static void createPlayer(String uuid) {
        if (!playerExists(uuid)) {
            MySQL.update("INSERT INTO PLAYERS (UUID, COINS, TOKENS, GAMEPASS, SPIELZEIT, ONLINE) VALUES ('" + uuid + "', '0', '0', '0', " +
                    "'0', '0');");
        }
    }

    public static Integer getCoins(String uuid) {
        Integer i = Integer.valueOf(0);
        if (playerExists(uuid)) {
            try {
                ResultSet rs = MySQL.getResult("SELECT * FROM PLAYERS WHERE UUID='" + uuid + "'");
                if (rs.next()) {
                    Integer.valueOf(rs.getInt("COINS"));
                }
                i = Integer.valueOf(rs.getInt("COINS"));
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        else {
            createPlayer(uuid);
            getCoins(uuid);
        }
        return i;
    }

    public static void setCoins(String uuid, Integer coins) {
        if (playerExists(uuid)) {
            MySQL.update("UPDATE PLAYERS SET COINS='" + coins + "' WHERE UUID='" + uuid + "'");
        } else {
            createPlayer(uuid);
            setCoins(uuid, coins);
        }
    }

    public static void addCoins(String uuid, Integer coins) {
        if (playerExists(uuid)) {
            setCoins(uuid, Integer.valueOf(getCoins(uuid).intValue() + coins.intValue()));
        } else {
            createPlayer(uuid);
            addCoins(uuid, coins);
        }
    }

    public static void removeCoins(String uuid, Integer coins) {
        if (playerExists(uuid)) {
            setCoins(uuid, Integer.valueOf(getCoins(uuid).intValue() - coins.intValue()));
        } else {
            createPlayer(uuid);
            removeCoins(uuid, coins);
        }
    }

    public static Integer getTokens(String uuid) {
        Integer i = Integer.valueOf(0);
        if (playerExists(uuid)) {
            try {
                ResultSet rs = MySQL.getResult("SELECT * FROM PLAYERS WHERE UUID='" + uuid + "'");
                if (rs.next()) {
                    Integer.valueOf(rs.getInt("TOKENS"));
                }
                i = Integer.valueOf(rs.getInt("TOKENS"));
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        else {
            createPlayer(uuid);
            getTokens(uuid);
        }
        return i;
    }

    public static void setTokens(String uuid, Integer coins) {
        if (playerExists(uuid)) {
            MySQL.update("UPDATE PLAYERS SET TOKENS='" + coins + "' WHERE UUID='" + uuid + "'");
        } else {
            createPlayer(uuid);
            setCoins(uuid, coins);
        }
    }

    public static void addTokens(String uuid, Integer coins) {
        if (playerExists(uuid)) {
            setTokens(uuid, Integer.valueOf(getTokens(uuid).intValue() + coins.intValue()));
        } else {
            createPlayer(uuid);
            addTokens(uuid, coins);
        }
    }

    public static void removeTokens(String uuid, Integer coins) {
        if (playerExists(uuid)) {
            setTokens(uuid, Integer.valueOf(getTokens(uuid).intValue() - coins.intValue()));
        } else {
            createPlayer(uuid);
            removeTokens(uuid, coins);
        }
    }

    public static Long getSpielzeit(String uuid) {
        Long l = Long.valueOf(0);
        if (playerExists(uuid)) {
            try {
                ResultSet rs = MySQL.getResult("SELECT * FROM PLAYERS WHERE UUID='" + uuid + "'");
                if (rs.next()) {
                    Long.valueOf(rs.getInt("SPIELZEIT"));
                }
                l = Long.valueOf(rs.getInt("SPIELZEIT"));
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            createPlayer(uuid);
            getSpielzeit(uuid);
        }
        return l;
    }

    public static void setSpielzeit(String uuid, Long seconds) {
        if (playerExists(uuid)) {
            MySQL.update("UPDATE PLAYERS SET SPIELZEIT='" + seconds + "' WHERE UUID='" + uuid + "'");
        } else {
            createPlayer(uuid);
            setSpielzeit(uuid, seconds);
        }
    }

    public static void addSpielzeit(String uuid, Long seconds) {
        if (playerExists(uuid)) {
            setSpielzeit(uuid, Long.valueOf(getSpielzeit(uuid).longValue() + seconds.longValue()));
        } else {
            createPlayer(uuid);
            addSpielzeit(uuid, seconds);
        }
    }

}
