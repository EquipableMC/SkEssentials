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
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import site.equipable.SkEssentials.SkEssentials;

@Name("Jail Location")
@Description("Returns the location of a created jail.")
@Examples(
        "set {_jailLocation} to location of jail named \"prison\""
)
@Since("1.2.0")
public class ExprGetJailLocation extends SimpleExpression<Location> {

    static {
        Skript.registerExpression(ExprGetJailLocation.class, Location.class, ExpressionType.COMBINED,
                "[the] location of jail [named] %string%");
    }

    private Expression<String> jailName;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean, ParseResult parseResult) {
        jailName = (Expression<String>) exprs[0];
        return true;
    }

    @Override
    protected Location @Nullable [] get(Event event) {
        String jailNameString = jailName.getSingle(event);
        try {
            return new Location[]{SkEssentials.essentials.getJails().getJail(jailNameString)};
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Location> getReturnType() {
        return Location.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "the location of jail named " + jailName.toString(event, debug);
    }
}
