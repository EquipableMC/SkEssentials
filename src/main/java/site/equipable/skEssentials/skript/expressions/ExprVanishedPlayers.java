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

@Name("Vanished Players")
@Description("Gets all the vanished players on the server.")
@Examples(
        "send \"Vanished players: %all of the vanished players%\""
)
@Since("1.0.0")
public class ExprVanishedPlayers extends SimpleExpression<Player> {

    static {
        Skript.registerExpression(ExprVanishedPlayers.class, Player.class, ExpressionType.SIMPLE,
                "[all [[of] the]|the] vanish[ed] players",
                "[all [[of] the]|the] players (in|with) vanish [mode]");
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean kleenean, ParseResult parseResult) {
        return true;
    }

    @Override
    @SuppressWarnings("NullableProblems")
    protected @Nullable Player[] get(Event event) {
        List<Player> vanishedPlayers = new ArrayList<>();
        for (User user : SkEssentials.essentials.getOnlineUsers()) {
            if (user.isVanished()) {
                vanishedPlayers.add(user.getBase());
            }
        }
        return vanishedPlayers.toArray(Player[]::new);
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
        return "all vanished players";
    }

}
