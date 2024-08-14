package site.equipable.skEssentials.skript.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import com.earth2me.essentials.User;
import org.bukkit.entity.Player;
import site.equipable.skEssentials.SkEssentials;

@Name("is Vanished")
@Description("Check if a player is vanished or not")
@Examples("if player is in vanish:")
@Since("1.0.0")
public class CondVanished extends PropertyCondition<Player> {

    static {
        register(CondVanished.class, PropertyType.BE, "[in] vanish[ed]", "players");
    }

    @Override
    public boolean check(Player player) {
        User user = SkEssentials.essentials.getUser(player);
        return user.isVanished();
    }

    @Override
    protected String getPropertyName() {
        return "Vanished";
    }
}
