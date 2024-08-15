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

@Name("Afk Players")
@Description("The list of afk players on the server.")
@Examples({"send \"%all of the afk players%\""})
@Since("1.0.0")
public class ExprAfkPlayers extends SimpleExpression<Player> {

    static {
        Skript.registerExpression(ExprAfkPlayers.class, Player.class, ExpressionType.SIMPLE, "[all [[of] the]|the] (afk|away from keyboard|idle) players");
    }

    @Override
    protected @Nullable Player[] get(Event event) {
        List<Player> afkPlayers = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            User user = SkEssentials.essentials.getUser(player);
            if (user != null) {
                if (user.isAfk()) {
                    afkPlayers.add(player);
                }
            }
        }
        return afkPlayers.toArray(new Player[0]);
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
    public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean, ParseResult parseResult) {
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "all afk players";
    }
}
