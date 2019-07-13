package me.WCSkywars.Storage;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.*;

public class SQLite {

    public static Connection conn = null;
    public static String url = "jdbc:sqlite:plugins/WarCraft_Skywars/database.db";

    public static void connection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Failed to load SQLite JDBC class", e);
        }
        conn = DriverManager.getConnection(url);
        Bukkit.getLogger().info("[ViplexChat] SQLite Connected.");

        setup();
    }

    public static void setup() {
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `playerData` (`Nickname`  TEXT,`UUID` TEXT);");
            DatabaseMetaData md = connect().getMetaData();

            if (isColumnMissing(md, "Kills")) {
                statement.executeUpdate("ALTER TABLE `playerData` ADD COLUMN `Kills`");
            }
            if (isColumnMissing(md, "Deaths")) {
                statement.executeUpdate("ALTER TABLE `playerData` ADD COLUMN `Deaths`");
            }
            if (isColumnMissing(md, "Wins")) {
                statement.executeUpdate("ALTER TABLE `playerData` ADD COLUMN `Wins`");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean isColumnMissing(DatabaseMetaData metaData, String columnName)
            throws SQLException {
        ResultSet rs = metaData.getColumns(null, null, "playerdata", columnName);
        Throwable localThrowable3 = null;
        try {
            return !rs.next();
        } catch (Throwable localThrowable4) {
            localThrowable3 = localThrowable4;
            throw localThrowable4;
        } finally {
            if (rs != null) {
                if (localThrowable3 != null) {
                    try {
                        rs.close();
                    } catch (Throwable localThrowable2) {
                        localThrowable3.addSuppressed(localThrowable2);
                    }
                } else {
                    rs.close();
                }
            }
        }
    }

    private static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static boolean playerExistInTable(Player player) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM playerData WHERE Nickname=?");
            statement.setString(1, player.getName());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void registerPlayer(Player player) {
        String sql = "INSERT INTO playerData(Nickname,Color, ColorCode) VALUES(?,?,?)";
        if (!playerExistInTable(player)) {
            try (Connection conn = connect()) {
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, player.getName());
                statement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            //updateColor(player);
        }
    }

    /*public static void update(Player player, String color, String colorCode) {
        try {
            PreparedStatement statement = conn.prepareStatement("UPDATE playerdata SET Color=?, ColorCode = ? WHERE Nickname=?");
            statement.setString(1, color);
            statement.setString(2, colorCode);
            statement.setString(3, player.getName());
            statement.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public static void deletePlayer(Player player) {
        try {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM playerdata WHERE Nickname=?");
            statement.setString(1, player.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static String getUUID(Player player) {
        String colorCode = "";
        try {
            if (playerExistInTable(player)) {
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM playerdata WHERE Nickname=?");
                statement.setString(1, player.getName());
                ResultSet results = statement.executeQuery();
                results.next();
                colorCode = results.getString("UUID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colorCode;
    }

}
