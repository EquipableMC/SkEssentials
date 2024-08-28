package site.equipable.skEssentials.skript.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.registrations.Classes;
import com.earth2me.essentials.User;
import net.ess3.api.events.AfkStatusChangeEvent.Cause;
import org.bukkit.entity.Player;
import org.skriptlang.skript.lang.converter.Converters;
import site.equipable.skEssentials.SkEssentials;
import site.equipable.skEssentials.skript.utils.EnumWrapper;

public class Types {

    static {
        EnumWrapper<Cause> AFK_CAUSE_ENUM = new EnumWrapper<>(Cause.class, "afk", null);
        Classes.registerClass(AFK_CAUSE_ENUM.getClassInfo("afkcause")
                .user("afk ?causes?")
                .name("Essentials AFK causes")
                .description("All of the supported Essentials AFK causes.")
                .since("1.0.0"));

        Classes.registerClass(new ClassInfo<>(User.class, "essentialsuser")
                .user("(essentialsx? ?)?users?")
                .name("Essentials User")
                .description("Represents an Essentials User object.")
                .since("1.1.0"));

        Converters.registerConverter(Player.class, User.class, player -> SkEssentials.essentials.getUser(player));
    }
}
