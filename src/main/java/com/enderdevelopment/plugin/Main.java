package com.enderdevelopment.plugin;

import org.bukkit.plugin.java.JavaPlugin;
import com.enderdevelopment.plugin.combat.CombatManager;
import com.enderdevelopment.plugin.combat.CombatListener;

public final class Main extends JavaPlugin {
    private CombatManager combatManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("CombatSystem has been enabled!");
        
        // Initialize combat manager
        combatManager = new CombatManager();
        
        // Register event listener
        getServer().getPluginManager().registerEvents(new CombatListener(combatManager), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("CombatSystem has been disabled!");
    }
}