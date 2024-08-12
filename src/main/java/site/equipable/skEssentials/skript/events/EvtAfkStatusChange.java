package site.equipable.skEssentials.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import net.ess3.api.events.AfkStatusChangeEvent;
import net.ess3.api.events.AfkStatusChangeEvent.Cause;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;;

public class EvtAfkStatusChange extends SimpleEvent {

    static {
        Skript.registerEvent("Essentials Player AFK Status Change", EvtAfkStatusChange.class, AfkStatusChangeEvent.class, "[essentials[x]] [player] (afk|away from keyboard|idle) (status change|toggle)")
            .description("Fired when a player's AFK status changes:")
            .examples("on AFK status change:")
            .since("1.0.0");
        EventValues.registerEventValue(AfkStatusChangeEvent.class, Cause.class, new Getter<Cause, AfkStatusChangeEvent>() {
            @Override
            public @Nullable Cause get(AfkStatusChangeEvent afkStatusChangeEvent) {
                return afkStatusChangeEvent.getCause();
            }
        }, EventValues.TIME_NOW);
        EventValues.registerEventValue(AfkStatusChangeEvent.class, Player.class, new Getter<Player, AfkStatusChangeEvent>() {
            @Override
            public @Nullable Player get(AfkStatusChangeEvent afkStatusChangeEvent) {
                return afkStatusChangeEvent.getAffected().getBase();
            }
        }, EventValues.TIME_NOW);
    }

}
