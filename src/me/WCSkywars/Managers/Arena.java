package me.WCSkywars.Managers;

import org.bukkit.*;
import org.bukkit.entity.Player;

import java.io.File;

import static me.WCSkywars.Managers.packetsManager.sendTitle;
import static me.WCSkywars.Storage.FileManager.getArenas;
import static me.WCSkywars.Storage.FileManager.saveConfig;
import static me.WCSkywars.Utils.chat.Chat;

public class Arena extends ArenaManager {

    String arenaName;
    Player player;
    boolean isLoad;

    public Arena(String arenaName, Player player) {
        this.arenaName = arenaName;
        this.player = player;
    }

    public void createWorldArena(String arenaName) {
        if (!getArenas().contains("Arenas."+arenaName)) {
            WorldCreator worldCreator = new WorldCreator(arenaName);
            worldCreator.type(WorldType.FLAT);
            worldCreator.generateStructures(false);
            worldCreator.generatorSettings("2;0;1;");
            World world = worldCreator.createWorld();
            world.setAutoSave(true);
            Location location = new Location(world, 0, 40, 0);
            registerLocationFile(arenaName, location);
        } else {
            player.sendMessage(Chat("&cEse mundo ya existe!", true));
        }
        this.isLoad = true;
    }

    public void firstTeleport(String arenaName, Player player) {
        if (isLoad) {
            Location location = new Location(Bukkit.getWorld(arenaName), 0.500, 40, 0.500);
            Location blockLocation = new Location(location.getWorld(), location.getX(), 39, location.getZ());
            blockLocation.getBlock().setType(Material.GLASS);
            teleportToArena(arenaName, player);
            editorMode(player, true);
            player.sendMessage(Chat("&aCreando...", true));
            sendTitle(player, "&a&lARENA CREADA!", 0, 1, 0);
        }
    }

    public World getWorld(String arenaName) {
        return Bukkit.getWorld(arenaName);
    }

    public void teleportToArena(String arenaName, Player player) {
        File worldFile = new File(Bukkit.getServer().getWorldContainer(), arenaName);
        if (worldFile.exists()) {
            player.teleport(getLocationArena(arenaName));
        } else {
            player.sendMessage(Chat("&cEse mundo no existe.", true));
        }
    }

    public void registerLocationFile(String arenaName, Location location) {
        String path = "Arenas." + arenaName + ".";
        getArenas().set(path + "x", location.getX()+.500);
        getArenas().set(path + "y", location.getY());
        getArenas().set(path + "z", location.getZ()+.500);
        saveConfig();
    }

    public Location getLocationArena(String arenaName) {
        String path = "Arenas." + arenaName + ".";

        if (getArenas().contains(path)) {
            World world = getWorld(arenaName);
            double x = getArenas().getDouble(path + "x");
            double y = getArenas().getDouble(path + "y");
            double z = getArenas().getDouble(path + "z");
            Location location = new Location(world, x, y, z);
            return location;
        }

        return null;
    }

    public static void loadWorlds() {
        try {
            for (String arena : getArenas().getConfigurationSection("Arenas.").getKeys(false)) {
                createWorldArenaLoad(arena);
            }
        } catch (Exception e) {
        }
    }

    public static void createWorldArenaLoad(String arenaName) {
        WorldCreator worldCreator = new WorldCreator(arenaName);
        worldCreator.type(WorldType.FLAT);
        worldCreator.generateStructures(false);
        worldCreator.generatorSettings("2;0;1;");
        World world = worldCreator.createWorld();
        world.setAutoSave(true);
    }
}
