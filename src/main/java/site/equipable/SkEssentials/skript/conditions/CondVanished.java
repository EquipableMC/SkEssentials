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

@Name("Is Vanished")
@Description("Checks whether or not a player is vanished.")
@Examples({
        "if player is vanished:",
            "\tbroadcast \"%player% is vanished!\""
})
@Since("1.0.0")
public class CondVanished extends PropertyCondition<Player> {

    static {
        register(CondVanished.class, PropertyType.BE, "(in vanish mode|vanished)", "players");
    }

    @Override
    public boolean check(Player player) {
        User user = SkEssentials.essentials.getUser(player);
        return user.isVanished();
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "vanished";
    }

}
