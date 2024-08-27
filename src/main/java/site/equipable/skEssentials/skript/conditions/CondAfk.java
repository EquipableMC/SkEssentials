package site.equipable.skEssentials.skript.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import com.earth2me.essentials.User;
import org.jetbrains.annotations.NotNull;

@Name("Is AFK")
@Description("Checks whether or not a player is AFK.")
@Examples({
        "if player is afk:",
            "\tbroadcast \"%player% is AFK!\""
})
@Since("1.0.0")
public class CondAfk extends PropertyCondition<User> {

    static {
        register(CondAfk.class, PropertyType.BE, "(afk|away from keyboard|idle)", "essentialsusers");
    }

    @Override
    public boolean check(User user) {
        return user.isAfk();
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "afk";
    }

}
