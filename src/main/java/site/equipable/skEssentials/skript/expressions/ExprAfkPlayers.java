package site.equipable.skEssentials.skript.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.earth2me.essentials.User;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import site.equipable.skEssentials.SkEssentials;

public class ExprAfkPlayers extends SimplePropertyExpression<Player, Player> {

    static {
        register(ExprAfkPlayers.class, Player.class, "(afk|away from keyboard|idle) (mode|status|state)", "players");
    }

    @Override
    protected String getPropertyName() {
        return "AFK Players";
    }

    @Override
    public @Nullable Player convert(Player player) {
        User user = SkEssentials.essentials.getUser(player);
        if (user.isAfk()) {
            return player.getPlayer();
        }
        return null;
    }

    @Override
    public Class<? extends Player> getReturnType() {
        return Player.class;
    }
}
