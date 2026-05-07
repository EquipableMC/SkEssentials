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
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import site.equipable.SkEssentials.SkEssentials;

@Name("Create Jail")
@Description("Create a jail with a name at a location")
@Examples({
        "create a jail named \"prison\" at location of player",
        "make a jail named \"prison\" at location of player"
})
@Since("1.2.0")
public class EffCreateJail extends Effect {

    static {
        Skript.registerEffect(EffCreateJail.class,
                "create [a] [new] jail (with name|named) %string% at %location%",
                "make [a] [new] jail (with name|named) %string% at %location%");
    }

    private Expression<String> jailName;
    private Expression<Location> jailLocation;

    @Override
    public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean, ParseResult parseResult) {
        jailName = (Expression<String>) exprs[0];
        jailLocation = (Expression<Location>) exprs[1];
        return true;
    }

    @Override
    protected void execute(Event event) {
        String name = jailName.getSingle(event);
        Location location = jailLocation.getSingle(event);
        try {
            SkEssentials.essentials.getJails().setJail(name, location);
        } catch (Exception ignored) {
        }
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "create a new jail with name " + jailName.toString(event, debug) + "at " + jailLocation.toString(event, debug);
    }
}
