package site.equipable.skEssentials.skript.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;
import net.ess3.api.events.AfkStatusChangeEvent;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("AFK Status Change")
@Description("Set the player's AFK status.")
@Examples({"mark player as idle"})
@Since("INSERT VERSION")
public class EffAfkStatus extends Effect {

    static {
        Skript.registerEffect(EffAfkStatus.class, "mark %players% as (afk|away from keyboard|idle)",
                "mark %players% as not (afk|away from keyboard|idle)");
    }

    private Expression<Player> players;

    private boolean enable;
    private boolean disable;

    @Override
    protected void execute(@NotNull Event event) {
        Essentials essentials = (Essentials) Bukkit.getServer().getPluginManager().getPlugin("Essentials");
        for (Player player : players.getArray(event)) {
            User user = essentials.getUser(player);
            if (enable) {
                user.setAfk(true, AfkStatusChangeEvent.Cause.UNKNOWN);
            } else if (disable) {
                user.setAfk(false, AfkStatusChangeEvent.Cause.UNKNOWN);
            }
        }
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "Afk Status";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        players = (Expression<Player>) exprs[0];
        enable = matchedPattern == 0;
        disable = matchedPattern == 1;
        return true;
    }
}
