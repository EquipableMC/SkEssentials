package site.equipable.skEssentials.skript.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.Boolean;

@Name("Vanish Mode")
@Description("Set the Vanish state of a player.")
@Examples({"set vanish state of player to true", "send \"%vanish mode of all players%\""})
@Since("INSERT VERSION")
public class ExprVanishMode extends SimplePropertyExpression<Player, Boolean> {

    static {
        register(ExprVanishMode.class, Boolean.class, "vanish[ed] (mode|status|state)", "players");
    }

    @Override
    protected String getPropertyName() {
        return "vanish mode";
    }

    @Override
    public Boolean convert(Player player) {
        Essentials essentials = (Essentials) Bukkit.getServer().getPluginManager().getPlugin("Essentials");
        User user = essentials.getUser(player);
        return user.isVanished();
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
        Essentials essentials = (Essentials) Bukkit.getServer().getPluginManager().getPlugin("Essentials");
        boolean vanished = (delta != null && delta[0] instanceof Boolean value) ? value : false;
        for (Player player : getExpr().getArray(event)) {
            User user = essentials.getUser(player);
            if (user != null) {
                if (vanished) {
                    user.setVanished(true);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false));
                }
            } else {
                    user.setVanished(false);
                    player.removePotionEffect(PotionEffectType.INVISIBILITY);
                }
            }
        }
}
