package site.equipable.SkEssentials.skript.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.config.Node;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Timespan;
import ch.njol.skript.util.Timespan.TimePeriod;
import ch.njol.util.Kleenean;
import com.earth2me.essentials.User;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import site.equipable.SkEssentials.SkEssentials;

import java.util.concurrent.CompletableFuture;

@Name("Jail a Player")
@Description("Jail a player at a certain jail at a location")
@Examples({
        "jail player at \"prison\" for 20 minutes"
})
@Since("1.2.0")
public class EffJail extends Effect {

    static {
        Skript.registerEffect(EffJail.class,
                "jail %players% (in|at) %string% [for %-timespan%]");

    }

    private Expression<Player> users;
    private Expression<String> jailName;
    private Expression<Timespan> jailTime;

    @Override
    @SuppressWarnings({"unchecked", "NullableProblems"})
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean kleenean, ParseResult parseResult) {
        users = (Expression<Player>) exprs[0];
        jailName = (Expression<String>) exprs[1];
        jailTime = (Expression<Timespan>) exprs[2];
        return true;
    }

    @Override
    protected void execute(Event event) {
        Timespan essentialsJailTime = jailTime != null ? jailTime.getSingle(event) : null;
        if (essentialsJailTime != null) {
            long seconds = essentialsJailTime.getAs(TimePeriod.SECOND);
        }
        for (Player player : users.getArray(event)) {
            User user = SkEssentials.essentials.getUser(player);
            String essentialsJailName = jailName.getSingle(event);
            try {
                SkEssentials.essentials.getJails().sendToJail(user, essentialsJailName, new CompletableFuture<>());
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        if (jailTime == null)
            return "jail " + users.toString(event, debug) + "in " + jailName.toString(event, debug);
        return "jail " + users.toString(event, debug) + "in " + jailName.toString(event, debug) + "for " + jailTime.toString(event, debug);
    }
}
