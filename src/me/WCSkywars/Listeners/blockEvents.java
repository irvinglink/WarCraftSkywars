package me.WCSkywars.Listeners;

import me.WCSkywars.Managers.ArenaManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import static me.WCSkywars.Utils.chat.Chat;
import static me.WCSkywars.Utils.specialItems.getSpawnBlock;

public class blockEvents extends ArenaManager implements Listener {

    @EventHandler
    public void placeBlock(BlockPlaceEvent event){
        Player player = event.getPlayer();
        Block block = event.getBlock();
        ItemStack item = player.getItemInHand();
        if (isEditor(player)){
            if (block.getType().equals(Material.BEACON) && item.getItemMeta().equals(getSpawnBlock().getItemMeta())){
                registerPlayerSpawn(block.getLocation());
                player.sendMessage(Chat("&aSpawn colocado", true));
            }
        }
    }

    @EventHandler
    public void breakBlock(BlockBreakEvent event){

    }
}
