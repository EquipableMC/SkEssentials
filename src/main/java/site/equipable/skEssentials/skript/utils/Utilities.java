package site.equipable.skEssentials.skript.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.util.regex.Matcher;

/**
 * @author cheeezburga
 *
 * Parts of this was taken from his Utils.java. All credits to him. I only modified it to make it fit with my plugin
 * <a href="https://github.com/cheeezburga/SkWE/blob/master/src/main/java/me/cheezburga/skwe/api/utils/Utils.java">...</a>
 */

public class Utilities {

    public static final String PLUGIN_PREFIX = ChatColor.of("#D4AF37") + "[SkEssentials]" + ChatColor.RESET;
    private static final boolean SKRIPT_EXISTS = Bukkit.getPluginManager().getPlugin("Skript") != null;
    private static final java.util.regex.Pattern HEX_PATTERN = java.util.regex.Pattern.compile("<#([A-Fa-f\\d]){6}>");

    public static void log(String format, Object... objects) {
        String log = String.format(format, objects);
        Bukkit.getConsoleSender().sendMessage(getColouredString(PLUGIN_PREFIX + " " + log));
    }

    @SuppressWarnings("deprecation") // Paper deprecation
    public static String getColouredString(String string) {
        Matcher matcher = HEX_PATTERN.matcher(string);
        if (SKRIPT_EXISTS) {
            while (matcher.find()) {
                ChatColor hexColor = ChatColor.of(matcher.group().substring(1, matcher.group().length() - 1));
                String before = string.substring(0, matcher.start());
                String after = string.substring(matcher.end());
                string = before + hexColor + after;
                matcher = HEX_PATTERN.matcher(string);
            }
        } else {
            string = HEX_PATTERN.matcher(string).replaceAll("");
        }
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
