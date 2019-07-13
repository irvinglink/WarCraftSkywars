package me.WCSkywars.Listeners;

import me.WCSkywars.Managers.handlers.lobby;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static me.WCSkywars.Managers.editorMode.isEditor;

public class joinEvent implements Listener {

    @EventHandler
    public void joinEvent(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if (isEditor(player)){
            
        } else {
            lobby lobby = new lobby(player);
            lobby.teleportToLobby(player);
        }
    }

}
