package site.equipable.SkEssentials.skript.expressions.TPA;

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
import com.earth2me.essentials.CommandSource;
import net.ess3.api.events.TPARequestEvent;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("TPA Request")
@Description("The person sending the TPA request")
@Examples({"on player tpa request:",
        "    set {_requester} to tpa requester"})
@Since("1.2.0")
public class ExprTPARequester extends SimpleExpression<CommandSource> {

    static {
        Skript.registerExpression(ExprTPARequester.class, CommandSource.class, ExpressionType.SIMPLE, "[the] tpa requester");
    }

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        if (!getParser().isCurrentEvent(TPARequestEvent.class)) {
            Skript.error("Cannot use `tpa requester` outside of a Tpa Request event");
            return false;
        }
        return true;
    }

    @Override
    protected CommandSource @Nullable [] get(Event event) {
        return new CommandSource[] {getRequester(event)};
    }

    private static CommandSource getRequester(@Nullable Event event) {
        if (event instanceof TPARequestEvent tpaRequestEvent) {
            return tpaRequestEvent.getRequester();
        }
        return null;
    }

    @Override
    public Class<? extends CommandSource> getReturnType() {
        return CommandSource.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "the tpa requester";
    }
}
