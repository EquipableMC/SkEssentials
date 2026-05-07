package site.equipable.SkEssentials.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import net.ess3.api.events.TPARequestEvent;

public class EvtTpaRequest extends SimpleEvent {

    static {
        Skript.registerEvent("Essentials TPA Request", EvtTpaRequest.class, TPARequestEvent.class, "[essentials|essentialsx] [player] tpa request")
                .description("Fired when a /tpa, /tpaall or /tpahere request is made.")
                .examples("on player tpa request:")
                .since("1.2.0");
    }

}
