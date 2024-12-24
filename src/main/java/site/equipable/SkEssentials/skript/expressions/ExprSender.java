package site.equipable.SkEssentials.skript.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.earth2me.essentials.Console;
import com.earth2me.essentials.User;
import net.ess3.api.events.PrivateMessagePreSendEvent;
import net.ess3.api.events.PrivateMessageSentEvent;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Private Message Sender")
@Description("The person sending the private message.")
@Examples({"on private message send:",
        "    set {_recipient} to message sender"})
public class ExprSender extends SimpleExpression<CommandSender> {

    static {
        Skript.registerExpression(ExprSender.class, CommandSender.class, ExpressionType.SIMPLE, "[the] [private] message sender");
    }

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean kleenean, ParseResult parseResult) {
       if (!getParser().isCurrentEvent(PrivateMessagePreSendEvent.class, PrivateMessageSentEvent.class)) {
           Skript.error("Cannot use 'message sender' outside of a Private Message Pre Send or Private Message Sent event");
           return false;
       }
       return true;
    }

    @Override
    protected @Nullable CommandSender[] get(Event event) {
        return new CommandSender[] {getSender(event)};
    }

    private static CommandSender getSender(@Nullable Event event) {
        return switch (event) {
            case PrivateMessagePreSendEvent privateMessagePreSendEvent -> {
                Object sender = privateMessagePreSendEvent.getSender();
                if (sender instanceof CommandSender commandSender) {
                    yield commandSender;
                } else if (sender instanceof User user) {
                    yield user.getBase();
                } else if (sender instanceof Console console) {
                    yield console.getCommandSender();
                } else {
                    yield null;
                }
            }
            case PrivateMessageSentEvent privateMessageSentEvent -> {
                Object sender = privateMessageSentEvent.getSender();
                if (sender instanceof CommandSender commandSender) {
                    yield commandSender;
                } else if (sender instanceof User user) {
                    yield user.getBase();
                } else if (sender instanceof Console console) {
                    yield console.getCommandSender();
                } else {
                    yield null;
                }
            }
            case null, default -> null;
        };
    }


    @Override
    public Class<? extends CommandSender> getReturnType() {
        return CommandSender.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "the private sender";
    }
}
