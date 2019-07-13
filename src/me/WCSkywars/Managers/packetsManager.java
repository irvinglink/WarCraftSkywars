package me.WCSkywars.Managers;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import static me.WCSkywars.Utils.chat.Chat;

public class packetsManager {

    public static void sendTitle(Player player, String title) {
        IChatBaseComponent titleToSend = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + Chat(title) + "\"}");
        PacketPlayOutTitle outTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleToSend);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(outTitle);
    }

    public static void sendTitle(Player player, String title, int fadein, int stay, int fadeout) {
        IChatBaseComponent titleToSend = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + Chat(title) + "\"}");
        PacketPlayOutTitle outTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleToSend, fadein, stay, fadeout);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(outTitle);
    }

    public static void sendTitleSubtitle(Player player, String title, String subtitle) {
        IChatBaseComponent titleToSend = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + Chat(title) + "\"}");
        IChatBaseComponent subtitleToSend = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + Chat(subtitle) + "\"}");
        PacketPlayOutTitle outTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleToSend);
        PacketPlayOutTitle outSubtitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subtitleToSend);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(outTitle);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(outSubtitle);
    }

    public static void sendTitleSubtitle(Player player, String title, String subtitle, int fadein, int stay, int fadeout) {
        IChatBaseComponent titleToSend = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + Chat(title) + "\"}");
        IChatBaseComponent subtitleToSend = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + Chat(subtitle) + "\"}");
        PacketPlayOutTitle outTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleToSend, fadein, stay, fadeout);
        PacketPlayOutTitle outSubtitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subtitleToSend, fadein, stay, fadeout);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(outTitle);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(outSubtitle);
    }
}
