package site.equipable.skEssentials;

import ch.njol.skript.SkriptAddon;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Logger;

public final class SkEssentials extends JavaPlugin {

    private static SkEssentials instance;
    private static boolean HAS_ESSENTIALS;
    public final static Logger logger = Logger.getLogger(SkEssentials.class.getName());
    private SkriptAddon skriptAddon;


    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        try {
            logger.info("&6Loading SkEssentials by Equipable...");
            skriptAddon.loadClasses("site.equipable.skEssentials.skript");
            Plugin essentialsChecker = Bukkit.getServer().getPluginManager().getPlugin("Essentials");

            if (essentialsChecker != null) {
                HAS_ESSENTIALS = true;
                logger.info( "&aEssentials was successfully found!");
            } else {
                logger.severe("Could not find Essentials! Is it up to date? Disabling SkEssentials...");
                getServer().getPluginManager().disablePlugin(this);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to load SkEssentials:" + e);
        }


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        logger.info("&6Shutting down SkEssentials, goodbye...");

    }
}
