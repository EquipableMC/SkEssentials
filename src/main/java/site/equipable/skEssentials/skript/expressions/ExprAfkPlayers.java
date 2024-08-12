package site.equipable.skEssentials.skript.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.earth2me.essentials.User;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import site.equipable.skEssentials.SkEssentials;

public class ExprAfkPlayers extends SimplePropertyExpression<Player, Boolean> {

    static {
        register(ExprAfkPlayers.class, Boolean.class, "(afk|away from keyboard|idle) (mode|status|state)", "players");
    }

    @Override
    protected String getPropertyName() {
        return "Expression AFK Players check";
    }

    @Override
    public @Nullable Boolean convert(Player player) {
        User user = SkEssentials.essentials.getUser(player);
        if (user.isAfk()) {
            return true;
        }
        return false;
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }
}
