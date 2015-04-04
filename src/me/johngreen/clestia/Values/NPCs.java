package me.johngreen.clestia.Values;

import org.powerbot.script.rt6.ClientContext;

/**
 * Created by johngreen on 01/04/15.
 */
public enum NPCs {
    Millie_Miller(3806),
    Cook(278),
    Gillie(3807)
    ;
    private int id;
    private NPCs(int id){
        this.id = id;
    }
    public void interact(ClientContext ctx){
        ctx.npcs.select().id(id).poll().interact("");
    }
}
