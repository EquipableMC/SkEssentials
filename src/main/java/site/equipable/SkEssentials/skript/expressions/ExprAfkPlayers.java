package site.equipable.SkEssentials.skript.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.earth2me.essentials.User;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import site.equipable.SkEssentials.SkEssentials;
import java.util.ArrayList;
import java.util.List;

@Name("AFK Players")
@Description("Gets all the players who are AFK on the server.")
@Examples(
        "send \"AFK players: %all of the afk players%\""
)
@Since("1.0.0")
public class ExprAfkPlayers extends SimpleExpression<Player> {

    static {
        Skript.registerExpression(ExprAfkPlayers.class, Player.class, ExpressionType.SIMPLE,
                "[all [[of] the]|the] (afk|away from keyboard|idle) players",
                "[all [[of] the]|the] players (in|with) (afk|away from keyboard|idle) (status|state|mode)");
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean kleenean, ParseResult parseResult) {
        return true;
    }

    @Override
    @SuppressWarnings("NullableProblems")
    protected @Nullable Player[] get(Event event) {
        List<Player> afkPlayers = new ArrayList<>();
        for (User user : SkEssentials.essentials.getOnlineUsers()) {
            if (user.isAfk()) {
                afkPlayers.add(user.getBase());
            }
        }
        return afkPlayers.toArray(Player[]::new);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NotNull Class<? extends Player> getReturnType() {
        return Player.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "all afk players";
    }

}
