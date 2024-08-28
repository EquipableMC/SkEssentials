package site.equipable.skEssentials.skript.expressions;

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
import site.equipable.skEssentials.SkEssentials;
import java.util.ArrayList;
import java.util.List;

@Name("God Mode Players")
@Description("Gets all the players in god mode on the server.")
@Examples(
        "send \"God mode players: %all of the god mode players%\""
)
@Since("1.0.0")
public class ExprGodModePlayers extends SimpleExpression<Player> {

    static {
        Skript.registerExpression(ExprGodModePlayers.class, Player.class, ExpressionType.SIMPLE,
                "[all [[of] the]|the] god mode players,",
                "[all [[of] the]|the] players (in|with) god mode");
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean kleenean, ParseResult parseResult) {
        return true;
    }

    @Override
    @SuppressWarnings("NullableProblems")
    protected @Nullable Player[] get(Event event) {
        List<Player> godPlayers = new ArrayList<>();
        for (User user : SkEssentials.essentials.getOnlineUsers()) {
            if (user.isGodModeEnabled()) {
                godPlayers.add(user.getBase());
            }
        }
        return godPlayers.toArray(Player[]::new);
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
        return "all god mode players";
    }

}
