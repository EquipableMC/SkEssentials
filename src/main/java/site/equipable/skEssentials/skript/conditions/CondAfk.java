package site.equipable.skEssentials.skript.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import com.earth2me.essentials.User;
import org.bukkit.entity.Player;
import site.equipable.skEssentials.SkEssentials;

@Name("is AFK")
@Description("Checks if the player is afk or not")
@Examples({"if player is away from keyboard:"})
@Since("1.0.0")
public class CondAfk extends PropertyCondition<Player> {

    static {
        register(CondAfk.class, PropertyType.BE, "(afk|away from keyboard|idle)", "players");
    }

    @Override
    public boolean check(Player player) {
        User user = SkEssentials.essentials.getUser(player);
        if (user != null) {
            return user.isAfk();
        }
        return false;
    }

    @Override
    protected String getPropertyName() {
        return "condition afk players";
    }
}
