package site.equipable.skEssentials.skript.types;

import ch.njol.skript.registrations.Classes;
import net.ess3.api.events.AfkStatusChangeEvent.Cause;
import site.equipable.skEssentials.skript.utils.EnumWrapper;

public class Types {

    static {
        EnumWrapper<Cause> AFK_CAUSE_ENUM = new EnumWrapper<>(Cause.class, "afk", null);
        Classes.registerClass(AFK_CAUSE_ENUM.getClassInfo("afkcause")
                .user("afk ?causes?")
                .name("Essentials AFK causes")
                .description("All the supported AFK causes in Essentials")
                .since("1.0.0"));
    }
}
