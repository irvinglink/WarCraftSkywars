package me.WCSkywars.Commands;

import me.WCSkywars.Managers.handlers.lobby;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.WCSkywars.Managers.editorMode.editorMode;
import static me.WCSkywars.Managers.editorMode.isEditor;
import static me.WCSkywars.Utils.chat.Chat;
import static me.WCSkywars.Utils.chat.unknownCommand;

public class skywarsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            lobby lobby = new lobby(player);
            if (args[0].equalsIgnoreCase("spawn")){
                if (args.length == 1){
                    if (isEditor(player)){
                        editorMode(player, false);
                    }
                    lobby.teleportToLobby(player);
                    player.sendMessage(Chat("&aHaz sido teletransportado al spawn.", true));
                    return true;
                }
                if (args.length == 2){
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target != null){
                        if (isEditor(target)){
                            editorMode(target, false);
                        }
                        lobby.teleportToLobby(player);
                        player.sendMessage(Chat("&aHaz sido teletransportado al spawn.", true));
                    } else {
                        player.sendMessage(Chat("&cEse jugador no esta &aconectado&7.", true));
                    }
                } else {
                    player.sendMessage(unknownCommand());
                }
                return true;
            } else {
                player.sendMessage(unknownCommand());
                return true;
            }
        }
        return false;
    }
}
