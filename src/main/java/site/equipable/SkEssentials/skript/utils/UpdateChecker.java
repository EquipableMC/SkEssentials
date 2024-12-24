package site.equipable.SkEssentials.skript.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import site.equipable.SkEssentials.SkEssentials;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class UpdateChecker implements Listener {

    private final JavaPlugin plugin;
    private final String currentVersion;
    private String latestVersion;

    public UpdateChecker(JavaPlugin plugin) {
        this.plugin = plugin;
        this.currentVersion = plugin.getDescription().getVersion();
        Bukkit.getPluginManager().registerEvents(this, plugin);
        checkForUpdate();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("SkEssentials.update.check") && latestVersion != null) {
            if (!currentVersion.equals(latestVersion)) {
                player.sendMessage(" ");
                player.sendRichMessage("<gold>[<gold>SkEssentials<gold>] <white>SkEssentials is <red><bold>OUTDATED</bold><white>!");
                player.sendRichMessage("<gold>[<golde>SkEssentials<gold>] <white>New version: <gold>" + latestVersion);
                player.sendRichMessage("<gold>[<orange>SkEssentials<gold>] <white>Download <gold><click:open_url:https://github.com/EquipableMC/SkEssentials/releases><hover:show_text:'<red>Click here to get the latest version!'>here<white>!</click>");
                player.sendMessage(" ");
            }
        }
    }

    private void checkForUpdate() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.github.com/repos/equipablemc/SkEssentials/releases/latest"))
                .build();

        CompletableFuture<HttpResponse<String>> responseFuture = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        responseFuture.thenApply(HttpResponse::body)
                .thenAccept(body -> {
                    JsonObject jsonResponse = new Gson().fromJson(body, JsonObject.class);
                    if (jsonResponse != null && jsonResponse.has("tag_name")) {
                        latestVersion = jsonResponse.get("tag_name").getAsString();

                        if (!currentVersion.equals(latestVersion)) {
                            Utilities.log("&cSkEssentials is not up to date!");
                            Utilities.log("&c - Current version: &6v" + currentVersion);
                            Utilities.log("&c - Available update: &6" + latestVersion);
                            Utilities.log("&c - Download available at: &6https://github.com/EquipableMC/SkEssentials/releases");
                        } else {
                            Utilities.log("&aSkEssentials is up to date!");
                        }
                    } else {
                        Bukkit.getLogger().severe("Failed to check for updates: Unexpected JSON format.");
                    }
                })
                .exceptionally(e -> {
                    Bukkit.getLogger().severe("Failed to check for updates: " + e.getMessage());
                    return null;
                });
    }

}