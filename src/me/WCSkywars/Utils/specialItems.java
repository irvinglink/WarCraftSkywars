package me.WCSkywars.Utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static me.WCSkywars.Utils.chat.Chat;

public class specialItems {

    public static  ItemStack getSpawnBlock(){
        ItemStack item = new ItemStack(Material.BEACON);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(Chat("&bSpawn Block"));
        itemMeta.addEnchant(Enchantment.DURABILITY, 1 , false);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static  ItemStack getSpectatorBlock(){
        ItemStack item = new ItemStack(Material.DIAMOND_BLOCK);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(Chat("&7Spectator Block"));
        itemMeta.addEnchant(Enchantment.DURABILITY, 1 , false);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static  ItemStack getWaitBlock(){
        ItemStack item = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(Chat("&aWait Spawn Block"));
        itemMeta.addEnchant(Enchantment.DURABILITY, 1 , false);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static  ItemStack getWand(){
        ItemStack item = new ItemStack(Material.STONE_AXE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(Chat("&dWand"));
        itemMeta.addEnchant(Enchantment.DURABILITY, 1 , false);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);
        return item;
    }
}
