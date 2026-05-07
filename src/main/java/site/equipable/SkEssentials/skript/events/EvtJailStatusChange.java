package site.equipable.SkEssentials.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import net.ess3.api.events.JailStatusChangeEvent;
import org.bukkit.entity.Player;

public class EvtJailStatusChange extends SimpleEvent {

    static {
        Skript.registerEvent("Essentials Jail", EvtJailStatusChange.class, JailStatusChangeEvent.class, "[essentials[x]] jail (status change|toggle)")
                .description("Fired when a player's jail status changes")
                .examples("on jail status change:")
                .since("1.2.0");
        EventValues.registerEventValue(JailStatusChangeEvent.class, Player.class, event -> event.getAffected(), EventValues.TIME_NOW);

    }
}
