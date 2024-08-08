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
import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;


@Name("Vanished players")
@Description("All players that are currently in vanish.")
@Examples({"send \"Size of all vanished players: %size of all vanished players%\""})
@Since("INSERT VERSION")
public class ExprVanishedPlayers extends SimpleExpression<Integer> {

    static {
        Skript.registerExpression(ExprVanishedPlayers.class, Integer.class, ExpressionType.SIMPLE, "[(all [[of] the]|the)] [essentials|essentialsx] vanished players");
    }

    @Override
    protected @Nullable Integer[] get(Event e) {
        Essentials essentials = (Essentials) Bukkit.getPluginManager().getPlugin("Essentials");

        if (essentials == null) {
            return new Integer[]{0};
        }

        long allVanishedPlayers = essentials.getOnlinePlayers().stream()
                .map(essentials::getUser)
                .filter(User::isVanished)
                .count();
        
        return new Integer[]{(int) allVanishedPlayers};
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "number of vanished players";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        return false;
    }
}
