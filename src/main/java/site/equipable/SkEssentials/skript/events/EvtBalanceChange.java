package site.equipable.SkEssentials.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import org.bukkit.entity.Player;
import net.ess3.api.events.UserBalanceUpdateEvent;

public class EvtBalanceChange extends SimpleEvent {

    static {
        Skript.registerEvent("Essentials Player Balance Change", EvtBalanceChange.class, UserBalanceUpdateEvent.class, "[essentials|essentialsx] [player] (bal|balance) change")
                .description("Fired when a player's balance changes with Essentials.")
                .examples("on player balance change:")
                .since("1.0.0");

        EventValues.registerEventValue(UserBalanceUpdateEvent.class, Number.class, event -> {
            if (event != null) {
                return event.getNewBalance().subtract(event.getOldBalance());
            }
            return 0;
        }, EventValues.TIME_NOW);

        EventValues.registerEventValue(UserBalanceUpdateEvent.class, Player.class, userBalanceUpdateEvent -> {
            if (userBalanceUpdateEvent != null) {
                return userBalanceUpdateEvent.getPlayer();
            }
            return null;
        }, EventValues.TIME_NOW);
    }

}
