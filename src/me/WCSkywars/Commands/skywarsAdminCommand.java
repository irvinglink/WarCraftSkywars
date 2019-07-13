package me.WCSkywars.Commands;

import me.WCSkywars.Managers.Arena;
import me.WCSkywars.Managers.handlers.lobby;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.WCSkywars.Storage.FileManager.reloadConfig;
import static me.WCSkywars.Utils.chat.*;

public class skywarsAdminCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("WCSkywars.Admin")) {
                if (args.length == 0 || args[0].equalsIgnoreCase("help")) {

                    return true;
                }
                if (args[0].equalsIgnoreCase("arena")) {
                    if (args[1].equalsIgnoreCase("create")) {
                        if (args.length == 3) {
                            String arenaName = args[2];
                            Arena arena = new Arena(arenaName, player);
                            arena.createWorldArena(arenaName);
                            arena.firstTeleport(arenaName, player);
                        } else {
                            player.sendMessage(Chat("&cUso incorrecto! Intenta con: &e/swa arena create <nombre>", true));
                        }
                    }
                    if (args[1].equalsIgnoreCase("delete")) {
                        if (args.length == 3) {
                            String arenaName = args[2];
                            player.sendMessage(Chat("&cDelete", true));
                        } else {
                            player.sendMessage(Chat("&cUso incorrecto! Intenta con: &e/swa arena delete <nombre>", true));
                        }
                        return true;
                    }
                    if (args[1].equalsIgnoreCase("editorSpawn")){
                        if (args.length == 2){
                            String arenaName = player.getLocation().getWorld().getName();
                            Arena arena = new Arena(arenaName, player);
                            arena.registerLocationFile(arenaName, player.getLocation());
                            player.sendMessage(Chat("&aSe ha modificado la ubicacion del spawn al momento de editar.", true));
                        } else {
                            player.sendMessage(Chat("&cUso incorrecto! Intenta con: &e/swa arena editorSpawn", true));
                        }
                    }
                    if (args[1].equalsIgnoreCase("teleport") || args[1].equalsIgnoreCase("tp")) {
                        if (args.length == 3) {
                            String arenaName = args[2];
                            Arena arena = new Arena(arenaName, player);
                            arena.teleportToArena(arenaName, player);
                        } else {
                            player.sendMessage(Chat("&cUso incorrecto! Intenta con: &e/swa arena teleport <nombre>", true));
                        }
                    }
                    return true;
                }
                if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                    reloadConfig();
                    player.sendMessage(Chat("&aLos archivos de configuracion se han recargado!", true));
                    return true;
                }
                if (args[0].equalsIgnoreCase("setlobby")) {
                    lobby lobby = new lobby(player);
                    lobby.registerLobby(player.getLocation());
                } else {
                    player.sendMessage(unknownCommand());
                    return true;
                }
            } else {
                player.sendMessage(noPermission());
            }
        }
        return false;
    }
}
