package site.equipable.skEssentials.skript.utils;

import ch.njol.skript.util.Version;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.function.Consumer;

/**
 * Utility class to check for plugin updates.
 * <p>
 *     This class is copied from SkBee, with the PlayerJoinEvent listener (and associated methods) stripped out.
 *     <a href=https://github.com/ShaneBeee/SkBee/blob/master/src/main/java/com/shanebeestudios/skbee/api/util/UpdateChecker.java">UpdateChecker</a>
 * </p>
 *
 * @author ShaneBee
 */
public class UpdateChecker {

    public static void checkForUpdate(String pluginVersion) {
        Bukkit.getLogger().info("Checking for update...");
        getLatestReleaseVersion(version -> {
            Version plugVer = new Version(pluginVersion);
            Version curVer = new Version(version);
            if (curVer.compareTo(plugVer) <= 0) {
                Utilities.log("&aSkEssentials is up to date!");
            } else {
                Utilities.log("&cSkEssentials is no up to date!");
                Utilities.log("&c - Current version: &6" + pluginVersion);
                Utilities.log("&c - Available update: &6" + version);
                Utilities.log("&c - Download available at:&6 https://github.com/EquipableMC/SkEssentials/releases");
            }
        });
    }

    @SuppressWarnings("CallToPrintStackTrace")
    private static void getLatestReleaseVersion(Consumer<String> consumer) {
        try {
            URL url = new URI("https://api.github.com/repos/EquipableMC/SkEssentials/releases/latest").toURL();
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            JsonObject jsonObject = new Gson().fromJson(reader, JsonObject.class);
            String tag_name = jsonObject.get("tag_name").getAsString();
            consumer.accept(tag_name);
        } catch (IOException | URISyntaxException e) {
            Utilities.log("&cFailed to check for updates!");
            e.printStackTrace();
        }
    }

}
