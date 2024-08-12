package site.equipable.skEssentials.skript.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.util.coll.CollectionUtils;
import com.earth2me.essentials.User;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import site.equipable.skEssentials.SkEssentials;

import java.util.Map;


@Name("Set Player's God Mode")
@Description("Set the essentials god mode of a player to true or false")
@Examples({"set god mode state of player to true"})
@Since("1.0.0")
public class ExprSetGodMode extends SimplePropertyExpression<Player, Boolean> {

    static {
        register(ExprSetGodMode.class, Boolean.class, "god mode [state]", "players");
    }

    @Override
    protected String getPropertyName() {
        return "god mode";
    }

    @Override
    public Boolean convert(Player player) {
        User user = SkEssentials.essentials.getUser(player);
        return user.isGodModeEnabled();
    }

    @Override
    public @NotNull Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.RESET) {
            return CollectionUtils.array(Boolean.class);
        }
        return null;
    }

    @Override
    public void change(@NotNull Event event, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        boolean god = (delta != null && delta[0] instanceof Boolean value) ? value : false;
        for (Player player : getExpr().getArray(event)) {
            User user = SkEssentials.essentials.getUser(player);
            if (user != null) {
                user.setGodModeEnabled(god);
            }
        }
    }
}