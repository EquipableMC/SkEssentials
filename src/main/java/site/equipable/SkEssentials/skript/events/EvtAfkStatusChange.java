package site.equipable.SkEssentials.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import net.ess3.api.events.AfkStatusChangeEvent;
import org.bukkit.entity.Player;

public class EvtAfkStatusChange extends SimpleEvent {

    static {
        Skript.registerEvent("Essentials Player AFK Status Change", EvtAfkStatusChange.class, AfkStatusChangeEvent.class, "[essentials[x]] [player] (afk|away from keyboard|idle) (status change|toggle)")
                .description("Fired when a player's AFK status changes:")
                .examples("on AFK status change:")
                .since("1.0.0");

        EventValues.registerEventValue(AfkStatusChangeEvent.class, AfkStatusChangeEvent.Cause.class, event -> event.getCause(), EventValues.TIME_NOW);
        EventValues.registerEventValue(AfkStatusChangeEvent.class, Player.class, event -> event.getAffected().getBase());
    }

}
