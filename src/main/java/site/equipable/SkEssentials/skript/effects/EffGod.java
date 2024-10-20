package site.equipable.SkEssentials.skript.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import com.earth2me.essentials.User;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import site.equipable.SkEssentials.SkEssentials;

@Name("God Mode")
@Description("Enable or disable a player's god mode.")
@Examples({
        "enable god mode of player",
        "disable god mode for all players"
})
@Since("1.0.0")
public class EffGod extends Effect {

    static {
        Skript.registerEffect(EffGod.class,
                "enable god mode (for|of) %players%",
                "disable god mode (for|of) %players%");
    }

    private Expression<Player> users;

    private boolean god;

    @Override
    @SuppressWarnings({"unchecked", "NullableProblems"})
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean kleenean, ParseResult parseResult) {
        users = (Expression<Player>) exprs[0];
        god = matchedPattern == 0;
        return true;
    }

    @Override
    @SuppressWarnings("NullableProblems")
    protected void execute(Event event) {
        for (Player player : users.getArray(event)) {
            User user = SkEssentials.essentials.getUser(player);
            user.setGodModeEnabled(god);
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return (god ? "enable" : "disable") + " god mode of " + users.toString(event,debug);
    }

}
