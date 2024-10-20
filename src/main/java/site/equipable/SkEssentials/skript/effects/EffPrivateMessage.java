package site.equipable.SkEssentials.skript.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import com.earth2me.essentials.User;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import site.equipable.SkEssentials.SkEssentials;

import java.util.Arrays;

@Name("Private Message")
@Description("Make a player send a private message to another player.")
@Examples("make player private message \"hello!\" to arg-1")
@Since("1.1.0")
public class EffPrivateMessage extends Effect {

    static {
        Skript.registerEffect(EffPrivateMessage.class,
                "make %players% ([private] message|[direct] message) %string% to %players%",
                "make %players% ([private] message|[direct] message) %players% %string%");
    }

    private Expression<Player> sender;
    private Expression<Player> recipient;
    private Expression<String> message;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean kleenean, ParseResult parseResult) {
        if (matchedPattern == 0) {
            sender = (Expression<Player>) exprs[0];
            message = (Expression<String>) exprs[1];
            recipient = (Expression<Player>) exprs[2];
            return true;
        }
        sender = (Expression<Player>) exprs[0];
        recipient = (Expression<Player>) exprs[1];
        message = (Expression<String>) exprs[2];
        return true;

    }

    @Override
    protected void execute(Event event) {
        Player[] playerSender = sender.getArray(event);
        Player[] playerRecipient = recipient.getArray(event);
        String msg = message.getSingle(event);

        Arrays.stream(playerRecipient)
                .forEach(recipientPlayer -> {
                    User recipient = SkEssentials.essentials.getUser(recipientPlayer);
                    Arrays.stream(playerSender)
                            .forEach(senderPlayer -> {
                                User sender = SkEssentials.essentials.getUser(senderPlayer);
                                recipient.sendMessage(sender, msg);
                            });
                });
    }


    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "make " + sender.toString(event, debug) + " private message " + message.toString(event, debug) + " to " + recipient.toString(event, debug);
    }
}
