package me.WCSkywars.Managers;

import org.apache.commons.lang.ObjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static me.WCSkywars.Storage.FileManager.*;

public class ArenaManager extends editorMode {

    //SCHEDULERS,ETC...

    Map<UUID, String> arenaPlayers = new HashMap();

    public Map<UUID, String> getPlayersInArena(String arena) {
        Map<UUID, String> list = this.arenaPlayers;
        return list;
    }

    public void registerPlayerSpawn(Location location) {
        String path = "Arenas." + location.getWorld().getName() + ".Player_Spawns.";
        ConfigurationSection cs = getConfig().getConfigurationSection(path);

        int toAdd = 1;
        try {
            for (int i = 0; i <= cs.getKeys(false).size()+1; i++) {
                toAdd = i;
            }
        }catch (NullPointerException e){
            toAdd = 1;
        }
        path = path+"Spawn_"+toAdd+".";
        getArenas().set(path + "x", (int) location.getX() + .500);
        getArenas().set(path + "y", location.getY());
        getArenas().set(path + "z", (int) location.getZ() + .500);
        saveConfig();
        path = "Arenas." + location.getWorld().getName() + ".Spawns.";
    }

    public void registerSpectatorSpawn(Location location) {
        String path = "Arenas." + location.getWorld().getName() + ".Spectator_Spawn.";
        getArenas().set(path + "x", (int) location.getX() + .500);
        getArenas().set(path + "y", location.getY());
        getArenas().set(path + "z", (int) location.getZ() + .500);
        saveConfig();
    }

    public void waitArenaSpawn(Location location){
        String path = "Arenas." + location.getWorld().getName() + ".Wait_Spawn.";
        getArenas().set(path + "x", (int) location.getX() + .500);
        getArenas().set(path + "y", location.getY());
        getArenas().set(path + "z", (int) location.getZ() + .500);
        saveConfig();
    }

    public void saveArena(String arenaName, Integer minPlayer, Integer maxPlayers) {

    }
}
