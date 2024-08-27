package site.equipable.skEssentials.skript.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import com.earth2me.essentials.User;
import org.jetbrains.annotations.NotNull;

@Name("Is Vanished")
@Description("Checks whether or not a player is vanished.")
@Examples({
        "if player is vanished:",
            "\tbroadcast \"%player% is vanished!\""
})
@Since("1.0.0")
public class CondVanished extends PropertyCondition<User> {

    static {
        register(CondVanished.class, PropertyType.BE, "(in vanish mode|vanished)", "users");
    }

    @Override
    public boolean check(User user) {
        return user.isVanished();
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "vanished";
    }

}
