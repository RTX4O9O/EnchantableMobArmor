package me.rtx4090.enchantableMobArmor;

import me.rtx4090.enchantableMobArmor.listener.PrepareAnvilListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.view.AnvilView;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

public final class EnchantableMobArmor extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("EnchantableMobArmor has been enabled!");
        // register listeners
        getServer().getPluginManager().registerEvents(new PrepareAnvilListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("EnchantableMobArmor has been disabled!");
    }




}

