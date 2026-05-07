package site.equipable.SkEssentials.skript.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import com.earth2me.essentials.User;
import org.bukkit.entity.Player;
import site.equipable.SkEssentials.SkEssentials;

@Name("Is God Mode")
@Description("Checks whether or not a player is in god mode.")
@Examples({
        "if player is in god mode:",
        "\tbroadcast \"%player% is in god mode!\""
})
@Since("1.2.0")
public class CondGodMode extends PropertyCondition<Player> {

    static {
        register(CondGodMode.class, PropertyType.BE, "in god mode", "players");
    }

    @Override
    public boolean check(Player player) {
        User user = SkEssentials.essentials.getUser(player);
        return user.isGodModeEnabled();
    }

    @Override
    protected String getPropertyName() {
        return "god mode";
    }
}
