package me.johngreen.clestia.Task;

import me.johngreen.clestia.Clestia;
import org.powerbot.script.rt6.ClientContext;

/**
 * Created by johngreen on 30/03/2015.
 */
public abstract class RunTask extends Task {
    private boolean isDone;
    public ClientContext ctx;
    public Clestia clestia;

    public RunTask(ClientContext ctx, Clestia clestia) {
        super(ctx, clestia);
        isDone = false;
        this.ctx = ctx;
        this.clestia = clestia;
    }

    public boolean isDone() {
        return isDone;
    }

    public void stop() {
        isDone = true;
    }
}