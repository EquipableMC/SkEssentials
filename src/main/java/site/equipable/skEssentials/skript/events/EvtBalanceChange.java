package site.equipable.skEssentials.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.entity.Player;
import net.ess3.api.events.UserBalanceUpdateEvent;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

public class EvtBalanceChange extends SimpleEvent {

    static {
        Skript.registerEvent("Essentials Player Balance Change", EvtBalanceChange.class, UserBalanceUpdateEvent.class, "[essentials|essentialsx] [player] (bal|balance) change")
                .description("Fired when a player's balance changes with Essentials.")
                .examples("on player balance change:")
                .since("1.0.0");

        EventValues.registerEventValue(UserBalanceUpdateEvent.class, Number.class, new Getter<>() {
            public @NotNull Number get(final UserBalanceUpdateEvent event) {
                if (event != null) {
                    return event.getNewBalance().subtract(event.getOldBalance());
                }
                return 0;
            }
        }, EventValues.TIME_NOW);

        EventValues.registerEventValue(UserBalanceUpdateEvent.class, Player.class, new Getter<>() {
            @Override
            public @Nullable Player get(UserBalanceUpdateEvent userBalanceUpdateEvent) {
                if (userBalanceUpdateEvent != null) {
                    return userBalanceUpdateEvent.getPlayer();
                }
                return null;
            }
        }, EventValues.TIME_NOW);
    }

}
