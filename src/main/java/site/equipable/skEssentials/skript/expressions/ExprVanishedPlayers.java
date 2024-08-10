package site.equipable.skEssentials.skript.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.earth2me.essentials.User;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import site.equipable.skEssentials.SkEssentials;

@Name("All Vanished Players")
@Description("Returns the name of all players who are vanished")
@Examples({"send \"%vanished state of all players%\""})
@Since("1.0.0")
public class ExprVanishedPlayers extends SimplePropertyExpression<Player, Player> {

    static {
        register(ExprVanishedPlayers.class, Player.class, "vanish[ed] (mode|status|state)", "player");
    }

    @Override
    protected String getPropertyName() {
        return "";
    }

    @Override
    public @Nullable Player convert(Player player) {
        User user = SkEssentials.essentials.getUser(player);
        if (user.isVanished()) {
            return player;
        }
        return null;
    }

    @Override
    public Class<? extends Player> getReturnType() {
        return Player.class;
    }
}
