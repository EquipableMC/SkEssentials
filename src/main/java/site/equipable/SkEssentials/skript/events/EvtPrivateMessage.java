package site.equipable.SkEssentials.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import net.ess3.api.events.PrivateMessagePreSendEvent;
import net.ess3.api.events.PrivateMessageSentEvent;

public class EvtPrivateMessage extends SimpleEvent {
    static {
        Skript.registerEvent("Essentials Private Message Pre Sent", EvtPrivateMessage.class, PrivateMessagePreSendEvent.class, "[essentials[x]] (private|direct) message pre (send|sent)")
                .description("Called just before a private message is sent to its recipient.\n NOTE: This event CAN be cancelled.")
                .examples("on private message pre send:")
                .since("1.1.0");
        Skript.registerEvent("Essentials Private Message Sent", EvtPrivateMessage.class, PrivateMessageSentEvent.class, "[essentials[x]] (private|direct) message (send|sent)")
                .description("Called after a private message has been sent to its recipient.\n NOTE: This event CANNOT be cancelled.")
                .examples("on private message send:")
                .since("1.1.0");
    }
}
