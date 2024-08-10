package site.equipable.skEssentials.skript.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import com.earth2me.essentials.User;
import org.bukkit.entity.Player;
import site.equipable.skEssentials.SkEssentials;

@Name("Cond Afk Players")
@Description("This is used to check a player's afk status.\n True = They are afk, False = they are not afk.")
@Examples({"if afk mode of player is true:"})
@Since("INSERT VERSION")
public class CondAfkPlayers extends PropertyCondition {

    static {
        register(CondAfkPlayers.class, "(afk|away from keyboard|idle) (mode|status|state)", "players");
    }

    @Override
    public boolean check(Object o) {
        if (o instanceof Player) {
            User user = SkEssentials.essentials.getUser(o);
            return user.isAfk();
        }
        return false;
    }

    @Override
    protected String getPropertyName() {
        return "Condition Afk Players";
    }
}
