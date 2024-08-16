package site.equipable.skEssentials.skript.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.earth2me.essentials.User;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import site.equipable.skEssentials.SkEssentials;
import java.util.ArrayList;
import java.util.List;

@Name("All God Mode Players")
@Description("The list of players in god mode on the server.")
@Examples({"send \"%all of the god mode players%\""})
@Since("1.0.0")
public class ExprGodModePlayers extends SimpleExpression<Player> {

    static {
        Skript.registerExpression(ExprGodModePlayers.class, Player.class, ExpressionType.SIMPLE, "[all [[of] the]|the] god mode players,",
                "[all [[of] the]|the] players (in|with) god mode");
    }

    @Override
    public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean, ParseResult parseResult) {
        return true;
    }



    @Override
    protected @Nullable Player[] get(Event event) {
        List<Player> godPlayers = new ArrayList<>();
        for (User user : SkEssentials.essentials.getOnlineUsers()) {
            if (user != null) {
                godPlayers.add(user.getBase());
            }
        }
        return godPlayers.toArray(new Player[0]);
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
    public String toString(@Nullable Event event, boolean b) {
        return "all god mode players";
    }
}
