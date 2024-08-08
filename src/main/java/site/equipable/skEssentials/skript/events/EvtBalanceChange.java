package site.equipable.skEssentials.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import net.ess3.api.events.UserBalanceUpdateEvent;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Essentials Player Balance Change")
@Description("Fired when a player's balance changes with Essentials.")
@Examples({"on player balance change:",
    "\tsend \"Your balance changed by %event-number%!\""})
@Since("INSERT VERSION")
public class EvtBalanceChange extends SkriptEvent {

    static {
        if (Bukkit.getPluginManager().getPlugin("Essentials") != null) {
            Skript.registerEvent("Essentials Player Balance Change", EvtBalanceChange.class, UserBalanceUpdateEvent.class, "[essentials|essentialsx] [player] (bal|balance) change");
            EventValues.registerEventValue(UserBalanceUpdateEvent.class, Number.class, new Getter<Number, UserBalanceUpdateEvent>() {
                public @NotNull Number get(final UserBalanceUpdateEvent event) {
                    if (event != null) {
                        return event.getNewBalance().subtract(event.getOldBalance());
                    }
                    return 0;
                }
            }, EventValues.TIME_NOW);
        }
    }

    @Override
    @SuppressWarnings({"NullableProblems"})
    public boolean init(Literal<?>[] args, int matchedPattern, SkriptParser.ParseResult parseResult) {
        return false;
    }

    @Override
    public boolean check(Event event) {
        if (event instanceof UserBalanceUpdateEvent) {
            return true;
        }
        return false;
    }


    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "essentials player balance change";
    }
}
