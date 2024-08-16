package site.equipable.skEssentials.skript.expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.earth2me.essentials.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import site.equipable.skEssentials.SkEssentials;



@Name("Nick Players")
@Description("Set the essentials nickname of a player.\n NOTE: You MUST specify essentials for it to work through Essentials.")
@Examples({"set the nickname of player to \"I am a cool person!\""})
@Since("1.0.0")
public class ExprNickPlayers extends SimplePropertyExpression<Player, String> {

    static {
        register(ExprNickPlayers.class, String.class, "essentials[x] [:full] nick[name]", "players");
    }

    private boolean fullNick;

    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        fullNick = parseResult.hasTag("full");
        return super.init(exprs, matchedPattern, isDelayed, parseResult);
    }

    @Override
    public @Nullable String convert(Player player) {
        User user = SkEssentials.essentials.getUser(player);
        if (user != null) {
            return fullNick ? user.getNick() : user.getNickname();
        }
        return null;
    }

    public Class<?>[] acceptChange(ChangeMode mode) {
        if (mode == ChangeMode.SET || mode == ChangeMode.RESET && !fullNick) {
            return CollectionUtils.array(String.class);
        }
        return null;
    }

    public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
        String nickName = delta != null ? (String) delta[0] : null;
        for (Player player : getExpr().getArray(event)) {
            User user = SkEssentials.essentials.getUser(player);
            if (user != null) {
                user.setNickname(nickName);
            }
        }
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    protected String getPropertyName() {
        return "nickname";
    }

}
