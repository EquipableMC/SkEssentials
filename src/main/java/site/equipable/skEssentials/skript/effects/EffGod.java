package site.equipable.skEssentials.skript.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.earth2me.essentials.User;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import site.equipable.skEssentials.SkEssentials;

@Name("God Player")
@Description("God or ungod a player.")
@Examples({"god player"})
@Since("INSERT VERSION")
public class EffGod extends Effect {

    static {
        Skript.registerEffect(EffGod.class, "god [mode] %players%",
                "ungod %players%");
    }

    private Expression<Player> players;

    private boolean god;
    private boolean ungod;

    @Override
    protected void execute(Event event) {
        for (Player player : players.getArray(event)) {
            User user = SkEssentials.essentials.getUser(player);
            if (god) {
                user.setGodModeEnabled(true);
            }
            if (ungod) {
                user.setGodModeEnabled(false);
            }
        }
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "god";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        players = (Expression<Player>) exprs[0];
        god = matchedPattern == 0;
        ungod = matchedPattern == 1;
        return true;
    }
}
