package site.equipable.skEssentials;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import com.earth2me.essentials.Essentials;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import site.equipable.skEssentials.skript.utils.Utilities;

import java.io.IOException;

public final class SkEssentials extends JavaPlugin {

    private static SkEssentials instance;
    private static boolean HAS_ESSENTIALS;
    private static SkriptAddon skriptAddon;
    public static Essentials essentials;


    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        skriptAddon = Skript.registerAddon(this).setLanguageFileDirectory("lang");

        try {
            Utilities.log("&6Loading SkEssentials by Equipable...");
            Plugin essentialsChecker = Bukkit.getServer().getPluginManager().getPlugin("Essentials");

            if (essentialsChecker != null) {
                HAS_ESSENTIALS = true;
                Utilities.log( "&aEssentials was successfully found!");
                essentials = (Essentials) Bukkit.getServer().getPluginManager().getPlugin("Essentials");
                skriptAddon.loadClasses("site.equipable.skEssentials.skript");
            } else {
                getLogger().severe("Could not find Essentials! Is it up to date? Disabling SkEssentials...");
                getServer().getPluginManager().disablePlugin(this);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to load SkEssentials:" + e);
        }

        if (!Skript.isAcceptRegistrations()) {
            getLogger().severe("Skript is not accepting registrations! Please do not attempt to use /reload or any plugin that reloads other plugins! Disabling...");
            getServer().getPluginManager().disablePlugin(this);
        }

    }
}
