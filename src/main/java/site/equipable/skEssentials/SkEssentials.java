package site.equipable.skEssentials;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import com.earth2me.essentials.Essentials;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import site.equipable.skEssentials.skript.utils.Utilities;

import java.io.IOException;

public final class SkEssentials extends JavaPlugin {

    public static Essentials essentials;

    @Override
    public void onEnable() {
        // Plugin startup logic

        if (!Skript.isAcceptRegistrations()) {
            getLogger().severe("Skript is not accepting registrations! Please do not attempt to use /reload or any plugin that reloads other plugins! Disabling...");
            getServer().getPluginManager().disablePlugin(this);
        }

        int pluginID = 22965;
        Metrics metrics = new Metrics(this, pluginID);

        SkEssentials instance = this;
        SkriptAddon skriptAddon = Skript.registerAddon(this).setLanguageFileDirectory("lang");

        try {
            Utilities.log("&6Loading SkEssentials by Equipable...");
            Plugin essentialsChecker = Bukkit.getServer().getPluginManager().getPlugin("Essentials");

            if (essentialsChecker != null) {
                Utilities.log("&aEssentials was successfully found!");
                essentials = (Essentials) Bukkit.getServer().getPluginManager().getPlugin("Essentials");
                skriptAddon.loadClasses("site.equipable.skEssentials.skript");
            } else {
                getLogger().severe("Could not find Essentials! Disabling SkEssentials...");
                getServer().getPluginManager().disablePlugin(this);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to load SkEssentials:" + e);
        }


    }
}
