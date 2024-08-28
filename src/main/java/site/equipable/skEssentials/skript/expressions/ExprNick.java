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
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Nick Players")
@Description({
        "Set the Essentials nickname of a player.",
        "NOTE: You MUST specify 'essentials' for it to work through Essentials.",
        "NOTE: You cannot set the 'full' nickname of a player."
})
@Examples(
        "set the essentials nickname of player to \"Cool Person\""
)
@Since("1.0.0")
public class ExprNick extends SimplePropertyExpression<User, String> {

    static {
        register(ExprNick.class, String.class, "essentials[x] [:full] nick[name]", "essentialsusers");
    }

    private boolean fullNick;

    @Override
    @SuppressWarnings("NullableProblems")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        fullNick = parseResult.hasTag("full");
        return super.init(exprs, matchedPattern, isDelayed, parseResult);
    }

    @Override
    public @Nullable String convert(User user) {
        return fullNick ? user.getNick() : user.getNickname();
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public Class<?>[] acceptChange(ChangeMode mode) {
        if (mode == ChangeMode.SET || (mode == ChangeMode.RESET && !fullNick)) {
            return CollectionUtils.array(String.class);
        }
        return null;
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public void change(Event event, Object @Nullable [] delta, ChangeMode mode) {
        String nickName = delta != null ? (String) delta[0] : null;
        for (User user : getExpr().getArray(event)) {
            user.setNickname(nickName);
        }
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "essentials nickname";
    }

}
