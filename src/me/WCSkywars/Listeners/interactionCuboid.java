package me.WCSkywars.Listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class interactionCuboid implements Listener {

    @EventHandler
    public void interact(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();
        Location loc1 = event.getClickedBlock().getLocation();
        Location loc2 = event.getClickedBlock().getLocation();

    }

}
