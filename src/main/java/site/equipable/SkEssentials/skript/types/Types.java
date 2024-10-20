package site.equipable.SkEssentials.skript.types;

import ch.njol.skript.registrations.Classes;
import com.earth2me.essentials.messaging.IMessageRecipient;
import net.ess3.api.events.AfkStatusChangeEvent.Cause;
import site.equipable.SkEssentials.skript.utils.EnumWrapper;

public class Types {

    static {
        EnumWrapper<Cause> AFK_CAUSE_ENUM = new EnumWrapper<>(Cause.class, "afk", null);
        Classes.registerClass(AFK_CAUSE_ENUM.getClassInfo("afkcause")
                .user("afk ?causes?")
                .name("Essentials AFK causes")
                .description("All of the supported Essentials AFK causes.")
                .since("1.0.0"));

        EnumWrapper<IMessageRecipient.MessageResponse> PRIVATE_MESSAGE_ENUM = new EnumWrapper<>(IMessageRecipient.MessageResponse.class, "message response", null);
        Classes.registerClass(PRIVATE_MESSAGE_ENUM.getClassInfo("privatemessagereponse")
                .user("private message ?reponses?")
                .name("Essentials Private Message Response")
                .description("All of the supported Essentials Private Message Responses")
                .since("1.1.0"));
    }
}
