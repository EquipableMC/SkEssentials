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

@Name("Recipient")
@Description("The person receiving the private message.")
@Examples({"on private message send:",
        "    set {_recipient} to recipient"})
public class ExprRecipient extends SimpleExpression<CommandSender> {

    static {
        Skript.registerExpression(ExprRecipient.class, CommandSender.class, ExpressionType.SIMPLE, "[the] recipient");
    }

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean kleenean, ParseResult parseResult) {
        if (!getParser().isCurrentEvent(PrivateMessagePreSendEvent.class, PrivateMessageSentEvent.class)) {
            Skript.error("Cannot use 'recipient' outside of a Private Message Pre Send or Private Message Sent event");
            return false;
        }
        return true;
    }

    @Override
    protected CommandSender[] get(Event event) {
        return new CommandSender[] {getRecipient(event)};
    }

    private static CommandSender getRecipient(@Nullable Event event) {
        if (event instanceof PrivateMessagePreSendEvent privateMessagePreSendEvent) {
            Object sender = privateMessagePreSendEvent.getRecipient();
            if (sender instanceof CommandSender commandSender) {
                return commandSender;
            } else if (sender instanceof User user) {
                return user.getBase();
            } else if (sender instanceof Console console) {
                return console.getCommandSender();
            }
        } else if (event instanceof PrivateMessageSentEvent privateMessageSentEvent) {
            Object sender = privateMessageSentEvent.getRecipient();
            if (sender instanceof CommandSender commandSender) {
                return commandSender;
            } else if (sender instanceof User user) {
                return user.getBase();
            } else if (sender instanceof Console console) {
                return console.getCommandSender();
            }
        }
        return null;
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
        return "the recipient";
    }
}
