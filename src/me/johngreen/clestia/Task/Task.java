package me.johngreen.clestia.Task;

import me.johngreen.clestia.Clestia;
import org.powerbot.script.ClientAccessor;
import org.powerbot.script.ClientContext;

/**
 * Created by johngreen on 30/03/2015.
 */
public abstract class Task<C extends ClientContext> extends ClientAccessor<C> {
    private Clestia clestia;

    public Task(C ctx, Clestia clestia) {
        super(ctx);
        this.clestia = clestia;
    }

    public abstract boolean activate();

    public abstract void execute();
}