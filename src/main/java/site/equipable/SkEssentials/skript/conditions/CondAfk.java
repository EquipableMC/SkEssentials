package site.equipable.SkEssentials.skript.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import com.earth2me.essentials.User;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import site.equipable.SkEssentials.SkEssentials;

@Name("Is AFK")
@Description("Checks whether or not a player is AFK.")
@Examples({
        "if player is afk:",
            "\tbroadcast \"%player% is AFK!\""
})
@Since("1.0.0")
public class CondAfk extends PropertyCondition<Player> {

    static {
        register(CondAfk.class, PropertyType.BE, "(afk|away from keyboard|idle)", "players");
    }

    @Override
    public boolean check(Player player) {
        User user = SkEssentials.essentials.getUser(player);
        return user.isAfk();
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "afk";
    }

}
