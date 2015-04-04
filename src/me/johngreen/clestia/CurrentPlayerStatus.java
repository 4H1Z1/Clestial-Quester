package me.johngreen.clestia;

import me.johngreen.clestia.Values.Quests;
import org.powerbot.script.rt6.ClientContext;

import java.util.List;

/**
 * Created by johngreen on 30/03/2015.
 */
public class CurrentPlayerStatus {
    private ClientContext ctx;
    private List<Quests> compleated;
    private List<Quests> uncompleated;
    private int combatLevels;



    public CurrentPlayerStatus(ClientContext ctx){
        this.ctx = ctx;
        sortQuests();
    }
    private void sortQuests(){

    }
    private void getLevels(){

    }
}
