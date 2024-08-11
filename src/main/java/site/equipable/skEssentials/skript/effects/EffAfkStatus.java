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
import com.earth2me.essentials.User;
import net.ess3.api.events.AfkStatusChangeEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import site.equipable.skEssentials.SkEssentials;

@Name("AFK Status Change")
@Description("Set the player's AFK status.")
@Examples({"mark player as idle"})
@Since("1.0.0")
public class EffAfkStatus extends Effect {

    static {
        Skript.registerEffect(EffAfkStatus.class, "mark %players% as (afk|away from keyboard|idle) [(with reason|because [of]) %-string%]",
                "(afk|away from keyboard|idle) %players% [(with reason|because [of]) %-string%]",
                "mark %players% as not (afk|away from keyboard|idle)",
                "un(afk|away from keyboard|idle) %players%");
    }

    private Expression<Player> players;
    private Expression<String> afkmessage;

    private boolean enable;

    @Override
    protected void execute(@NotNull Event event) {
        for (Player player : players.getArray(event)) {
            User user = SkEssentials.essentials.getUser(player);
            user.setAfk(this.enable);
            user.setAfkMessage(String.valueOf(afkmessage));
        }
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "Afk Status";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        players = (Expression<Player>) exprs[0];
        enable = matchedPattern < 2;
        afkmessage = (Expression<String>) exprs[1];
        return true;
    }
}
