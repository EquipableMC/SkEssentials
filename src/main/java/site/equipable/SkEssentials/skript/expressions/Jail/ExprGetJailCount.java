package site.equipable.SkEssentials.skript.expressions.Jail;

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
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import site.equipable.SkEssentials.SkEssentials;

@Name("Jail Count")
@Description("Returns a number of created jails.")
@Examples(
        "send \"Jail count: %the jail count%\""
)
@Since("1.2.0")
public class ExprGetJailCount extends SimpleExpression<Integer> {

    static {
        Skript.registerExpression(ExprGetJailCount.class, Integer.class, ExpressionType.SIMPLE,
                "[the] jail count");
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult) {
        return true;
    }

    @Override
    protected @Nullable Integer [] get(Event event) {
        return new Integer[]{SkEssentials.essentials.getJails().getCount()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "the jail count";
    }
}
