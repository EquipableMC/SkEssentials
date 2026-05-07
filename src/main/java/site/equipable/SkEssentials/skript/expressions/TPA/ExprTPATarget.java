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
import net.ess3.api.IUser;
import net.ess3.api.events.TPARequestEvent;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("TPA Target")
@Description("The TPA Target")
@Examples({"on player tpa request:",
        "    set {_target} to tpa target"})
@Since("1.2.0")
public class ExprTPATarget extends SimpleExpression<IUser> {

    static {
        Skript.registerExpression(ExprTPATarget.class, IUser.class, ExpressionType.SIMPLE, "[the] tpa target");
    }

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        if (!getParser().isCurrentEvent(TPARequestEvent.class)) {
            Skript.error("Cannot use `tpa target` outside of a Tpa Request event");
            return false;
        }
        return true;
    }

    @Override
    protected IUser @Nullable [] get(Event event) {
        return new IUser[] {getTarget(event)};
    }

    private static IUser getTarget(@Nullable Event event) {
        if (event instanceof TPARequestEvent tpaRequestEvent) {
            return tpaRequestEvent.getTarget();
        }
        return null;
    }

    @Override
    public Class<? extends IUser> getReturnType() {
        return IUser.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "the tpa target";
    }
}
