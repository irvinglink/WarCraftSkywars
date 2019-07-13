package me.WCSkywars.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import static me.WCSkywars.Managers.editorMode.isEditor;
import static me.WCSkywars.Utils.chat.Chat;
import static me.WCSkywars.Utils.specialItems.*;

public class dropEvent implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        Player player = event.getPlayer();
        ItemStack item = event.getItemDrop().getItemStack();
        if (isEditor(player)){
            if (item.equals(getWaitBlock()) || item.equals(getSpawnBlock()) || item.equals(getSpectatorBlock())){
                event.setCancelled(true);
                player.sendMessage(Chat("&cEstos bloques &eespeciales &cno los puedes tirar&7.", true));
            }
        }
    }
}
