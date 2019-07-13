package me.WCSkywars.Listeners;

import me.WCSkywars.Managers.handlers.lobby;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static me.WCSkywars.Managers.editorMode.editorMode;
import static me.WCSkywars.Managers.editorMode.isEditor;

public class quitEvent implements Listener {

    @EventHandler
    public void quitEvent(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if (isEditor(player)){
            editorMode(player, false);
        }
        if (!player.hasPermission("WCSkywars.Admin")){
            lobby lobby = new lobby(player);
            lobby.teleportToLobby(player);
        }
    }
}
