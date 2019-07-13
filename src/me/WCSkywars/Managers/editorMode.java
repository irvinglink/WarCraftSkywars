package me.WCSkywars.Managers;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static me.WCSkywars.Utils.specialItems.*;

public class editorMode {

    //private static Player player;
    private static Map<UUID, World> arenaEditor = new HashMap();
    private static Map<UUID, ItemStack[]> inventory = new HashMap<>();

    public static void editorMode(Player player, boolean value) {
        UUID uuid = player.getUniqueId();
        if (value) {
            World arenaWorld = player.getLocation().getWorld();
            getEditorList().put(uuid, arenaWorld);

            inventory.put(uuid, player.getInventory().getContents());

            player.getInventory().clear();
            setItems(player);
        } else {
            getEditorList().remove(player.getUniqueId());
            player.getInventory().clear();
            player.getInventory().setContents(getInventoryItems(uuid));

        }
    }

    public static Map<UUID, World> getEditorList() {
        return arenaEditor;
    }

    public static boolean isEditor(Player player) {
        if (getEditorList().containsKey(player.getUniqueId())) {
            return true;
        }
        return false;
    }

    public static boolean isInventorySave(Player player) {
        if (inventory.containsKey(player)) {
            return true;
        }
        return false;
    }

    private static ItemStack[] getInventoryItems(UUID uuid) {
        return inventory.get(uuid);
    }

    public static void setItems(Player player){
        player.getInventory().addItem(getSpawnBlock());
        player.getInventory().addItem(getSpectatorBlock());
        player.getInventory().addItem(getWaitBlock());
        player.getInventory().addItem(getWand());
    }
}
