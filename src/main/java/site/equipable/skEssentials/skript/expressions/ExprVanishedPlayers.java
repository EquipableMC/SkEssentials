package site.equipable.skEssentials.skript.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.earth2me.essentials.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import site.equipable.skEssentials.SkEssentials;
import java.util.ArrayList;
import java.util.List;

@Name("All Vanished Players")
@Description("The list of vanished players on the server.")
@Examples({"send \"%all of the vanished players%\""})
@Since("1.0.0")
public class ExprVanishedPlayers extends SimpleExpression<Player> {

    static {
        Skript.registerExpression(ExprVanishedPlayers.class, Player.class, ExpressionType.SIMPLE, "[all [[of] the]|the] vanish[ed] players");
    }

    @Override
    protected @Nullable Player[] get(Event event) {
        List<Player> vanishedPlayers = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            User user = SkEssentials.essentials.getUser(player);
            if (user != null) {
                if (user.isVanished()) {
                    vanishedPlayers.add(player);
                }
            }
        }
        return vanishedPlayers.toArray(new Player[0]);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Player> getReturnType() {
        return Player.class;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean kleenean, ParseResult parseResult) {
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "all vanished players";
    }
}
