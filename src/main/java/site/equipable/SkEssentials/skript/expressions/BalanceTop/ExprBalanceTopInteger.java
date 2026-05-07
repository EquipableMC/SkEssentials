package site.equipable.SkEssentials.skript.expressions.BalanceTop;

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
import net.essentialsx.api.v2.services.BalanceTop;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import site.equipable.SkEssentials.SkEssentials;
import java.util.Map;
import java.util.UUID;

@Name("Get the player at a certain balance top")
@Description("Get the player who is at a certain baltop! \nNOTE: This requires /baltop to be ran. This depends on Essentialx's cache. I will not code a way to force the cache to update.")
@Examples({
        "the essentials baltop from 2"
})
@Since("1.2.0")
public class ExprBalanceTopInteger extends SimpleExpression<OfflinePlayer> {

    static {
        Skript.registerExpression(ExprBalanceTopInteger.class, OfflinePlayer.class, ExpressionType.COMBINED,
                "[the] essentials[x] bal[ance] top [(of|from)] %integer%");
    }

    private Expression<Integer> balTopInt;

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        balTopInt = (Expression<Integer>) exprs[0];
        return true;
    }

    @Override
    protected OfflinePlayer @Nullable [] get(Event event) {
        Integer balanceTopInt = balTopInt.getSingle(event);
        if (balanceTopInt == null || balanceTopInt < 1) return null;

        BalanceTop balanceTop = SkEssentials.essentials.getBalanceTop();

        // Never trigger a build -> just read whatever is already there
        Map<UUID, BalanceTop.Entry> cache = balanceTop.getBalanceTopCache();
        if (cache == null || cache.isEmpty()) return null;
        if (balanceTopInt > cache.size()) return null;

        UUID uuid = cache.keySet().stream()
                .skip(balanceTopInt - 1)
                .findFirst()
                .orElse(null);

        if (uuid == null) return null;

        return new OfflinePlayer[]{Bukkit.getOfflinePlayer(uuid)};
    }

    @Override
    public boolean isSingle() { return true; }

    @Override
    public Class<? extends OfflinePlayer> getReturnType() { return OfflinePlayer.class; }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "essentialsx balance top " + balTopInt.toString(event, debug);
    }
}
