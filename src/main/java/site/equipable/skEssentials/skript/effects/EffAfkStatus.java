package site.equipable.skEssentials.skript.effects;

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
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;

@Name("AFK Status")
@Description("Change a player's AFK status.")
@Examples({
        "mark player as idle",
        "unafk player"
})
@Since("1.0.0")
public class EffAfkStatus extends Effect {

    static {
        Skript.registerEffect(EffAfkStatus.class, "[:un]mark %users% as [:not] (afk|away from keyboard|idle) [(due to|with reason|because [of]) %-string%]",
                "(afk|away from keyboard|idle) %users% [(due to|with reason|because [of]) %-string%]",
                "un[-](afk|away from keyboard|idle) %users%");
    }

    private Expression<User> users;
    @UnknownNullability
    private Expression<String> afkMessage;

    private boolean afk;

    @Override
    @SuppressWarnings({"unchecked", "NullableProblems"})
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean kleenean, ParseResult parseResult) {
        users = (Expression<User>) exprs[0];
        if (matchedPattern == 1 || matchedPattern == 2) {
            afkMessage = (Expression<String>) exprs[1];
        }
        afk = matchedPattern == 1 || parseResult.hasTag("un") == parseResult.hasTag("not");
        return true;
    }

    @Override
    protected void execute(@NotNull Event event) {
        String afkMessage = this.afkMessage != null ? this.afkMessage.getSingle(event) : null;
        for (User user : users.getArray(event)) {
            if (afkMessage != null) {
                user.setAfkMessage(afkMessage);
            }
            user.setAfk(afk);
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "mark " + users.toString(event, debug) + " as " + (afk ? "" : "not ") + "afk" + (afkMessage == null ? "" : " with message " + afkMessage.toString(event, debug));
    }

}
