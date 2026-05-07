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
import java.util.Collection;

@Name("Created Jails")
@Description("Returns a list of created jails.")
@Examples(
        "send \"Jails: %all of the created jails%\""
)
@Since("1.2.0")
public class ExprGetJailNames extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprGetJailNames.class, String.class, ExpressionType.SIMPLE,
                "[all [[of] the]|the] [essentials[x]] created jails");
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult) {
        return true;
    }

    @Override
    protected String @Nullable [] get(Event event) {
        Collection<String> createdJails;
        try {
            createdJails = SkEssentials.essentials.getJails().getList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return createdJails.toArray(String[]::new);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "all created jails";
    }
}
