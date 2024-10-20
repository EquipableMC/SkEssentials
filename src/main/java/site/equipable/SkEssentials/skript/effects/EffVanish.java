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

@Name("Vanish Player")
@Description("Vanish or unvanish a player.")
@Examples({
        "vanish player",
        "make all players unvanish"
})
@Since("1.0.0")
public class EffVanish extends Effect {

    static {
        Skript.registerEffect(EffVanish.class,
                "[:un]vanish %players%",
                "make %players% [:un]vanish");
    }

    private Expression<Player> users;

    private boolean vanish;

    @Override
    @SuppressWarnings({"unchecked", "NullableProblems"})
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean kleenean, ParseResult parseResult) {
        users = (Expression<Player>) exprs[0];
        vanish = !parseResult.hasTag("un");
        return true;
    }

    @Override
    @SuppressWarnings("NullableProblems")
    protected void execute(Event event) {
        for (Player player : users.getArray(event)) {
            User user = SkEssentials.essentials.getUser(player);
            user.setVanished(vanish);
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return (vanish ? "" : "un") + "vanish " + users.toString(event, debug);
    }
}
