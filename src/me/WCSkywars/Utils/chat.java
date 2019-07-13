package me.WCSkywars.Utils;

import org.bukkit.ChatColor;

import static me.WCSkywars.Storage.FileManager.getConfig;

public class chat {

    public static String Chat(String textToTranslate){
        return ChatColor.translateAlternateColorCodes('&', textToTranslate);
    }

    public static String Chat(String textToTranslate, boolean value){
        if (value){
            textToTranslate = Chat(getPrefix() + textToTranslate);
        }
        return Chat(textToTranslate);
    }

    public static String getPrefix(){
        return getConfig().getString("Configuration.prefix");
    }

    public static String noPermission(){
        return Chat("&cNo tienes permiso para hacer esto!", true);
    }


    public static String unknownCommand(){
        return Chat("&cEse comando no existe.", true);
    }
}
