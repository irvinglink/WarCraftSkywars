package me.WCSkywars;

import me.WCSkywars.Commands.skywarsAdminCommand;
import me.WCSkywars.Commands.skywarsCommand;
import me.WCSkywars.Commands.spawnCommand;
import me.WCSkywars.Listeners.blockEvents;
import me.WCSkywars.Listeners.dropEvent;
import me.WCSkywars.Listeners.joinEvent;
import me.WCSkywars.Listeners.quitEvent;
import me.WCSkywars.Storage.FileManager;
import me.WCSkywars.Storage.SQLite;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

import static me.WCSkywars.Managers.Arena.loadWorlds;
import static me.WCSkywars.Utils.chat.Chat;

public class MClass extends JavaPlugin {

    public static MClass plugin;

    boolean SQLiteConn = false;
    @Override
    public void onEnable() {
        plugin = this;
        new FileManager();
        loadWorlds();

        try {
            SQLite.connection();
            SQLiteConn = true;
        } catch (SQLException e) {
            SQLiteConn = false;
            e.printStackTrace();
        }

        getServer().getPluginCommand("sw").setExecutor(new skywarsCommand());
        getServer().getPluginCommand("swa").setExecutor(new skywarsAdminCommand());
        getServer().getPluginCommand("spawn").setExecutor(new spawnCommand());

        getServer().getPluginManager().registerEvents(new blockEvents(), this);
        getServer().getPluginManager().registerEvents(new joinEvent(), this);
        getServer().getPluginManager().registerEvents(new quitEvent(), this);
        getServer().getPluginManager().registerEvents(new dropEvent(), this);

        if (SQLiteConn){
            getServer().getConsoleSender().sendMessage(Chat("&bSQLite Connected.", true));
        } else {
            getServer().getConsoleSender().sendMessage(Chat("&cSQLite Error.", true));
        }
        getServer().getConsoleSender().sendMessage(Chat("&bPlugin developed by irvinglink.", true));
    }
}
