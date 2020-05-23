package de.rewex.bungeebase.mysql;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import java.sql.*;

public class MySQL {

    public static String prefix =  "§7» §eMySQL §7| ";
    public static String host = "localhost";
    public static String database = "Server";
    public static String user = "Fabian";
    public static String password = "Lpwg1fcD";
    public static Connection con;

    public MySQL(String user, String pass, String host2, String dB) {}

    public static void connect() {
        if (!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database + "?autoReconnect=true", user, password);
                ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(prefix + "§aconnected!"));

            }
            catch (SQLException e) {
                ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(prefix + "§cError while connecting!"));
            }
        }
    }

    public static void close() {
        if (isConnected()) {
            try {
                con.close();
                ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(prefix + "§adisconnected!"));
            }
            catch (SQLException e) {
                ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(prefix + "§cError while disconnecting!"));
            }
        }
    }

    public static boolean isConnected() {
        return con != null;
    }

    public static void createPlayersTable() {
        if (isConnected()) {
            try {
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS PLAYERS (UUID VARCHAR(64), COINS int, TOKENS int, " +
                        "GAMEPASS VARCHAR(64), SPIELZEIT VARCHAR(64), ONLINE VARCHAR(64))");
                ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(prefix + "§7PLAYERS TABLE §acreated"));
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS BannedPlayers (UUID VARCHAR(64), Spielername VARCHAR(16), Bannedfrom VARCHAR(16), Ende VARCHAR(64), Grund VARCHAR(64))");
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS MutedPlayers (UUID VARCHAR(64), Spielername VARCHAR(16), Mutedfrom VARCHAR(16), Ende VARCHAR(64), Grund VARCHAR(64))");
                ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(prefix + "§7BannedPlayers / MutedPlayers TABLE " +
                        "§acreated"));
            }
            catch (SQLException e) {
                ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(prefix + "§cERROR §7while creating §7PLAYERS TABLES"));
                e.printStackTrace();
            }
        }
    }

    public static void update(String qry) {
        if (isConnected()) {
            try {
                con.createStatement().executeUpdate(qry);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static ResultSet getResult(String qry) {
        ResultSet rs = null;
        try {
            Statement st = con.createStatement();
            rs = st.executeQuery(qry);
        }
        catch (SQLException e) {
            connect();
            e.printStackTrace();
        }
        return rs;
    }

}
