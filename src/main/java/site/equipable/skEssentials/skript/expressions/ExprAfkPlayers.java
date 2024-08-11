package site.equipable.skEssentials.skript.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import site.equipable.skEssentials.SkEssentials;

@Name("All AFK Players")
@Description("Returns the name of all players who are afk.")
@Examples({"send \"%afk mode of all players%\""})
@Since("1.0.0")
public class ExprAfkPlayers extends SimplePropertyExpression<Player, Player> {

    static {
        register(ExprAfkPlayers.class, Player.class, "(afk|away from keyboard|idle) (mode|status|state)", "players");
    }

    @Override
    protected String getPropertyName() {
        return "Afk Players";
    }

    @Override
    public @Nullable Player convert(Player player) {
        User user = SkEssentials.essentials.getUser(player);
        if (user.isAfk()) {
            return player;
        }
        return null;
    }

    @Override
    public Class<? extends Player> getReturnType() {
        return Player.class;
    }
}
