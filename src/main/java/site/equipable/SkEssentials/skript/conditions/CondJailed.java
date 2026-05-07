package site.equipable.SkEssentials.skript.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import com.earth2me.essentials.User;
import org.bukkit.entity.Player;
import site.equipable.SkEssentials.SkEssentials;

@Name("Is Jailed")
@Description("Checks whether or not a player is in jail.")
@Examples({
        "if player is jailed:",
        "\tbroadcast \"%player% is in jail!\""
})
@Since("1.2.0")
public class CondJailed extends PropertyCondition<Player> {

    static {
        register(CondJailed.class, PropertyType.BE, "(in jail|jailed)", "players");
    }

    @Override
    public boolean check(Player player) {
        User user = SkEssentials.essentials.getUser(player);
        return user.isJailed();
    }

    @Override
    protected String getPropertyName() {
        return "jailed";
    }
}
