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

import net.ess3.api.events.AfkStatusChangeEvent;
import net.ess3.api.events.AfkStatusChangeEvent.Cause;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import org.jetbrains.annotations.Nullable;


@Name("Essentials Player AFK Status Change")
@Description("Fired when a player's AFK status changes")
@Examples("on AFK status change:")
@Since("INSERT VERSION")
public class EvtAfkStatusChange extends SkriptEvent {

    static {
        if (Bukkit.getPluginManager().getPlugin("Essentials") != null) {
            Skript.registerEvent("Essentials Player AFK Status Change", EvtAfkStatusChange.class, AfkStatusChangeEvent.class, "[essentials[x]] [player] afk (status change|toggle)");
            EventValues.registerEventValue(AfkStatusChangeEvent.class, Cause.class, new Getter<Cause, AfkStatusChangeEvent>() {
                @Override
                public @Nullable Cause get(AfkStatusChangeEvent afkStatusChangeEvent) {
                    return afkStatusChangeEvent.getCause();
                }
            }, EventValues.TIME_NOW);
        }
    }


    @Override
    public boolean init(Literal<?>[] literals, int i, SkriptParser.ParseResult parseResult) {
        return false;
    }

    @Override
    public boolean check(Event event) {
        return false;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "";
    }
}
