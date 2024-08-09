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
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import site.equipable.skEssentials.SkEssentials;

@Name("Afk players")
@Description("All players that are currently Afk.")
@Examples({"send \"Size of all afk players: %size of all afk players%\""})
@Since("INSERT VERSION")
public class ExprSizeAfkPlayers extends SimpleExpression<Integer> {

    static {
        Skript.registerExpression(ExprSizeAfkPlayers.class, Integer.class, ExpressionType.SIMPLE, "[all [[of] the]] [essentials[x]] (afk|away from keyboard|idle) players");
    }

    @Override
    protected @Nullable Integer[] get(Event event) {

        long allAFKPlayers = SkEssentials.essentials.getOnlinePlayers().stream()
                .map(SkEssentials.essentials::getUser)
                .filter(User::isAfk)
                .count();
        return new Integer[]{(int) allAFKPlayers};
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "Size of Afk Players";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult) {
        return false;
    }
}
