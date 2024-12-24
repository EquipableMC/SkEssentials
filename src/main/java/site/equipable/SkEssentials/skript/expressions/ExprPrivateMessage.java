package site.equipable.SkEssentials.skript.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.ess3.api.events.PrivateMessagePreSendEvent;
import net.ess3.api.events.PrivateMessageSentEvent;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprPrivateMessage extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprPrivateMessage.class, String.class, ExpressionType.SIMPLE, "[the] private message");
    }

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean kleenean, ParseResult parseResult) {
        if (!getParser().isCurrentEvent(PrivateMessagePreSendEvent.class, PrivateMessageSentEvent.class)) {
            Skript.error("Cannot use 'private message' outside of a Private Message Pre Send or Private Message Sent event");
            return false;
        }
        return true;
    }

    @Override
    protected @Nullable String[] get(Event event) {
        return new String[] {getMessage(event)};
    }

    private static String getMessage(@Nullable Event event) {
        return switch (event) {
            case PrivateMessagePreSendEvent privateMessagePreSendEvent -> privateMessagePreSendEvent.getMessage();
            case PrivateMessageSentEvent privateMessageSentEvent -> privateMessageSentEvent.getMessage();
            case null, default -> null;
        };
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "the private message";
    }
}
