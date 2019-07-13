package me.WCSkywars.Managers.handlers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import static me.WCSkywars.Managers.packetsManager.sendTitle;
import static me.WCSkywars.Storage.FileManager.getConfig;
import static me.WCSkywars.Storage.FileManager.saveConfig;
import static me.WCSkywars.Utils.chat.Chat;

public class lobby {

    Player player;

    public lobby(Player player) {
        this.player = player;
    }

    public void registerLobby(Location location) {
        String path = "Configuration.Lobby.";

        getConfig().set(path + "world", location.getWorld().getName());
        getConfig().set(path + "x",(int)location.getX()+.500);
        getConfig().set(path + "y", location.getY());
        getConfig().set(path + "z",(int) location.getZ()+.500);
        getConfig().set(path + "yaw", location.getYaw());
        getConfig().set(path + "pitch", location.getPitch());
        saveConfig();

        sendTitle(player, "&a&lLOBBY COLOCADO!");
    }

    public Location getLobby() {
        String path = "Configuration.Lobby.";
        World world = Bukkit.getWorld(getConfig().getString(path + "world"));
        double x = getConfig().getDouble(path + "x");
        double y = getConfig().getDouble(path + "y");
        double z = getConfig().getDouble(path + "z");
        float yaw = (float) getConfig().getDouble(path + "yaw");
        float pitch = (float) getConfig().getDouble(path + "pitch");
        Location location = new Location(world, x, y, z, yaw, pitch);
        return location;
    }

    public void teleportToLobby(Player player) {
        try {
            player.teleport(getLobby());
        } catch (Exception e) {
            player.sendMessage(Chat("&c&lSe ha producido un error!", true));
            e.printStackTrace();
        }
    }

}
